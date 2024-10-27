package com.moss.zhyl.domain.enums;

import com.moss.common.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.enums
 * @Author: YY
 * @CreateTime: 2024-10-26 19:45
 * @Description: DeviceUploadingDataCommandEnum
 * 设备数据上传命令枚举
 * @Version: 1.0
 */
public enum DeviceUploadingDataCommandEnum {

    DEVICE_UPLOADING_DATA_COMMAND_DEFAULT("默认", "default"),
    DEVICE_UPLOADING_DATA_COMMAND_DISCONNECTED("设备离线", "disconnected"),
    DEVICE_UPLOADING_DATA_COMMAND_CONNECTED("设备上线", "connected"),
    DEVICE_UPLOADING_DATA_COMMAND_LOCATION("定位信息", "location"),
    DEVICE_UPLOADING_DATA_COMMAND_WARNING("预警信息", "warning"),
    DEVICE_UPLOADING_DATA_COMMAND_HEALTH("健康信息", "health"),
    DEVICE_UPLOADING_DATA_COMMAND_BEAT_HEART("运动情况", "beatHeart");

    private final String text;
    private final String value;

    DeviceUploadingDataCommandEnum(String text, String value) {
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
    public static DeviceUploadingDataCommandEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (DeviceUploadingDataCommandEnum anEnum : DeviceUploadingDataCommandEnum.values()) {
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
