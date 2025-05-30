package com.moss.zhyl.mapper;

import java.util.List;

import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.statics.ro.StaticRo;

/**
 * 设备上传数据Mapper接口
 *
 * @author YY
 * @date 2024-10-26
 */
public interface DeviceUploadingDataMapper {
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
     * 删除设备上传数据
     *
     * @param dataId 设备上传数据主键
     * @return 结果
     */
    public int deleteDeviceUploadingDataByDataId(Long dataId);

    /**
     * 批量删除设备上传数据
     *
     * @param dataIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceUploadingDataByDataIds(Long[] dataIds);

    /**
     * @description: 时间范围查询精确为天
     * @author: YY
     * @method: selectDeviceUploadingDataListByDays
     * @date: 2024/10/31 15:53
     * @param:
     * @param: deviceUploadingData
     * @return: java.util.List<com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData>
     **/
    List<DeviceUploadingData> selectDeviceUploadingDataListByDays(DeviceUploadingData deviceUploadingData);

    /**
     * description:  查询数据上传总数
     * author: YY
     * method: selectDeviceUploadingDataCount
     * date: 2024/11/1 22:49
     * param:
     *
     * @param: deviceUploadingData
     * return: java.lang.Long
     **/
    Long selectDeviceUploadingDataCount(DeviceUploadingData deviceUploadingData);

    /**
     * description: 根据创建时间查询每日创建总数
     * author: YY
     * method: staticByCreateTimeCounts
     * date: 2024/11/2 14:57
     * param:
     *
     * @param: deviceUploadingData
     * return: java.util.List<com.moss.zhyl.domain.statics.ro.StaticRo>
     **/
    List<StaticRo> staticByCreateTimeCounts(DeviceUploadingData deviceUploadingData);

    /**
     * description: 或许最新一条数据
     * author: YY
     * method: selectNewData
     * date: 2024/11/11 15:13
     * param:
     *
     * @param: deviceUploadingData
     * return: com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData
     **/
    DeviceUploadingData selectNewData(DeviceUploadingData deviceUploadingData);
}
