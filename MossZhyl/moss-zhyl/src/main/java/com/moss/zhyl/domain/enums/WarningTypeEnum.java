package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-26 19:50
 * @Description: WarningTypeEnum
 * 预警类型枚举
 * @Version: 1.0
 */
public enum WarningTypeEnum {

    WARNING_TYPE_0("正常上传", "0"),
    WARNING_TYPE_1("sos预警", "1"),
    WARNING_TYPE_2("跌倒预警", "2"),
    WARNING_TYPE_3("脱手预警", "3"),
    WARNING_TYPE_4("久坐预警", "4"),
    WARNING_TYPE_5("低电预警", "5"),
    WARNING_TYPE_6("家庭医生", "6"),
    WARNING_TYPE_7("房颠预警", "7"),
    WARNING_TYPE_8("司法手表拆卸预警", "8"),
    WARNING_TYPE_9("红外预警", "9"),
    WARNING_TYPE_101("sos报警恢复", "101");

    private final String text;
    private final String value;

    WarningTypeEnum(String text, String value) {
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
    public static WarningTypeEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (WarningTypeEnum anEnum : WarningTypeEnum.values()) {
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
