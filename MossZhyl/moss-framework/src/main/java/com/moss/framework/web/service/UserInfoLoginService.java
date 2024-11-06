package com.moss.framework.web.service;


import com.moss.common.core.domain.entity.UserInfo;
import com.moss.common.core.domain.model.LoginUserInfo;
import com.moss.common.exception.ServiceException;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
        UserInfo userInfo = userInfoService.selectUserInfoByIdCard(username);
        if (StringUtils.isNull(userInfo)) {
            throw new ServiceException("用户不存在");
        }
        if (SecurityUtils.matchesPassword(password, userInfo.getPassword())) {
            throw new ServiceException("密码不正确");
        }
        LoginUserInfo loginUserInfo = new LoginUserInfo(userInfo.getUserInfoId(), userInfo);
        // 生成token
        return userInfoTokenService.createAppToken(loginUserInfo);
    }

}
