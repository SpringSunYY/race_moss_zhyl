package com.moss.zhyl.mapper;

import java.util.List;
import com.moss.zhyl.domain.Appointment;

/**
 * 预约Mapper接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface AppointmentMapper 
{
    /**
     * 查询预约
     * 
     * @param appointmentId 预约主键
     * @return 预约
     */
    public Appointment selectAppointmentByAppointmentId(Long appointmentId);

    /**
     * 查询预约列表
     * 
     * @param appointment 预约
     * @return 预约集合
     */
    public List<Appointment> selectAppointmentList(Appointment appointment);

    /**
     * 新增预约
     * 
     * @param appointment 预约
     * @return 结果
     */
    public int insertAppointment(Appointment appointment);

    /**
     * 修改预约
     * 
     * @param appointment 预约
     * @return 结果
     */
    public int updateAppointment(Appointment appointment);

    /**
     * 删除预约
     * 
     * @param appointmentId 预约主键
     * @return 结果
     */
    public int deleteAppointmentByAppointmentId(Long appointmentId);

    /**
     * 批量删除预约
     * 
     * @param appointmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppointmentByAppointmentIds(Long[] appointmentIds);
}
