package com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy;

import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy
 * @Author: YY
 * @CreateTime: 2024-10-27  21:45
 * @Description: DeviceUploadingDataSaveStrategy
 * 保存上传数据策略模式
 * 目前特殊的
 * @Version: 1.0
 */
public interface DeviceUploadingDataSaveStrategy {
    /**
     * @description: 保存上传数据
     * @author: YY
     * @method: saveDeviceUploadingData
     * @date: 2024/10/27 21:55
     * @param:
     * @return: com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData
     **/
    int saveDeviceUploadingData(DeviceUploadingData deviceUploadingData);
}
