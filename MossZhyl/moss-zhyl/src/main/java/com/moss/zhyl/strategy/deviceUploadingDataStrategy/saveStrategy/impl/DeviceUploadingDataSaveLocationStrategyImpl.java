package com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.impl;

import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.deviceUploadingData.argument.LocationArgument;
import com.moss.zhyl.service.IDeviceUploadingDataService;
import com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.DeviceUploadingDataSaveStrategy;
import com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.DeviceUploadingDataSaveStrategyConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.impl
 * @Author: YY
 * @CreateTime: 2024-10-27  22:04
 * @Description: DeviceUploadingDataSaveLocationStrategyImpl
 * 默认执行的保存策略，因为特殊的只有定位和预警
 * 位置
 * @Version: 1.0
 */
@DeviceUploadingDataSaveStrategyConfig(command = "location")
public class DeviceUploadingDataSaveLocationStrategyImpl implements DeviceUploadingDataSaveStrategy {
    @Autowired
    IDeviceUploadingDataService deviceUploadingDataService;

    @Override
    public int saveDeviceUploadingData(DeviceUploadingData deviceUploadingData) {
        try {
            LocationArgument argument = new LocationArgument();
            argument = argument.jsonFormatObject(deviceUploadingData.getArgument());
            deviceUploadingData.setLat(argument.getLat());
            deviceUploadingData.setLon(argument.getLon());
        } catch (Exception e) {
            System.out.println("上传数据--转换位置信息失败" + e.getMessage());
        }
        return deviceUploadingDataService.insertDeviceUploadingData(deviceUploadingData);
    }
}
