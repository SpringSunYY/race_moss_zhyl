package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-24 11:08
 * @Description: DisabilityStatusEnum
 * 残疾状态枚举
 * @Version: 1.0
 */
public enum DisabilityStatusEnum {

    DISABILITY_STATUS_ENUM_0("能力完好", "0"),
    DISABILITY_STATUS_ENUM_1("轻度", "1"),
    DISABILITY_STATUS_ENUM_2("中度", "2"),
    DISABILITY_STATUS_ENUM_3("重度", "3");

    private final String text;
    private final String value;

    DisabilityStatusEnum(String text, String value) {
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
    public static DisabilityStatusEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (DisabilityStatusEnum anEnum : DisabilityStatusEnum.values()) {
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
