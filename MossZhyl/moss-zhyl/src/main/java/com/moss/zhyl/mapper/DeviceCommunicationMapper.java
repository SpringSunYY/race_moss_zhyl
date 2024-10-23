package com.moss.zhyl.mapper;

import java.util.List;
import com.moss.zhyl.domain.DeviceCommunication;

/**
 * 设备通信记录Mapper接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface DeviceCommunicationMapper 
{
    /**
     * 查询设备通信记录
     * 
     * @param deviceCommunicationId 设备通信记录主键
     * @return 设备通信记录
     */
    public DeviceCommunication selectDeviceCommunicationByDeviceCommunicationId(Long deviceCommunicationId);

    /**
     * 查询设备通信记录列表
     * 
     * @param deviceCommunication 设备通信记录
     * @return 设备通信记录集合
     */
    public List<DeviceCommunication> selectDeviceCommunicationList(DeviceCommunication deviceCommunication);

    /**
     * 新增设备通信记录
     * 
     * @param deviceCommunication 设备通信记录
     * @return 结果
     */
    public int insertDeviceCommunication(DeviceCommunication deviceCommunication);

    /**
     * 修改设备通信记录
     * 
     * @param deviceCommunication 设备通信记录
     * @return 结果
     */
    public int updateDeviceCommunication(DeviceCommunication deviceCommunication);

    /**
     * 删除设备通信记录
     * 
     * @param deviceCommunicationId 设备通信记录主键
     * @return 结果
     */
    public int deleteDeviceCommunicationByDeviceCommunicationId(Long deviceCommunicationId);

    /**
     * 批量删除设备通信记录
     * 
     * @param deviceCommunicationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceCommunicationByDeviceCommunicationIds(Long[] deviceCommunicationIds);
}
