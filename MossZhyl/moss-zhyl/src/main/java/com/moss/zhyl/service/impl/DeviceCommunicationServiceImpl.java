package com.moss.zhyl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.DeviceCommunicationMapper;
import com.moss.zhyl.domain.DeviceCommunication;
import com.moss.zhyl.service.IDeviceCommunicationService;

/**
 * 设备通信记录Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class DeviceCommunicationServiceImpl implements IDeviceCommunicationService 
{
    @Autowired
    private DeviceCommunicationMapper deviceCommunicationMapper;

    /**
     * 查询设备通信记录
     * 
     * @param deviceCommunicationId 设备通信记录主键
     * @return 设备通信记录
     */
    @Override
    public DeviceCommunication selectDeviceCommunicationByDeviceCommunicationId(Long deviceCommunicationId)
    {
        return deviceCommunicationMapper.selectDeviceCommunicationByDeviceCommunicationId(deviceCommunicationId);
    }

    /**
     * 查询设备通信记录列表
     * 
     * @param deviceCommunication 设备通信记录
     * @return 设备通信记录
     */
    @Override
    public List<DeviceCommunication> selectDeviceCommunicationList(DeviceCommunication deviceCommunication)
    {
        return deviceCommunicationMapper.selectDeviceCommunicationList(deviceCommunication);
    }

    /**
     * 新增设备通信记录
     * 
     * @param deviceCommunication 设备通信记录
     * @return 结果
     */
    @Override
    public int insertDeviceCommunication(DeviceCommunication deviceCommunication)
    {
        return deviceCommunicationMapper.insertDeviceCommunication(deviceCommunication);
    }

    /**
     * 修改设备通信记录
     * 
     * @param deviceCommunication 设备通信记录
     * @return 结果
     */
    @Override
    public int updateDeviceCommunication(DeviceCommunication deviceCommunication)
    {
        return deviceCommunicationMapper.updateDeviceCommunication(deviceCommunication);
    }

    /**
     * 批量删除设备通信记录
     * 
     * @param deviceCommunicationIds 需要删除的设备通信记录主键
     * @return 结果
     */
    @Override
    public int deleteDeviceCommunicationByDeviceCommunicationIds(Long[] deviceCommunicationIds)
    {
        return deviceCommunicationMapper.deleteDeviceCommunicationByDeviceCommunicationIds(deviceCommunicationIds);
    }

    /**
     * 删除设备通信记录信息
     * 
     * @param deviceCommunicationId 设备通信记录主键
     * @return 结果
     */
    @Override
    public int deleteDeviceCommunicationByDeviceCommunicationId(Long deviceCommunicationId)
    {
        return deviceCommunicationMapper.deleteDeviceCommunicationByDeviceCommunicationId(deviceCommunicationId);
    }
}
