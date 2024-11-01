package com.moss.zhyl.service.impl;

import com.github.pagehelper.PageInfo;
import com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.domain.enums.DeviceUploadingDataCommandEnum;
import com.moss.zhyl.domain.enums.ProcessingStatusEnum;
import com.moss.zhyl.domain.enums.UserInfoRoleEnum;
import com.moss.zhyl.domain.statics.CountStatics;
import com.moss.zhyl.mapper.DeviceUploadingDataMapper;
import com.moss.zhyl.mapper.ElderlyDeviceBindingMapper;
import com.moss.zhyl.mapper.UserInfoMapper;
import com.moss.zhyl.service.IStaticsService;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl.service.impl
 * Author: YY
 * CreateTime: 2024-11-01  22:15
 * Description: StaticsServiceImpl
 * 统计
 * Version: 1.0
 */
@Service
public class StaticsServiceImpl implements IStaticsService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private DeviceUploadingDataMapper deviceUploadingDataMapper;
    @Autowired
    private ElderlyDeviceBindingMapper elderlyDeviceBindingMapper;

    @Override
    public CountStatics getCounts() {
        CountStatics countStatics = new CountStatics();
        UserInfo elderly = new UserInfo();
        elderly.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        elderly.setUserInfoRole(UserInfoRoleEnum.ELDERLY.getValue());
        Long elderlyCount = userInfoMapper.selectUserInfoCount(elderly);
        countStatics.setElderlyCounts(elderlyCount);

        UserInfo family = new UserInfo();
        family.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        family.setUserInfoRole(UserInfoRoleEnum.ELDERLY_FAMILY.getValue());
        Long familyCount = userInfoMapper.selectUserInfoCount(family);
        countStatics.setFamilyCounts(familyCount);

        DeviceUploadingData deviceUploadingData = new DeviceUploadingData();
        deviceUploadingData.setCommand(DeviceUploadingDataCommandEnum.DEVICE_UPLOADING_DATA_COMMAND_WARNING.getValue());
        deviceUploadingData.setProcessingStatus(ProcessingStatusEnum.PROCESSING_STATUS_ENUM_0.getValue());
        deviceUploadingData.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        Long sosCounts=deviceUploadingDataMapper.selectDeviceUploadingDataCount(deviceUploadingData);
        countStatics.setSosCounts(sosCounts);

        ElderlyDeviceBinding binding = new ElderlyDeviceBinding();
        binding.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        Long deviceCounts=elderlyDeviceBindingMapper.selectElderlyDeviceBindingCounts(binding);
        countStatics.setDeviceCounts(deviceCounts);
        return countStatics;
    }
}
