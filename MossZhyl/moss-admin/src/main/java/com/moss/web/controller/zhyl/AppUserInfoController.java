package com.moss.web.controller.zhyl;

import cn.hutool.core.util.StrUtil;
import com.moss.common.annotation.Log;
import com.moss.common.config.RuoYiConfig;
import com.moss.common.core.controller.BaseController;
import com.moss.common.core.domain.AjaxResult;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.common.core.domain.model.LoginUserInfo;
import com.moss.common.core.redis.RedisCache;
import com.moss.common.enums.BusinessType;
import com.moss.common.utils.CalculateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.file.FileUploadUtils;
import com.moss.common.utils.file.MimeTypeUtils;
import com.moss.framework.web.service.UserInfoTokenService;
import com.moss.zhyl.mapper.UserInfoMapper;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

import static com.moss.common.constant.RedisConstants.CAPTCHA_CODE_NO_USER_ROLE_KEY;

/**
 * 用户信息Controller
 *
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/app/zhyl/userInfo")
public class AppUserInfoController extends BaseController {
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoTokenService userInfoTokenService;

    @Autowired
    private RedisCache redisCache;
    private final static int CAPTCHA_LENGTH = 6;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasUserAnyRole('no_binding_user_info')")
    @GetMapping("/code")
    public AjaxResult code(String phoneNumber) {
        if (StrUtil.isEmpty(phoneNumber)) {
            return new AjaxResult(500, "手机号不能为空！！！");
        }
        //获取验证码
        String captcha = CalculateUtils.generateNumericCaptcha(CAPTCHA_LENGTH);
        System.err.println(captcha);
        //保存验证码信息
        redisCache.setCacheObject(CAPTCHA_CODE_NO_USER_ROLE_KEY + phoneNumber, captcha, 60 * 5, TimeUnit.SECONDS);
        // TODO 短信
        return new AjaxResult(200, "获取成功！！！");
    }

    /**
     * 绑定用户
     *
     * @param phoneNumber
     * @param captcha
     * @param idCard
     * @return
     */
    @PreAuthorize("@ss.hasUserAnyRole('no_binding_user_info')")
    @GetMapping("/binding")
    public AjaxResult binding(String phoneNumber, String captcha, String idCard) {
        if (StrUtil.isEmpty(phoneNumber) || StrUtil.isEmpty(captcha) || StrUtil.isEmpty(idCard)) {
            return new AjaxResult(500, "手机号、验证码、身份证号不能为空！！！");
        }
        //获取验证码
        String cache = redisCache.getCacheObject(CAPTCHA_CODE_NO_USER_ROLE_KEY + phoneNumber);
        if (!CalculateUtils.validateCode(captcha, cache, CAPTCHA_LENGTH)) {
            return new AjaxResult(500, "验证码错误！！！");
        }
        return success(userInfoService.bindingByUnionid(phoneNumber, captcha, idCard));
    }

    /**
     * 修改密码
     */
    @PreAuthorize("@ss.hasUserAnyRole('elderly,elderly_family')")
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword) {
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserInfoId(SecurityUtils.getLoginUserInfo().getUserInfoId());
//        System.err.println(userInfo);
        String password = userInfo.getPassword();
        System.out.println("password = " + password);
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return error("新密码不能与旧密码相同");
        }
        newPassword = SecurityUtils.encryptPassword(newPassword);
        userInfo.setPassword(newPassword);
        return success(userInfoMapper.updateUserInfo(userInfo));
    }

    /**
     * 头像上传
     */
//    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            LoginUserInfo loginUserInfo = getLoginUserInfo();
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
            UserInfo userInfo = userInfoMapper.selectUserInfoByUserInfoId(loginUserInfo.getUserInfoId());
            userInfo.setUserInfoProfile(avatar);
            if (userInfoMapper.updateUserInfo(userInfo) > 0) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUserInfo.getUserInfo().setUserInfoProfile(avatar);
                userInfoTokenService.setLoginUserInfo(loginUserInfo);
                return ajax;
            }
        }
        return error("上传图片异常，请联系管理员");
    }

}
