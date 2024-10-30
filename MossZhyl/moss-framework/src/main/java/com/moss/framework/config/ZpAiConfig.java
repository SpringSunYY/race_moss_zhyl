package com.moss.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.zhipu.oapi.ClientV4;

import java.util.concurrent.TimeUnit;

/**
 * @Project: MossZhyl
 * @Package: com.moss.framework.config
 * @Author: YY
 * @CreateTime: 2024-10-30  08:53
 * @Description: ZpAiConfig
 * 智普ai配置
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "ai")
public class ZpAiConfig {
    /**
     * apikey
     */
    private String apiKey;

    @Bean
    public ClientV4 getClientV4() {
        return new ClientV4.Builder(apiKey).networkConfig(60,60,60,60, TimeUnit.SECONDS).build();
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
