package com.moss.framework.web.service;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.common.core.domain.model.LoginUserInfo;
import com.moss.common.core.domain.model.WxMiniAppLoginResponse;
import com.moss.common.exception.ServiceException;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.moss.common.enums.UserInfoRoleEnum.NO_BINDING_USER_INFO;

/**
 * Project: MossZhyl
 * Package: com.moss.framework.web.service
 * Author: YY
 * CreateTime: 2024-11-06  21:01
 * Description: UserInfoLoginService
 * Version: 1.0
 */
@Component
public class UserInfoLoginService {
    //微信小程序appId
    @Value("${wx.minApp.appId}")
    private String appId;

    //微信小程序密钥
    @Value("${wx.minApp.appSecret}")
    private String appSecret;


    @Resource
    private UserInfoTokenService userInfoTokenService;

    @Resource
    private IUserInfoService userInfoService;


    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public String login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ServiceException("用户名或密码不能为空");
        }
        UserInfo userInfo = userInfoService.selectUserInfoByIdCard(username);
        if (StringUtils.isNull(userInfo)) {
            throw new ServiceException("用户不存在");
        }
        if (!SecurityUtils.matchesPassword(password, userInfo.getPassword())) {
            throw new ServiceException("密码不正确");
        }
        LoginUserInfo loginUserInfo = new LoginUserInfo(userInfo.getUserInfoId(), userInfo);
        // 生成token
        return userInfoTokenService.createAppToken(loginUserInfo);
    }

    /**
     * 小程序一键登录
     *
     * @return token
     */
    public String miniProgramLogin(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new ServiceException("微信授权码不能为空");
        }
        //微信小程序获取openId请求地址
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", appId).replace("{1}", appSecret).replace("{2}", code);
        String res = HttpUtil.get(replaceUrl);
//        System.err.println(res);
        WxMiniAppLoginResponse response = JSONUtil.toBean(res, WxMiniAppLoginResponse.class);
        String errcode = response.getErrcode();
        String errmsg = response.getErrmsg();
        if (StrUtil.isNotEmpty(errcode)) {
            throw new ServiceException(StrUtil.format("获取微信授权信息失败，错误编码{}，错误信息：{}", response.getErrcode(), response.getErrmsg()));
        }
        String openid = response.getOpenid();
        String sessionKey = response.getSessionKey();
        String unionid = response.getUnionid();
        //查询用户信息
        UserInfo userInfo = userInfoService.selectUserInfoByUnionIdOrOpenId(unionid, openid);
        //如果为空则为新用户或者为平台用户但是从未使用微信登录过的用户 为他赋值未授权用户临时登录，之后可以自己授权
        if (StringUtils.isNull(userInfo)) {
            UserInfo info = new UserInfo();
            info.setUserInfoName("未授权用户");
            info.setWxOpenid(openid);
            info.setWxUnionid(unionid);
            info.setUserInfoRole(NO_BINDING_USER_INFO.getValue());
            LoginUserInfo loginUserInfo = new LoginUserInfo(info);
            return userInfoTokenService.createAppToken(loginUserInfo);
        }
        LoginUserInfo loginUserInfo = new LoginUserInfo(userInfo.getUserInfoId(), userInfo);
        // 生成token
        return userInfoTokenService.createAppToken(loginUserInfo);
    }

}
