package com.moss.zhyl.controller.app;

import com.moss.common.core.controller.BaseController;
import com.moss.common.core.domain.AjaxResult;
import com.moss.common.core.page.TableDataInfo;
import com.moss.common.utils.PageUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.zhyl.domain.deviceUploadingData.Argument;
import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.domain.enums.DeviceUploadingCommandTypeEnum;
import com.moss.zhyl.mapper.DeviceUploadingDataMapper;
import com.moss.zhyl.service.IDeviceUploadingDataService;
import com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.DeviceUploadingDataSaveStrategyExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.moss.zhyl.domain.enums.DeviceUploadingDataCommandEnum.DEVICE_UPLOADING_DATA_COMMAND_HEALTH;

/**
 * 设备上传数据Controller
 *
 * @author YY
 * @date 2024-10-26
 */
@RestController
@RequestMapping("/app/zhyl/deviceUploadingData")
public class AppDeviceUploadingDataController extends BaseController {
    @Autowired
    private IDeviceUploadingDataService deviceUploadingDataService;

    @Autowired
    private DeviceUploadingDataSaveStrategyExecutor deviceUploadingDataSaveStrategyExecutor;

    @Autowired
    private DeviceUploadingDataMapper deviceUploadingDataMapper;

    /**
     * 获取自己设备最新健康数据
     */
    @PreAuthorize("@ss.hasUserAnyRole('elderly,elderly_family')")
    @GetMapping("/newHealthData")
    public AjaxResult newHealthData(DeviceUploadingData deviceUploadingData) {
        deviceUploadingData.setUserInfoId(SecurityUtils.getLoginUserInfo().getUserInfoId());
        deviceUploadingData.setType(DeviceUploadingCommandTypeEnum.UPDATE.getValue());
        deviceUploadingData.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        deviceUploadingData.setCommand(DEVICE_UPLOADING_DATA_COMMAND_HEALTH.getValue());
        DeviceUploadingData newData = deviceUploadingDataMapper.selectNewData(deviceUploadingData);
        if (StringUtils.isNotNull(newData)) {
            Argument.returnArgumentData(newData);
        }
        return success(newData);
    }

    /**
     * 获取自己的健康数据
     */
    @PreAuthorize("@ss.hasUserAnyRole('elderly,elderly_family')")
    @GetMapping("/healthData")
    public TableDataInfo healthData(DeviceUploadingData deviceUploadingData) {
        PageUtils.startPage();
        deviceUploadingData.setUserInfoId(SecurityUtils.getLoginUserInfo().getUserInfoId());
        deviceUploadingData.setType(DeviceUploadingCommandTypeEnum.UPDATE.getValue());
        deviceUploadingData.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        deviceUploadingData.setCommand(DEVICE_UPLOADING_DATA_COMMAND_HEALTH.getValue());
        List<DeviceUploadingData> list = deviceUploadingDataMapper.selectDeviceUploadingDataList(deviceUploadingData);
        for (DeviceUploadingData info : list) {
            Argument.returnArgumentData(info);
        }
        return getDataTable(list);
    }
}
