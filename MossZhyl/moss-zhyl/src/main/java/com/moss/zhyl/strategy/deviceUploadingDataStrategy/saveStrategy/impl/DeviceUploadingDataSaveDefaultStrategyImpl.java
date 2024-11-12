package com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.impl;

import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.service.IDeviceUploadingDataService;
import com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.DeviceUploadingDataSaveStrategy;
import com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.DeviceUploadingDataSaveStrategyConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.impl
 * @Author: YY
 * @CreateTime: 2024-10-27  22:04
 * @Description: DeviceUploadingDataSaveDefaultStrategyImpl
 * 默认执行的保存策略，因为特殊的只有定位和预警
 * @Version: 1.0
 */
@DeviceUploadingDataSaveStrategyConfig(command = "default")
public class DeviceUploadingDataSaveDefaultStrategyImpl implements DeviceUploadingDataSaveStrategy {
    @Autowired
    private IDeviceUploadingDataService deviceUploadingDataService;

    @Override
    public int saveDeviceUploadingData(DeviceUploadingData deviceUploadingData) {
        System.out.println("deviceUploadingData = " + deviceUploadingData);
        return deviceUploadingDataService.insertDeviceUploadingData(deviceUploadingData);
    }
}
