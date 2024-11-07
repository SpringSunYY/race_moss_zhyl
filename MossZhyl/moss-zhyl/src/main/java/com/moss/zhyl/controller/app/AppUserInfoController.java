package com.moss.zhyl.controller.app;

import cn.hutool.core.util.StrUtil;
import com.moss.common.annotation.Log;
import com.moss.common.core.controller.BaseController;
import com.moss.common.core.domain.AjaxResult;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.common.core.page.TableDataInfo;
import com.moss.common.core.redis.RedisCache;
import com.moss.common.enums.BusinessType;
import com.moss.common.utils.CalculateUtils;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.zhyl.domain.dto.UserInfoElderlyDto;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    private RedisCache redisCache;
    private final static int CAPTCHA_LENGTH = 6;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasUserAnyRole('no_binding_user_info,elderly')")
    @GetMapping("/code")
    public AjaxResult code(String phoneNumber) {
        if (StrUtil.isEmpty(phoneNumber)) {
            return new AjaxResult(500, "手机号不能为空！！！");
        }
        //获取验证码
        String captcha = CalculateUtils.generateNumericCaptcha(CAPTCHA_LENGTH);
        System.err.println(captcha);
        //保存验证码信息
        redisCache.setCacheObject(CAPTCHA_CODE_NO_USER_ROLE_KEY + phoneNumber, captcha, 60, TimeUnit.SECONDS);
        // TODO 短信
        return new AjaxResult(200, "获取成功！！！");
    }
}
