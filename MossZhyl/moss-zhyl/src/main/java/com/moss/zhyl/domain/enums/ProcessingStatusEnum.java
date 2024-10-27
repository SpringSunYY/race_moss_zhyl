package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-24 11:10
 * @Description: ProcessingStatusEnum
 * 处理状态枚举
 * @Version: 1.0
 */
public enum ProcessingStatusEnum {

    PROCESSING_STATUS_ENUM_1("待处理", "1"),
    PROCESSING_STATUS_ENUM_2("处理完成", "2"),
    PROCESSING_STATUS_ENUM_3("无需处理", "3"),
    PROCESSING_STATUS_ENUM_4("处理失败", "4");
//    PROCESSING_STATUS_ENUM_5("处理失败", "5");

    private final String text;
    private final String value;

    ProcessingStatusEnum(String text, String value) {
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
    public static ProcessingStatusEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (ProcessingStatusEnum anEnum : ProcessingStatusEnum.values()) {
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
