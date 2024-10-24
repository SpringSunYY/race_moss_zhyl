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
 * @Description: AddressTypeEnum
 * 地址类型枚举
 * @Version: 1.0
 */
public enum AddressTypeEnum {

    ADDRESS_TYPE_ENUM_0("国家", "0"),
    ADDRESS_TYPE_ENUM_1("省", "1"),
    ADDRESS_TYPE_ENUM_2("市", "2"),
    ADDRESS_TYPE_ENUM_3("区县", "3"),
    ADDRESS_TYPE_ENUM_4("乡镇街道", "4");

    private final String text;
    private final String value;

    AddressTypeEnum(String text, String value) {
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
    public static AddressTypeEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (AddressTypeEnum anEnum : AddressTypeEnum.values()) {
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
