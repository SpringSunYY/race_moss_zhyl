package com.moss.zhyl;

import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.zhyl.domain.statics.ro.StaticRo;
import com.moss.zhyl.mapper.DeviceUploadingDataMapper;
import com.moss.zhyl.mapper.ElderlyDeviceBindingMapper;
import com.moss.zhyl.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl
 * Author: YY
 * CreateTime: 2024-11-02  15:03
 * Description: StaticsTest
 * Version: 1.0
 */
@SpringBootTest
public class StaticsTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private DeviceUploadingDataMapper deviceUploadingDataMapper;

    @Autowired
    private ElderlyDeviceBindingMapper elderlyDeviceBindingMapper;

    @Test
    public void userinfoStaticByCreateTimeCounts() {
        UserInfo userInfo = new UserInfo();
        userInfo.setDelFlag("0");
        userInfo.setUserInfoRole("elderly");
        Map<String, Object> params = new HashMap<>();
        params.put("beginCreateTime", "2024-10-29");
        params.put("endCreateTime", "2024-11-30");
        userInfo.setParams(params);
        List<StaticRo> staticRos = userInfoMapper.staticByCreateTimeCounts(userInfo);
        for (StaticRo staticRo : staticRos) {
            System.err.println(staticRo);
        }
    }

    @Test
    public void uploadingDateStaticByCreateTimeCounts() {
        DeviceUploadingData deviceUploadingData = new DeviceUploadingData();
        deviceUploadingData.setCommand("warning");
        deviceUploadingData.setDelFlag("0");
        Map<String, Object> params = new HashMap<>();
        params.put("beginCreateTime", "2024-09-29");
        params.put("endCreateTime", "2024-10-30");
        deviceUploadingData.setParams(params);
        List<StaticRo> staticRos = deviceUploadingDataMapper.staticByCreateTimeCounts(deviceUploadingData);
        for (StaticRo staticRo : staticRos) {
            System.err.println(staticRo);
        }
    }

    @Test
    public void bindingStaticByCreateTimeCounts() {
        ElderlyDeviceBinding elderlyDeviceBinding = new ElderlyDeviceBinding();
        elderlyDeviceBinding.setDelFlag("0");
        Map<String, Object> params = new HashMap<>();
        params.put("beginCreateTime", "2024-09-29");
        params.put("endCreateTime", "2024-10-30");
        elderlyDeviceBinding.setParams(params);
        List<StaticRo> staticRos = elderlyDeviceBindingMapper.staticByCreateTimeCounts(elderlyDeviceBinding);
        for (StaticRo staticRo : staticRos) {
            System.err.println(staticRo);
        }
    }
}
