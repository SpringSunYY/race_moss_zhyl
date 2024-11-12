package com.moss.zhyl.service.impl;

import com.moss.common.utils.CalculateUtils;
import com.moss.common.utils.StringUtils;
import com.moss.zhyl.domain.DeviceType;
import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.domain.enums.DeviceUploadingDataCommandEnum;
import com.moss.zhyl.domain.enums.ProcessingStatusEnum;
import com.moss.common.enums.UserInfoRoleEnum;
import com.moss.zhyl.domain.statics.ro.StaticRo;
import com.moss.zhyl.domain.statics.vo.BarStaticVo;
import com.moss.zhyl.domain.statics.vo.CountStaticVo;
import com.moss.zhyl.domain.statics.vo.LineStaticVo;
import com.moss.zhyl.domain.statics.vo.PieStaticVo;
import com.moss.zhyl.mapper.DeviceTypeMapper;
import com.moss.zhyl.mapper.DeviceUploadingDataMapper;
import com.moss.zhyl.mapper.ElderlyDeviceBindingMapper;
import com.moss.zhyl.mapper.UserInfoMapper;
import com.moss.zhyl.service.IStaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private DeviceTypeMapper deviceTypeMapper;


    @Override
    public CountStaticVo getCounts() {
        CountStaticVo countStatics = new CountStaticVo();
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
        Long sosCounts = deviceUploadingDataMapper.selectDeviceUploadingDataCount(deviceUploadingData);
        countStatics.setSosCounts(sosCounts);

        ElderlyDeviceBinding binding = new ElderlyDeviceBinding();
        binding.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        Long deviceCounts = elderlyDeviceBindingMapper.selectElderlyDeviceBindingCounts(binding);
        countStatics.setDeviceCounts(deviceCounts);
        return countStatics;
    }

    @Override
    public LineStaticVo getUserinfoFamilyStaticByCreateTimeCounts(UserInfo family) {
        LineStaticVo lineStaticVo = new LineStaticVo();
        List<String> names = new ArrayList<>();
        List<Long> totals = new ArrayList<>();

        //查询条件写死
        family.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        family.setUserInfoRole(UserInfoRoleEnum.ELDERLY_FAMILY.getValue());
        Map<String, Object> params = new HashMap<>();
        // TODO 时间写死
        params.put("beginCreateTime", "2024-11-08");
        params.put("endCreateTime", "2024-11-16");
        family.setParams(params);
        System.err.println(family);
        List<StaticRo> staticRos = userInfoMapper.staticByCreateTimeCounts(family);
        for (StaticRo staticRo : staticRos) {
            names.add(staticRo.getName());
            totals.add(staticRo.getTotal());
        }
        lineStaticVo.setNames(names);
        lineStaticVo.setTotals(totals);
        return lineStaticVo;
    }

    @Override
    public LineStaticVo getUserinfoElderlyStaticByCreateTimeCounts(UserInfo userInfo) {
        LineStaticVo lineStaticVo = new LineStaticVo();
        List<String> names = new ArrayList<>();
        List<Long> totals = new ArrayList<>();

        //查询条件写死
        userInfo.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        userInfo.setUserInfoRole(UserInfoRoleEnum.ELDERLY.getValue());
        Map<String, Object> params = new HashMap<>();
        // TODO 时间写死
        params.put("beginCreateTime", "2024-11-07");
        params.put("endCreateTime", "2024-11-16");
        userInfo.setParams(params);
        List<StaticRo> staticRos = userInfoMapper.staticByCreateTimeCounts(userInfo);
        for (StaticRo staticRo : staticRos) {
            names.add(staticRo.getName());
            totals.add(staticRo.getTotal());
        }
        lineStaticVo.setNames(names);
        lineStaticVo.setTotals(totals);
        return lineStaticVo;
    }

    @Override
    public LineStaticVo getUploadingDateStaticByCreateTimeCounts(DeviceUploadingData deviceUploadingData) {
        LineStaticVo lineStaticVo = new LineStaticVo();
        List<String> names = new ArrayList<>();
        List<Long> totals = new ArrayList<>();

        deviceUploadingData.setCommand(DeviceUploadingDataCommandEnum.DEVICE_UPLOADING_DATA_COMMAND_WARNING.getValue());
        deviceUploadingData.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        Map<String, Object> params = new HashMap<>();
        params.put("beginCreateTime", "2024-10-24");
        params.put("endCreateTime", "2024-11-03");
        deviceUploadingData.setParams(params);
        deviceUploadingData.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        List<StaticRo> staticRos = deviceUploadingDataMapper.staticByCreateTimeCounts(deviceUploadingData);
        for (StaticRo staticRo : staticRos) {
            names.add(staticRo.getName());
            totals.add(staticRo.getTotal());
        }
        lineStaticVo.setNames(names);
        lineStaticVo.setTotals(totals);
        return lineStaticVo;
    }

    @Override
    public LineStaticVo getBindingStaticByCreateTimeCounts(ElderlyDeviceBinding elderlyDeviceBinding) {
        LineStaticVo lineStaticVo = new LineStaticVo();
        List<String> names = new ArrayList<>();
        List<Long> totals = new ArrayList<>();

        elderlyDeviceBinding.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        Map<String, Object> params = new HashMap<>();
        params.put("beginCreateTime", "2024-10-22");
        params.put("endCreateTime", "2024-10-30");
        elderlyDeviceBinding.setParams(params);
        List<StaticRo> staticRos = elderlyDeviceBindingMapper.staticByCreateTimeCounts(elderlyDeviceBinding);
        for (StaticRo staticRo : staticRos) {
            names.add(staticRo.getName());
            totals.add(staticRo.getTotal());
        }
        lineStaticVo.setNames(names);
        lineStaticVo.setTotals(totals);
        return lineStaticVo;
    }

    @Override
    public BarStaticVo getUserInfoAge(UserInfo userInfo) {
        BarStaticVo barStaticVo = new BarStaticVo();
        //先把数据赋值上
        List<String> names = new ArrayList<>();
        names.add("60以下");
        names.add("60-65");
        names.add("65-70");
        names.add("70-75");
        names.add("75-80");
        names.add("80-90");
        names.add("90-100");
        List<Long> totals = new ArrayList<>();
        totals.add(0L);
        totals.add(0L);
        totals.add(0L);
        totals.add(0L);
        totals.add(0L);
        totals.add(0L);
        totals.add(0L);
        //查询条件写死
        userInfo.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        userInfo.setUserInfoRole(UserInfoRoleEnum.ELDERLY.getValue());
        List<UserInfo> userInfos = userInfoMapper.selectUserInfoList(userInfo);
        for (UserInfo info : userInfos) {
            int age = CalculateUtils.calculateAgeByIdCard(info.getIdCard());
            if (age < 60) {
                totals.set(0, totals.get(0) + 1);
            }else if (age < 65) {
                totals.set(1, totals.get(1) + 1);
            }else if (age < 70) {
                totals.set(2, totals.get(2) + 1);
            }else if (age < 75) {
                totals.set(3, totals.get(3) + 1);
            }else if (age < 80) {
                totals.set(4, totals.get(4) + 1);
            }else if (age < 90) {
                totals.set(5, totals.get(5) + 1);
            }else if (age < 100) {
                totals.set(6, totals.get(6) + 1);
            }
        }
        barStaticVo.setTotals(totals);
        barStaticVo.setNames(names);
        return barStaticVo;
    }

    @Override
    public PieStaticVo getDeviceByDeviceTypeCount(ElderlyDeviceBinding elderlyDeviceBinding) {
        PieStaticVo staticVo = new PieStaticVo();
        List<PieStaticVo.Data> datas = new ArrayList<>();
        elderlyDeviceBinding.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        List<StaticRo> staticRos = elderlyDeviceBindingMapper.getDeviceByDeviceTypeCount(elderlyDeviceBinding);
        for (StaticRo staticRo : staticRos) {
            PieStaticVo.Data data = new PieStaticVo.Data();
            DeviceType type = deviceTypeMapper.selectDeviceTypeByDeviceType(staticRo.getName());
            if (StringUtils.isNull(type)) {
                data.setName(staticRo.getName());
            }else {
                data.setName(type.getName());
            }
            data.setValue(staticRo.getTotal());
            datas.add(data);
        }
        staticVo.setDatas(datas);
        return staticVo;
    }
}
