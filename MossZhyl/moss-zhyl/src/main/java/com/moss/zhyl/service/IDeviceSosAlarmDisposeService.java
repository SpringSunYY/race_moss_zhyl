package com.moss.zhyl.service;

import java.util.List;
import com.moss.zhyl.domain.DeviceSosAlarmDispose;

/**
 * 设备预警处理Service接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface IDeviceSosAlarmDisposeService 
{
    /**
     * 查询设备预警处理
     * 
     * @param disposeId 设备预警处理主键
     * @return 设备预警处理
     */
    public DeviceSosAlarmDispose selectDeviceSosAlarmDisposeByDisposeId(Long disposeId);

    /**
     * 查询设备预警处理列表
     * 
     * @param deviceSosAlarmDispose 设备预警处理
     * @return 设备预警处理集合
     */
    public List<DeviceSosAlarmDispose> selectDeviceSosAlarmDisposeList(DeviceSosAlarmDispose deviceSosAlarmDispose);

    /**
     * 新增设备预警处理
     * 
     * @param deviceSosAlarmDispose 设备预警处理
     * @return 结果
     */
    public int insertDeviceSosAlarmDispose(DeviceSosAlarmDispose deviceSosAlarmDispose);

    /**
     * 修改设备预警处理
     * 
     * @param deviceSosAlarmDispose 设备预警处理
     * @return 结果
     */
    public int updateDeviceSosAlarmDispose(DeviceSosAlarmDispose deviceSosAlarmDispose);

    /**
     * 批量删除设备预警处理
     * 
     * @param disposeIds 需要删除的设备预警处理主键集合
     * @return 结果
     */
    public int deleteDeviceSosAlarmDisposeByDisposeIds(Long[] disposeIds);

    /**
     * 删除设备预警处理信息
     * 
     * @param disposeId 设备预警处理主键
     * @return 结果
     */
    public int deleteDeviceSosAlarmDisposeByDisposeId(Long disposeId);
}
