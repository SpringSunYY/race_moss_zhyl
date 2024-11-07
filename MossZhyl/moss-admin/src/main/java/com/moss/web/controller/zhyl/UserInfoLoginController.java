package com.moss.web.controller.zhyl;

import com.moss.common.annotation.Anonymous;
import com.moss.common.constant.Constants;
import com.moss.common.core.controller.BaseController;
import com.moss.common.core.domain.AjaxResult;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.common.core.domain.model.LoginUserInfo;
import com.moss.framework.web.service.UserInfoLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: MossZhyl
 * Package: com.moss.web.controller.zhyl
 * Author: YY
 * CreateTime: 2024-11-06  21:23
 * Description: UserInfoLoginController
 * 用户登录
 * Version: 1.0
 */
@RestController
@RequestMapping("/app")
public class UserInfoLoginController extends BaseController {
    @Autowired
    private UserInfoLoginService userInfoLoginService;


    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginUserInfo loginUserInfo) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = userInfoLoginService.login(loginUserInfo.getUsername(), loginUserInfo.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @GetMapping("/userInfo")
    @Anonymous
    public AjaxResult userInfo() {
        UserInfo userInfo = getLoginUserInfo().getUserInfo();
//        System.err.println(userInfo);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("userInfo", userInfo);
        return ajax;
    }

    @GetMapping("/login/miniProgramLogin")
    public AjaxResult miniProgramLogin(@RequestParam("code") String code) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = userInfoLoginService.miniProgramLogin(code);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
}
