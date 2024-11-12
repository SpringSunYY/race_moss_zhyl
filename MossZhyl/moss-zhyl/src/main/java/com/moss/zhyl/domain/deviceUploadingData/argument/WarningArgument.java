package com.moss.zhyl.domain.deviceUploadingData.argument;

import com.alibaba.fastjson2.JSON;
import com.moss.zhyl.domain.deviceUploadingData.Argument;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.DeviceUploadingData.argument
 * @Author: YY
 * @CreateTime: 2024-10-27  19:42
 * @Description: WarningArgument
 * 警告子节点
 * @Version: 1.0
 */
public class WarningArgument extends Argument {
    private String warningType; // 预警类型，例如 "1" 表示 SOS 预警
    private Integer battery;    // 设备电量（仅在低电预警时有值）
    private String address;     // 预警地址（仅在 SOS 预警时有值）
    private String lon;         // SOS 高德坐标经度（仅在 SOS 预警时有值）
    private String lat;         // SOS 高德坐标纬度（仅在 SOS 预警时有值）

    @Override
    public WarningArgument jsonFormatObject(String json) {
        return JSON.parseObject(json, WarningArgument.class);
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "WarningArgument{" +
                "warningType='" + warningType + '\'' +
                ", battery=" + battery +
                ", address='" + address + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
