package com.moss.zpai.model.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-30 17:45
 * @Description: HealthReportTaskStatusEnum
 * 健康报告任务状态枚举
 * @Version: 1.0
 */
public enum AsyncReportTaskStatusEnum {

    HEALTH_REPORT_TASK_STATUS_PROCESSING("进行中", "PROCESSING"),
    HEALTH_REPORT_TASK_STATUS_SUCCESS("成功", "SUCCESS"),
    HEALTH_REPORT_TASK_STATUS_FAIL("失败", "FAIL");

    private final String text;
    private final String value;

    AsyncReportTaskStatusEnum(String text, String value) {
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
    public static AsyncReportTaskStatusEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (AsyncReportTaskStatusEnum status : AsyncReportTaskStatusEnum.values()) {
            if (status.value.equals(value)) {
                return status;
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
