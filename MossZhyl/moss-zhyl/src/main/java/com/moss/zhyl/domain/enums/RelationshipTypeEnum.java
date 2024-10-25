package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-25 15:20
 * @Description: RelationshipTypeEnum
 * 关系类型枚举
 * @Version: 1.0
 */
public enum RelationshipTypeEnum {

    RELATIONSHIP_TYPE_0("其他", "0"),
    RELATIONSHIP_TYPE_1("配偶", "1"),
    RELATIONSHIP_TYPE_2("子女", "2"),
    RELATIONSHIP_TYPE_3("子孙", "3");

    private final String text;
    private final String value;

    RelationshipTypeEnum(String text, String value) {
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
    public static RelationshipTypeEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (RelationshipTypeEnum anEnum : RelationshipTypeEnum.values()) {
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
