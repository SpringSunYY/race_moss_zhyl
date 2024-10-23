package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.DeviceSosAlarmDisposeMapper;
import com.moss.zhyl.domain.DeviceSosAlarmDispose;
import com.moss.zhyl.service.IDeviceSosAlarmDisposeService;

/**
 * 设备预警处理Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class DeviceSosAlarmDisposeServiceImpl implements IDeviceSosAlarmDisposeService 
{
    @Autowired
    private DeviceSosAlarmDisposeMapper deviceSosAlarmDisposeMapper;

    /**
     * 查询设备预警处理
     * 
     * @param disposeId 设备预警处理主键
     * @return 设备预警处理
     */
    @Override
    public DeviceSosAlarmDispose selectDeviceSosAlarmDisposeByDisposeId(Long disposeId)
    {
        return deviceSosAlarmDisposeMapper.selectDeviceSosAlarmDisposeByDisposeId(disposeId);
    }

    /**
     * 查询设备预警处理列表
     * 
     * @param deviceSosAlarmDispose 设备预警处理
     * @return 设备预警处理
     */
    @Override
    public List<DeviceSosAlarmDispose> selectDeviceSosAlarmDisposeList(DeviceSosAlarmDispose deviceSosAlarmDispose)
    {
        return deviceSosAlarmDisposeMapper.selectDeviceSosAlarmDisposeList(deviceSosAlarmDispose);
    }

    /**
     * 新增设备预警处理
     * 
     * @param deviceSosAlarmDispose 设备预警处理
     * @return 结果
     */
    @Override
    public int insertDeviceSosAlarmDispose(DeviceSosAlarmDispose deviceSosAlarmDispose)
    {
        deviceSosAlarmDispose.setCreateTime(DateUtils.getNowDate());
        return deviceSosAlarmDisposeMapper.insertDeviceSosAlarmDispose(deviceSosAlarmDispose);
    }

    /**
     * 修改设备预警处理
     * 
     * @param deviceSosAlarmDispose 设备预警处理
     * @return 结果
     */
    @Override
    public int updateDeviceSosAlarmDispose(DeviceSosAlarmDispose deviceSosAlarmDispose)
    {
        return deviceSosAlarmDisposeMapper.updateDeviceSosAlarmDispose(deviceSosAlarmDispose);
    }

    /**
     * 批量删除设备预警处理
     * 
     * @param disposeIds 需要删除的设备预警处理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceSosAlarmDisposeByDisposeIds(Long[] disposeIds)
    {
        return deviceSosAlarmDisposeMapper.deleteDeviceSosAlarmDisposeByDisposeIds(disposeIds);
    }

    /**
     * 删除设备预警处理信息
     * 
     * @param disposeId 设备预警处理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceSosAlarmDisposeByDisposeId(Long disposeId)
    {
        return deviceSosAlarmDisposeMapper.deleteDeviceSosAlarmDisposeByDisposeId(disposeId);
    }
}
