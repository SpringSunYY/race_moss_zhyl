package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-25 22:15
 * @Description: DeviceBindingStatusEnum
 * 设备绑定状态枚举
 * @Version: 1.0
 */
public enum DeviceBindingStatusEnum {

    DEVICE_BINDING_STATUS_0("未绑定", "0"),
    DEVICE_BINDING_STATUS_1("已绑定", "1");

    private final String text;
    private final String value;

    DeviceBindingStatusEnum(String text, String value) {
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
    public static DeviceBindingStatusEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (DeviceBindingStatusEnum anEnum : DeviceBindingStatusEnum.values()) {
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
