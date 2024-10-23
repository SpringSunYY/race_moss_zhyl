package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.AppointmentDisposeMapper;
import com.moss.zhyl.domain.AppointmentDispose;
import com.moss.zhyl.service.IAppointmentDisposeService;

/**
 * 预约处理Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class AppointmentDisposeServiceImpl implements IAppointmentDisposeService 
{
    @Autowired
    private AppointmentDisposeMapper appointmentDisposeMapper;

    /**
     * 查询预约处理
     * 
     * @param disposeId 预约处理主键
     * @return 预约处理
     */
    @Override
    public AppointmentDispose selectAppointmentDisposeByDisposeId(Long disposeId)
    {
        return appointmentDisposeMapper.selectAppointmentDisposeByDisposeId(disposeId);
    }

    /**
     * 查询预约处理列表
     * 
     * @param appointmentDispose 预约处理
     * @return 预约处理
     */
    @Override
    public List<AppointmentDispose> selectAppointmentDisposeList(AppointmentDispose appointmentDispose)
    {
        return appointmentDisposeMapper.selectAppointmentDisposeList(appointmentDispose);
    }

    /**
     * 新增预约处理
     * 
     * @param appointmentDispose 预约处理
     * @return 结果
     */
    @Override
    public int insertAppointmentDispose(AppointmentDispose appointmentDispose)
    {
        appointmentDispose.setCreateTime(DateUtils.getNowDate());
        return appointmentDisposeMapper.insertAppointmentDispose(appointmentDispose);
    }

    /**
     * 修改预约处理
     * 
     * @param appointmentDispose 预约处理
     * @return 结果
     */
    @Override
    public int updateAppointmentDispose(AppointmentDispose appointmentDispose)
    {
        return appointmentDisposeMapper.updateAppointmentDispose(appointmentDispose);
    }

    /**
     * 批量删除预约处理
     * 
     * @param disposeIds 需要删除的预约处理主键
     * @return 结果
     */
    @Override
    public int deleteAppointmentDisposeByDisposeIds(Long[] disposeIds)
    {
        return appointmentDisposeMapper.deleteAppointmentDisposeByDisposeIds(disposeIds);
    }

    /**
     * 删除预约处理信息
     * 
     * @param disposeId 预约处理主键
     * @return 结果
     */
    @Override
    public int deleteAppointmentDisposeByDisposeId(Long disposeId)
    {
        return appointmentDisposeMapper.deleteAppointmentDisposeByDisposeId(disposeId);
    }
}
