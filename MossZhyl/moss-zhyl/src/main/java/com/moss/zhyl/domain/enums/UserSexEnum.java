package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-30 17:50
 * @Description: UserSexEnum
 * 用户性别枚举
 * @Version: 1.0
 */
public enum UserSexEnum {

    USER_SEX_MALE("男", "0"),
    USER_SEX_FEMALE("女", "1"),
    USER_SEX_UNKNOWN("未知", "2");

    private final String text;
    private final String value;

    UserSexEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return 值列表
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值
     * @return 对应的枚举类型
     */
    public static UserSexEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (UserSexEnum sex : UserSexEnum.values()) {
            if (sex.value.equals(value)) {
                return sex;
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
