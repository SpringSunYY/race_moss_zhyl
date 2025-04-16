package com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy;

import com.moss.common.exception.ServiceException;
import com.moss.common.utils.StringUtils;
import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.impl.DeviceUploadingDataSaveDefaultStrategyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy
 * @Author: YY
 * @CreateTime: 2024-10-27  21:51
 * @Description: DeviceUploadingDataSaveStrategyExecutor
 * 上传数据策略模式执行器
 * @Version: 1.0
 */
@Service
public class DeviceUploadingDataSaveStrategyExecutor {
    //策略模式列表
    @Autowired
    private List<DeviceUploadingDataSaveStrategy> strategies;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 保存上传数据
     *
     * @param deviceUploadingData
     * @return
     */
    public int saveDeviceUploadingData(DeviceUploadingData deviceUploadingData) {
        System.out.println("deviceUploadingData = " + deviceUploadingData);
        String command = deviceUploadingData.getCommand();
        if (StringUtils.isEmpty(command)) {
            throw new ServiceException("上传数据命令为空，请输入上传命令！！！");
        }
        //根据注解获取策略
        for (DeviceUploadingDataSaveStrategy strategy : strategies) {
            //判断是否存在注解
            if (strategy.getClass().isAnnotationPresent(DeviceUploadingDataSaveStrategyConfig.class)) {
                //获取到配置
                DeviceUploadingDataSaveStrategyConfig config = strategy.getClass().getAnnotation(DeviceUploadingDataSaveStrategyConfig.class);
                if (config.command().equals(command)) {
                    return strategy.saveDeviceUploadingData(deviceUploadingData);
                }
            }
        }
        //如果都不存在，执行默认的
        DeviceUploadingDataSaveStrategy defaultStrategy = applicationContext.getBean(DeviceUploadingDataSaveDefaultStrategyImpl.class);
        return defaultStrategy.saveDeviceUploadingData(deviceUploadingData);
    }
}
