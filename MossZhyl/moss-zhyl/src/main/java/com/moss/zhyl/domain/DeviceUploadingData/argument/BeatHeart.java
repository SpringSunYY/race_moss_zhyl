package com.moss.zhyl.domain.DeviceUploadingData.argument;

import com.alibaba.fastjson2.JSON;
import com.moss.zhyl.domain.DeviceUploadingData.Argument;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.DeviceUploadingData.argument
 * @Author: YY
 * @CreateTime: 2024-10-27  19:46
 * @Description: BeatHeart
 * 设备电量/计步数据/温度计电量子节点
 * @Version: 1.0
 */
public class BeatHeart extends Argument {
    private Integer battery; // 设备电量
    private Integer pedometer; // 计步数据
    private Integer tmBattery; // 温度计电量

    @Override
    public BeatHeart jsonFormatObject(String json) {
        return JSON.parseObject(json, BeatHeart.class);
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Integer getPedometer() {
        return pedometer;
    }

    public void setPedometer(Integer pedometer) {
        this.pedometer = pedometer;
    }

    public Integer getTmBattery() {
        return tmBattery;
    }

    public void setTmBattery(Integer tmBattery) {
        this.tmBattery = tmBattery;
    }

    @Override
    public String toString() {
        return "BeatHeart{" +
                "battery=" + battery +
                ", pedometer=" + pedometer +
                ", tmBattery=" + tmBattery +
                '}';
    }
}
