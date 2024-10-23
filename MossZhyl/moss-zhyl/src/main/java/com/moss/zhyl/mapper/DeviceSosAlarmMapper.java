package com.moss.zhyl.mapper;

import java.util.List;
import com.moss.zhyl.domain.DeviceSosAlarm;

/**
 * 设备预警Mapper接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface DeviceSosAlarmMapper 
{
    /**
     * 查询设备预警
     * 
     * @param sosAlarmId 设备预警主键
     * @return 设备预警
     */
    public DeviceSosAlarm selectDeviceSosAlarmBySosAlarmId(Long sosAlarmId);

    /**
     * 查询设备预警列表
     * 
     * @param deviceSosAlarm 设备预警
     * @return 设备预警集合
     */
    public List<DeviceSosAlarm> selectDeviceSosAlarmList(DeviceSosAlarm deviceSosAlarm);

    /**
     * 新增设备预警
     * 
     * @param deviceSosAlarm 设备预警
     * @return 结果
     */
    public int insertDeviceSosAlarm(DeviceSosAlarm deviceSosAlarm);

    /**
     * 修改设备预警
     * 
     * @param deviceSosAlarm 设备预警
     * @return 结果
     */
    public int updateDeviceSosAlarm(DeviceSosAlarm deviceSosAlarm);

    /**
     * 删除设备预警
     * 
     * @param sosAlarmId 设备预警主键
     * @return 结果
     */
    public int deleteDeviceSosAlarmBySosAlarmId(Long sosAlarmId);

    /**
     * 批量删除设备预警
     * 
     * @param sosAlarmIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceSosAlarmBySosAlarmIds(Long[] sosAlarmIds);
}
