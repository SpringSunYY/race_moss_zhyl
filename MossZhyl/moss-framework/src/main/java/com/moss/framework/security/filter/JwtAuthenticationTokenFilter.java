package com.moss.framework.security.filter;

import java.io.IOException;
import java.util.Collection;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moss.common.constant.Constants;
import com.moss.common.core.domain.model.LoginUserInfo;
import com.moss.framework.web.service.UserInfoTokenService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.moss.common.core.domain.model.LoginUser;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.framework.web.service.TokenService;

/**
 * token过滤器 验证token有效性
 *
 * @author YY
 */
@Component
@Log
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Resource
    private UserInfoTokenService userInfoTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (request.getRequestURI().contains(Constants.APP_URI)) {
            log.info("前台接口请求:" + request.getRequestURI());
            LoginUserInfo loginUserInfo = userInfoTokenService.getLoginUserInfo(request);
//            System.out.println("loginUserInfo = " + loginUserInfo);
            if (StringUtils.isNotNull(loginUserInfo) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
                userInfoTokenService.verifyToken(loginUserInfo);
                auth(loginUserInfo, loginUserInfo.getAuthorities(), request);
            }
        } else {
            logger.info("后台接口请求:" + request.getRequestURI());
            LoginUser loginUser = tokenService.getLoginUser(request);
            if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
                tokenService.verifyToken(loginUser);
                auth(loginUser, loginUser.getAuthorities(), request);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 验证token
     *
     * @param obj         登录信息
     * @param authorities 已授予的权限集合
     * @param request     网络请求
     */
    private void auth(Object obj, Collection<? extends GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(obj, null, authorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
