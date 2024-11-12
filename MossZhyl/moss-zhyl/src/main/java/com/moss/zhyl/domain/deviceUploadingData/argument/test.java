package com.moss.zhyl.domain.deviceUploadingData.argument;

import java.util.Date;

import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.DeviceUploadingData.argument
 * @Author: YY
 * @CreateTime: 2024-10-27  19:26
 * @Description: test
 * @Version: 1.0
 */
public class test {
    public static void main(String[] args) {
        DeviceUploadingData deviceUploadingData = new DeviceUploadingData();
        deviceUploadingData.setDataId(0L);
        deviceUploadingData.setDeviceId("");
        deviceUploadingData.setType("");
        deviceUploadingData.setCommand("");
        deviceUploadingData.setDeviceType("");
        deviceUploadingData.setUserInfoId(0L);
        LocationArgument argument = new LocationArgument();
        String json = "{ \"mapType\": \"1\", \"lon\": 120.31, \"lat\": 30.12 }";
        deviceUploadingData.setArgumentData(argument.jsonFormatObject(json));
        deviceUploadingData.setWarningType("");
        deviceUploadingData.setLon("");
        deviceUploadingData.setLat("");
        deviceUploadingData.setProcessingStatus("");
        deviceUploadingData.setDelFlag("");
        deviceUploadingData.setSearchValue("");
        deviceUploadingData.setCreateBy("");
        deviceUploadingData.setCreateTime(new Date());
        deviceUploadingData.setUpdateBy("");
        deviceUploadingData.setUpdateTime(new Date());
        deviceUploadingData.setRemark("");
        System.out.println("json = " + json);
        System.out.println("deviceUploadingData = " + deviceUploadingData);
    }
}
