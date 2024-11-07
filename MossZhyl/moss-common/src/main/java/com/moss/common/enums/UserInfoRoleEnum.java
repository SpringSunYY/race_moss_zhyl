package com.moss.common.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-24 11:08
 * @Description: UserInfoRoleEnum
 * 用户信息角色枚举
 * @Version: 1.0
 */
public enum UserInfoRoleEnum {

    ELDERLY("长者", "elderly"),
    ELDERLY_FAMILY("家属", "elderly_family"),
    NO_BINDING_USER_INFO("没有绑定的用户", "no_binding_user_info");

    private final String text;
    private final String value;

    UserInfoRoleEnum(String text, String value) {
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
    public static UserInfoRoleEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (UserInfoRoleEnum anEnum : UserInfoRoleEnum.values()) {
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
