package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-30 17:55
 * @Description: DeviceUploadingCommandTypeEnum
 * 设备上传命令类型枚举
 * @Version: 1.0
 */
public enum DeviceUploadingCommandTypeEnum {

    UPDATE("更新", "update"),
    CONNECTED("设备上线", "connected"),
    DISCONNECTED("设备离线", "disconnected");

    private final String text;
    private final String value;

    DeviceUploadingCommandTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值
     * @return 对应的枚举类型
     */
    public static DeviceUploadingCommandTypeEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return Arrays.stream(DeviceUploadingCommandTypeEnum.values())
                .filter(command -> command.value.equals(value))
                .findFirst()
                .orElse(null);
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
