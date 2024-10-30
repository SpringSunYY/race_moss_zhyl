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
 * @Description: HealthReportTypeEnum
 * 健康报告类型枚举
 * @Version: 1.0
 */
public enum HealthReportTypeEnum {

    HEALTH_REPORT_TYPE_OTHER("其他", "0"),
    HEALTH_REPORT_TYPE_DATA("健康数据", "1");

    private final String text;
    private final String value;

    HealthReportTypeEnum(String text, String value) {
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
    public static HealthReportTypeEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (HealthReportTypeEnum anEnum : HealthReportTypeEnum.values()) {
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
