package com.moss.zhyl.domain.deviceUploadingData.argument;

import com.alibaba.fastjson2.JSON;
import com.moss.zhyl.domain.deviceUploadingData.Argument;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.DeviceUploadingData.argument
 * @Author: YY
 * @CreateTime: 2024-10-27  19:19
 * @Description: LocationArgument
 * 叶子节点（Leaf）：叶子节点对象，其下再无分支，是系统层次遍历的最小单位。
 * @Version: 1.0
 */
public class LocationArgument extends Argument {
    private String mapType; // 地图类型，目前只支持高德
    private String lon;     // 高德坐标经度
    private String lat;     // 高德坐标纬度

    @Override
    public LocationArgument jsonFormatObject(String json) {
        return JSON.parseObject(json, LocationArgument.class);
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
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
        return "LocationArgument{" +
                "mapType='" + mapType + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
