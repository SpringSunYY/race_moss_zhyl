package com.moss.zhyl.mapper;

import java.util.List;
import com.moss.zhyl.domain.AppointmentDispose;

/**
 * 预约处理Mapper接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface AppointmentDisposeMapper 
{
    /**
     * 查询预约处理
     * 
     * @param disposeId 预约处理主键
     * @return 预约处理
     */
    public AppointmentDispose selectAppointmentDisposeByDisposeId(Long disposeId);

    /**
     * 查询预约处理列表
     * 
     * @param appointmentDispose 预约处理
     * @return 预约处理集合
     */
    public List<AppointmentDispose> selectAppointmentDisposeList(AppointmentDispose appointmentDispose);

    /**
     * 新增预约处理
     * 
     * @param appointmentDispose 预约处理
     * @return 结果
     */
    public int insertAppointmentDispose(AppointmentDispose appointmentDispose);

    /**
     * 修改预约处理
     * 
     * @param appointmentDispose 预约处理
     * @return 结果
     */
    public int updateAppointmentDispose(AppointmentDispose appointmentDispose);

    /**
     * 删除预约处理
     * 
     * @param disposeId 预约处理主键
     * @return 结果
     */
    public int deleteAppointmentDisposeByDisposeId(Long disposeId);

    /**
     * 批量删除预约处理
     * 
     * @param disposeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppointmentDisposeByDisposeIds(Long[] disposeIds);
}
