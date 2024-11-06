package com.moss.framework.web.service;

import com.moss.common.constant.CacheConstants;
import com.moss.common.constant.Constants;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.common.core.domain.model.LoginUserInfo;
import com.moss.common.core.redis.RedisCache;
import com.moss.common.utils.ServletUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.ip.AddressUtils;
import com.moss.common.utils.ip.IpUtils;
import com.moss.common.utils.uuid.IdUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Project: MossZhyl
 * Package: com.moss.framework.web.service
 * Author: YY
 * CreateTime: 2024-11-06  20:51
 * Description: UserInfoTokenService
 * 用户登录token
 * Version: 1.0
 */
@Component
public class UserInfoTokenService {
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.userInfoExpireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Resource
    private RedisCache redisCache;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUserInfo getLoginUserInfo(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Constants.LOGIN_USER_INFO_KEY);
                String userKey = getTokenKey(uuid);
                LoginUserInfo userInfo = redisCache.getCacheObject(userKey);
                return userInfo;
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUserInfo(LoginUserInfo loginUserInfo) {
        if (StringUtils.isNotNull(loginUserInfo) && StringUtils.isNotEmpty(loginUserInfo.getToken())) {
            refreshToken(loginUserInfo);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUserInfo(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param loginUserInfo 用户信息
     * @return 令牌
     */
    public String createAppToken(LoginUserInfo loginUserInfo) {
        String token = IdUtils.fastUUID();
        loginUserInfo.setToken(token);
        setUserAgent(loginUserInfo);
        refreshToken(loginUserInfo);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_INFO_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUserInfo
     * @return 令牌
     */
    public void verifyToken(LoginUserInfo loginUserInfo) {
        long expireTime = loginUserInfo.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUserInfo);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUserInfo 登录信息
     */
    public void refreshToken(LoginUserInfo loginUserInfo) {
        loginUserInfo.setRole(loginUserInfo.getRole());
        loginUserInfo.setLoginTime(System.currentTimeMillis());
        loginUserInfo.setExpireTime(loginUserInfo.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUserInfo.getToken());
        System.err.println("刷新令牌"+loginUserInfo);
        redisCache.setCacheObject(userKey, loginUserInfo, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUserInfo 登录信息
     */
    public void setUserAgent(LoginUserInfo loginUserInfo) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr();
        loginUserInfo.setIpaddr(ip);
        loginUserInfo.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUserInfo.setBrowser(userAgent.getBrowser().getName());
        loginUserInfo.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return CacheConstants.LOGIN_USER_INFO_TOKEN_KEY + uuid;
    }
}
