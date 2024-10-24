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
 * @Description: EducationEnum
 * 教育类型枚举
 * @Version: 1.0
 */
public enum EducationEnum {

    EDUCATION_ENUM_0("本科", "0"),
    EDUCATION_ENUM_1("专科", "1"),
    EDUCATION_ENUM_2("硕士", "2"),
    EDUCATION_ENUM_3("高中", "3"),
    EDUCATION_ENUM_4("其他", "4");

    private final String text;
    private final String value;

    EducationEnum(String text, String value) {
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
    public static EducationEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (EducationEnum anEnum : EducationEnum.values()) {
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
