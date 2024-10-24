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
 * @Description: PowerSupplyModeEnum
 * 电源供应模式类型枚举
 * @Version: 1.0
 */
public enum PowerSupplyModeEnum {

    POWER_SUPPLY_MODE_ENUM_0("其他", "0"),
    POWER_SUPPLY_MODE_ENUM_1("铝电池", "1"),
    POWER_SUPPLY_MODE_ENUM_2("适配器", "2");

    private final String text;
    private final String value;

    PowerSupplyModeEnum(String text, String value) {
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
    public static PowerSupplyModeEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (PowerSupplyModeEnum anEnum : PowerSupplyModeEnum.values()) {
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
