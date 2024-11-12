package com.moss.zhyl.controller;

import com.moss.common.core.controller.BaseController;
import com.moss.common.core.domain.AjaxResult;
import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.zhyl.service.IStaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl.controller
 * Author: YY
 * CreateTime: 2024-11-01  22:13
 * Description: StaticsController
 * Version: 1.0
 */
@RestController
@RequestMapping("/zhyl/statics")
public class StaticsController extends BaseController {
    @Autowired
    private IStaticsService staticsService;

    @GetMapping(value = "/getCounts")
    public AjaxResult getCounts() {
        return success(staticsService.getCounts());
    }

    @GetMapping("/getUserinfoFamilyStaticByCreateTimeCounts")
    public AjaxResult getUserinfoFamilyStaticByCreateTimeCounts(UserInfo userInfo) {
        return success(staticsService.getUserinfoFamilyStaticByCreateTimeCounts(userInfo));
    }

    @GetMapping("/getUserinfoElderlyStaticByCreateTimeCounts")
    public AjaxResult getUserinfoElderlyStaticByCreateTimeCounts(UserInfo userInfo) {
        return success(staticsService.getUserinfoElderlyStaticByCreateTimeCounts(userInfo));
    }

    @GetMapping("/getUploadingDateStaticByCreateTimeCounts")
    public AjaxResult getUploadingDateStaticByCreateTimeCounts(DeviceUploadingData deviceUploadingData) {
        return success(staticsService.getUploadingDateStaticByCreateTimeCounts(deviceUploadingData));
    }

    @GetMapping("/getBindingStaticByCreateTimeCounts")
    public AjaxResult getBindingStaticByCreateTimeCounts(ElderlyDeviceBinding elderlyDeviceBinding) {
        return success(staticsService.getBindingStaticByCreateTimeCounts(elderlyDeviceBinding));
    }

    @GetMapping("/getUserInfoElderlyAge")
    public AjaxResult getUserInfoElderlyAge(UserInfo userInfo) {
        return success(staticsService.getUserInfoAge(userInfo));
    }

    @GetMapping("/getDeviceByDeviceTypeCount")
    public AjaxResult getDeviceByDeviceTypeCount(ElderlyDeviceBinding elderlyDeviceBinding) {
        return success(staticsService.getDeviceByDeviceTypeCount(elderlyDeviceBinding));
    }
}
