package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-24 11:08
 * @Description: DeviceStatusEnum
 * 设备状态类型枚举
 * @Version: 1.0
 */
public enum DeviceStatusEnum {

    DEVICE_STATUS_ENUM_0("在线", "0"),
    DEVICE_STATUS_ENUM_1("离线", "1");

    private final String text;
    private final String value;

    DeviceStatusEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static DeviceStatusEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (DeviceStatusEnum anEnum : DeviceStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
