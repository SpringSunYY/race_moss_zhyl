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
 * @Description: InstallationModeEnum
 * 安装模式类型枚举
 * @Version: 1.0
 */
public enum InstallationModeEnum {

    INSTALLATION_MODE_ENUM_0("其他", "0"),
    INSTALLATION_MODE_ENUM_1("挂脖", "1"),
    INSTALLATION_MODE_ENUM_2("手腕佩戴", "2"),
    INSTALLATION_MODE_ENUM_3("吸顶", "3"),
    INSTALLATION_MODE_ENUM_4("挂壁", "4");

    private final String text;
    private final String value;

    InstallationModeEnum(String text, String value) {
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
    public static InstallationModeEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (InstallationModeEnum anEnum : InstallationModeEnum.values()) {
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
