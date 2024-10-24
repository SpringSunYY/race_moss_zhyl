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
 * @Description: LivingConditionEnum
 * 生活条件类型枚举
 * @Version: 1.0
 */
public enum LivingConditionEnum {

    LIVING_CONDITION_ENUM_0("其他", "0"),
    LIVING_CONDITION_ENUM_1("独自居住", "1"),
    LIVING_CONDITION_ENUM_2("与配偶居住", "2"),
    LIVING_CONDITION_ENUM_3("与子女居住", "3"),
    LIVING_CONDITION_ENUM_4("与亲戚居住", "4");

    private final String text;
    private final String value;

    LivingConditionEnum(String text, String value) {
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
    public static LivingConditionEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (LivingConditionEnum anEnum : LivingConditionEnum.values()) {
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
