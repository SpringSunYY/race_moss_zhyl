package com.moss.zhyl.domain.deviceUploadingData.argument;

import com.alibaba.fastjson2.JSON;
import com.moss.zhyl.domain.deviceUploadingData.Argument;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.DeviceUploadingData.argument
 * @Author: YY
 * @CreateTime: 2024-10-27  19:44
 * @Description: HealthArgument
 * 健康数据子节点
 * @Version: 1.0
 */
public class HealthArgument extends Argument {
    private int heartRate;       // 心率
    private int dbp;             // 低压（舒张压）
    private int sdp;             // 高压（收缩压）
    private int oxygen;          // 血氧饱和度
    private String bloodSugar;   // 血糖值
    private String str_temperature; // 体温

    @Override
    public HealthArgument jsonFormatObject(String json) {
        return JSON.parseObject(json,HealthArgument.class);
    }
    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getDbp() {
        return dbp;
    }

    public void setDbp(int dbp) {
        this.dbp = dbp;
    }

    public int getSdp() {
        return sdp;
    }

    public void setSdp(int sdp) {
        this.sdp = sdp;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getStr_temperature() {
        return str_temperature;
    }

    public void setStr_temperature(String str_temperature) {
        this.str_temperature = str_temperature;
    }

    @Override
    public String toString() {
        return "HealthArgument{" +
                "heartRate=" + heartRate +
                ", dbp=" + dbp +
                ", sdp=" + sdp +
                ", oxygen=" + oxygen +
                ", bloodSugar='" + bloodSugar + '\'' +
                ", strTemperature='" + str_temperature + '\'' +
                '}';
    }
}
