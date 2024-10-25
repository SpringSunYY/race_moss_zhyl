package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-25 21:40
 * @Description: DeviceTypeEnum
 * 设备类型枚举
 * @Version: 1.0
 */
public enum DeviceTypeEnum {

    DEVICE_TYPE_0("其他", "0"),
    DEVICE_TYPE_1("手表", "1");

    private final String text;
    private final String value;

    DeviceTypeEnum(String text, String value) {
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
    public static DeviceTypeEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (DeviceTypeEnum anEnum : DeviceTypeEnum.values()) {
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
