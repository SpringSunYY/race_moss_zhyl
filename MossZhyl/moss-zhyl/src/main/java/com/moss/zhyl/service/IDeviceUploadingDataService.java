package com.moss.zhyl.service;

import java.util.List;
import com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData;

/**
 * 设备上传数据Service接口
 * 
 * @author YY
 * @date 2024-10-26
 */
public interface IDeviceUploadingDataService 
{
    /**
     * 查询设备上传数据
     * 
     * @param dataId 设备上传数据主键
     * @return 设备上传数据
     */
    public DeviceUploadingData selectDeviceUploadingDataByDataId(Long dataId);

    /**
     * 查询设备上传数据列表
     * 
     * @param deviceUploadingData 设备上传数据
     * @return 设备上传数据集合
     */
    public List<DeviceUploadingData> selectDeviceUploadingDataList(DeviceUploadingData deviceUploadingData);

    /**
     * 新增设备上传数据
     * 
     * @param deviceUploadingData 设备上传数据
     * @return 结果
     */
    public int insertDeviceUploadingData(DeviceUploadingData deviceUploadingData);

    /**
     * 修改设备上传数据
     * 
     * @param deviceUploadingData 设备上传数据
     * @return 结果
     */
    public int updateDeviceUploadingData(DeviceUploadingData deviceUploadingData);

    /**
     * 批量删除设备上传数据
     * 
     * @param dataIds 需要删除的设备上传数据主键集合
     * @return 结果
     */
    public int deleteDeviceUploadingDataByDataIds(Long[] dataIds);

    /**
     * 删除设备上传数据信息
     * 
     * @param dataId 设备上传数据主键
     * @return 结果
     */
    public int deleteDeviceUploadingDataByDataId(Long dataId);
}
