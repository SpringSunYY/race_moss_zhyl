package com.moss.zhyl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.DeviceSosAlarmMapper;
import com.moss.zhyl.domain.DeviceSosAlarm;
import com.moss.zhyl.service.IDeviceSosAlarmService;

/**
 * 设备预警Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class DeviceSosAlarmServiceImpl implements IDeviceSosAlarmService 
{
    @Autowired
    private DeviceSosAlarmMapper deviceSosAlarmMapper;

    /**
     * 查询设备预警
     * 
     * @param sosAlarmId 设备预警主键
     * @return 设备预警
     */
    @Override
    public DeviceSosAlarm selectDeviceSosAlarmBySosAlarmId(Long sosAlarmId)
    {
        return deviceSosAlarmMapper.selectDeviceSosAlarmBySosAlarmId(sosAlarmId);
    }

    /**
     * 查询设备预警列表
     * 
     * @param deviceSosAlarm 设备预警
     * @return 设备预警
     */
    @Override
    public List<DeviceSosAlarm> selectDeviceSosAlarmList(DeviceSosAlarm deviceSosAlarm)
    {
        return deviceSosAlarmMapper.selectDeviceSosAlarmList(deviceSosAlarm);
    }

    /**
     * 新增设备预警
     * 
     * @param deviceSosAlarm 设备预警
     * @return 结果
     */
    @Override
    public int insertDeviceSosAlarm(DeviceSosAlarm deviceSosAlarm)
    {
        return deviceSosAlarmMapper.insertDeviceSosAlarm(deviceSosAlarm);
    }

    /**
     * 修改设备预警
     * 
     * @param deviceSosAlarm 设备预警
     * @return 结果
     */
    @Override
    public int updateDeviceSosAlarm(DeviceSosAlarm deviceSosAlarm)
    {
        return deviceSosAlarmMapper.updateDeviceSosAlarm(deviceSosAlarm);
    }

    /**
     * 批量删除设备预警
     * 
     * @param sosAlarmIds 需要删除的设备预警主键
     * @return 结果
     */
    @Override
    public int deleteDeviceSosAlarmBySosAlarmIds(Long[] sosAlarmIds)
    {
        return deviceSosAlarmMapper.deleteDeviceSosAlarmBySosAlarmIds(sosAlarmIds);
    }

    /**
     * 删除设备预警信息
     * 
     * @param sosAlarmId 设备预警主键
     * @return 结果
     */
    @Override
    public int deleteDeviceSosAlarmBySosAlarmId(Long sosAlarmId)
    {
        return deviceSosAlarmMapper.deleteDeviceSosAlarmBySosAlarmId(sosAlarmId);
    }
}
