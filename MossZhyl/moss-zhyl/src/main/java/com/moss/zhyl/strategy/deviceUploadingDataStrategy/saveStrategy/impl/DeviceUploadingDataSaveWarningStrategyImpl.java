package com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.impl;

import com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.DeviceUploadingData.argument.LocationArgument;
import com.moss.zhyl.domain.DeviceUploadingData.argument.WarningArgument;
import com.moss.zhyl.domain.enums.ProcessingStatusEnum;
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
 * 警告
 * @Version: 1.0
 */
@DeviceUploadingDataSaveStrategyConfig(command = "warning")
public class DeviceUploadingDataSaveWarningStrategyImpl implements DeviceUploadingDataSaveStrategy {
    @Autowired
    IDeviceUploadingDataService deviceUploadingDataService;

    @Override
    public int saveDeviceUploadingData(DeviceUploadingData deviceUploadingData) {
        try {
            WarningArgument argument = new WarningArgument();
            argument = argument.jsonFormatObject(deviceUploadingData.getArgument());
            deviceUploadingData.setLat(argument.getLat());
            deviceUploadingData.setLon(argument.getLon());
            //处理状态
            deviceUploadingData.setProcessingStatus(ProcessingStatusEnum.PROCESSING_STATUS_ENUM_1.getValue());
            //报警类型
            deviceUploadingData.setWarningType(argument.getWarningType());
        } catch (Exception e) {
            System.out.println("上传数据--转换位置信息失败" + e.getMessage());
        }
        return deviceUploadingDataService.insertDeviceUploadingData(deviceUploadingData);
        // TODO 为管理者发送信息
    }
}
