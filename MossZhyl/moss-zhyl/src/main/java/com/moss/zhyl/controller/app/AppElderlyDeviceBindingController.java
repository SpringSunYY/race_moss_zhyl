package com.moss.zhyl.controller.app;

import com.moss.common.core.controller.BaseController;
import com.moss.common.core.page.TableDataInfo;
import com.moss.common.utils.SecurityUtils;
import com.moss.zhyl.domain.Device;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.zhyl.service.IElderlyDeviceBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl.controller.app
 * Author: YY
 * CreateTime: 2024-11-12  11:34
 * Description: AppElderlyDeviceBindingController
 * 关于设备的信息
 * Version: 1.0
 */
@RestController
@RequestMapping("/app/zhyl/elderlyDeviceBinding")
public class AppElderlyDeviceBindingController extends BaseController {
    @Autowired
    private IElderlyDeviceBindingService elderlyDeviceBindingService;

    /**
     * 查询长者设备绑定列表
     */
    @PreAuthorize("@ss.hasUserAnyRole('elderly,elderly_family')")
    @GetMapping("/list")
    public TableDataInfo list(ElderlyDeviceBinding elderlyDeviceBinding) {
        startPage();
        elderlyDeviceBinding.setUserInfoId(SecurityUtils.getLoginUserInfo().getUserInfoId());
        List<ElderlyDeviceBinding> list = elderlyDeviceBindingService.selectAppElderlyDeviceBindingList(elderlyDeviceBinding);
        return getDataTable(list);
    }
}
