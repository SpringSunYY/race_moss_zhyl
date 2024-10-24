package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-24 11:30
 * @Description: ConsultTypeEnum
 * 咨询类型枚举
 * @Version: 1.0
 */
public enum ConsultTypeEnum {

    CONSULT_TYPE_ENUM_0("公告", "0"),
    CONSULT_TYPE_ENUM_1("防诈咨询", "1"),
    CONSULT_TYPE_ENUM_2("通知", "2"),
    CONSULT_TYPE_ENUM_3("其他", "3");

    private final String text;
    private final String value;

    ConsultTypeEnum(String text, String value) {
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
    public static ConsultTypeEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (ConsultTypeEnum anEnum : ConsultTypeEnum.values()) {
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
