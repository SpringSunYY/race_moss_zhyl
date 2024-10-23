package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.AppointmentMapper;
import com.moss.zhyl.domain.Appointment;
import com.moss.zhyl.service.IAppointmentService;

/**
 * 预约Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class AppointmentServiceImpl implements IAppointmentService 
{
    @Autowired
    private AppointmentMapper appointmentMapper;

    /**
     * 查询预约
     * 
     * @param appointmentId 预约主键
     * @return 预约
     */
    @Override
    public Appointment selectAppointmentByAppointmentId(Long appointmentId)
    {
        return appointmentMapper.selectAppointmentByAppointmentId(appointmentId);
    }

    /**
     * 查询预约列表
     * 
     * @param appointment 预约
     * @return 预约
     */
    @Override
    public List<Appointment> selectAppointmentList(Appointment appointment)
    {
        return appointmentMapper.selectAppointmentList(appointment);
    }

    /**
     * 新增预约
     * 
     * @param appointment 预约
     * @return 结果
     */
    @Override
    public int insertAppointment(Appointment appointment)
    {
        appointment.setCreateTime(DateUtils.getNowDate());
        return appointmentMapper.insertAppointment(appointment);
    }

    /**
     * 修改预约
     * 
     * @param appointment 预约
     * @return 结果
     */
    @Override
    public int updateAppointment(Appointment appointment)
    {
        return appointmentMapper.updateAppointment(appointment);
    }

    /**
     * 批量删除预约
     * 
     * @param appointmentIds 需要删除的预约主键
     * @return 结果
     */
    @Override
    public int deleteAppointmentByAppointmentIds(Long[] appointmentIds)
    {
        return appointmentMapper.deleteAppointmentByAppointmentIds(appointmentIds);
    }

    /**
     * 删除预约信息
     * 
     * @param appointmentId 预约主键
     * @return 结果
     */
    @Override
    public int deleteAppointmentByAppointmentId(Long appointmentId)
    {
        return appointmentMapper.deleteAppointmentByAppointmentId(appointmentId);
    }
}
