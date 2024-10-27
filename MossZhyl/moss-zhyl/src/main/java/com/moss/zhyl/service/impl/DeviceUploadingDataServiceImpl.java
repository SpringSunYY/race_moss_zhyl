package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
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
public class DeviceUploadingDataServiceImpl implements IDeviceUploadingDataService 
{
    @Autowired
    private DeviceUploadingDataMapper deviceUploadingDataMapper;

    /**
     * 查询设备上传数据
     * 
     * @param dataId 设备上传数据主键
     * @return 设备上传数据
     */
    @Override
    public DeviceUploadingData selectDeviceUploadingDataByDataId(Long dataId)
    {
        return deviceUploadingDataMapper.selectDeviceUploadingDataByDataId(dataId);
    }

    /**
     * 查询设备上传数据列表
     * 
     * @param deviceUploadingData 设备上传数据
     * @return 设备上传数据
     */
    @Override
    public List<DeviceUploadingData> selectDeviceUploadingDataList(DeviceUploadingData deviceUploadingData)
    {
        return deviceUploadingDataMapper.selectDeviceUploadingDataList(deviceUploadingData);
    }

    /**
     * 新增设备上传数据
     * 
     * @param deviceUploadingData 设备上传数据
     * @return 结果
     */
    @Override
    public int insertDeviceUploadingData(DeviceUploadingData deviceUploadingData)
    {
        deviceUploadingData.setCreateTime(DateUtils.getNowDate());
        return deviceUploadingDataMapper.insertDeviceUploadingData(deviceUploadingData);
    }

    /**
     * 修改设备上传数据
     * 
     * @param deviceUploadingData 设备上传数据
     * @return 结果
     */
    @Override
    public int updateDeviceUploadingData(DeviceUploadingData deviceUploadingData)
    {
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
    public int deleteDeviceUploadingDataByDataIds(Long[] dataIds)
    {
        return deviceUploadingDataMapper.deleteDeviceUploadingDataByDataIds(dataIds);
    }

    /**
     * 删除设备上传数据信息
     * 
     * @param dataId 设备上传数据主键
     * @return 结果
     */
    @Override
    public int deleteDeviceUploadingDataByDataId(Long dataId)
    {
        return deviceUploadingDataMapper.deleteDeviceUploadingDataByDataId(dataId);
    }
}
