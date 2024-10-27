package com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy
 * @Author: YY
 * @CreateTime: 2024-10-27  21:49
 * @Description: DeviceUploadingDataSaveStrategyConfig
 * 上传数据保存配置
 * @Version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface DeviceUploadingDataSaveStrategyConfig {
     String command();
}
