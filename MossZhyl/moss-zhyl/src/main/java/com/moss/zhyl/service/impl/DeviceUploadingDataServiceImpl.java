package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.exception.ServiceException;
import com.moss.common.utils.DateUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.zhyl.domain.DeviceBrand;
import com.moss.zhyl.domain.DeviceUploadingData.Argument;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.service.IElderlyDeviceBindingService;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.DeviceUploadingDataMapper;
import com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.service.IDeviceUploadingDataService;

/**
 * 设备上传数据Service业务层处理
 *
 * @author YY
 * @date 2024-10-26
 */
@Service
public class DeviceUploadingDataServiceImpl implements IDeviceUploadingDataService {
    @Autowired
    private DeviceUploadingDataMapper deviceUploadingDataMapper;

    @Autowired
    private IElderlyDeviceBindingService elderlyDeviceBindingService;

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 查询设备上传数据
     *
     * @param dataId 设备上传数据主键
     * @return 设备上传数据
     */
    @Override
    public DeviceUploadingData selectDeviceUploadingDataByDataId(Long dataId) {
        DeviceUploadingData deviceUploadingData = deviceUploadingDataMapper.selectDeviceUploadingDataByDataId(dataId);
        Argument.returnArgumentData(deviceUploadingData);
        return deviceUploadingData;
    }

    /**
     * 查询设备上传数据列表
     *
     * @param deviceUploadingData 设备上传数据
     * @return 设备上传数据
     */
    @Override
    public List<DeviceUploadingData> selectDeviceUploadingDataList(DeviceUploadingData deviceUploadingData) {
        List<DeviceUploadingData> uploadingDataList = deviceUploadingDataMapper.selectDeviceUploadingDataList(deviceUploadingData);
        for (DeviceUploadingData info : uploadingDataList) {
            UserInfo userInfo = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(info.getUserInfoId());
            if (StringUtils.isNotNull(userInfo)) {
                info.setUserInfoName(userInfo.getUserInfoName());
            }
        }
        return uploadingDataList;
    }

    /**
     * 新增设备上传数据
     *
     * @param deviceUploadingData 设备上传数据
     * @return 结果
     */
    @Override
    public int insertDeviceUploadingData(DeviceUploadingData deviceUploadingData) {
        //根据设备查询老者信息
        ElderlyDeviceBinding binding = elderlyDeviceBindingService.selectElderlyDeviceBindingByDeviceIMEI(deviceUploadingData.getDeviceId());
        if (StringUtils.isNull(binding)) {
            throw new ServiceException("此设备不存在！！！");
        }
        deviceUploadingData.setUserInfoId(binding.getUserInfoId());

        deviceUploadingData.setDataId(IdUtils.snowflakeId());
        deviceUploadingData.setCreateTime(DateUtils.getNowDate());
        deviceUploadingData.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        return deviceUploadingDataMapper.insertDeviceUploadingData(deviceUploadingData);
    }

    /**
     * 修改设备上传数据
     *
     * @param deviceUploadingData 设备上传数据
     * @return 结果
     */
    @Override
    public int updateDeviceUploadingData(DeviceUploadingData deviceUploadingData) {
        deviceUploadingData.setUpdateTime(DateUtils.getNowDate());
        return deviceUploadingDataMapper.updateDeviceUploadingData(deviceUploadingData);
    }

    /**
     * 批量删除设备上传数据
     *
     * @param dataIds 需要删除的设备上传数据主键
     * @return 结果
     */
    @Override
    public int deleteDeviceUploadingDataByDataIds(Long[] dataIds) {
        return deviceUploadingDataMapper.deleteDeviceUploadingDataByDataIds(dataIds);
    }

    /**
     * 删除设备上传数据信息
     *
     * @param dataId 设备上传数据主键
     * @return 结果
     */
    @Override
    public int deleteDeviceUploadingDataByDataId(Long dataId) {
        return deviceUploadingDataMapper.deleteDeviceUploadingDataByDataId(dataId);
    }
}
