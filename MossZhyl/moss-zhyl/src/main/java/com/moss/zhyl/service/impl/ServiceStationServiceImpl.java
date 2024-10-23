package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.ServiceStationMapper;
import com.moss.zhyl.domain.ServiceStation;
import com.moss.zhyl.service.IServiceStationService;

/**
 * 服务站信息Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class ServiceStationServiceImpl implements IServiceStationService 
{
    @Autowired
    private ServiceStationMapper serviceStationMapper;

    /**
     * 查询服务站信息
     * 
     * @param serviceStationId 服务站信息主键
     * @return 服务站信息
     */
    @Override
    public ServiceStation selectServiceStationByServiceStationId(Long serviceStationId)
    {
        return serviceStationMapper.selectServiceStationByServiceStationId(serviceStationId);
    }

    /**
     * 查询服务站信息列表
     * 
     * @param serviceStation 服务站信息
     * @return 服务站信息
     */
    @Override
    public List<ServiceStation> selectServiceStationList(ServiceStation serviceStation)
    {
        return serviceStationMapper.selectServiceStationList(serviceStation);
    }

    /**
     * 新增服务站信息
     * 
     * @param serviceStation 服务站信息
     * @return 结果
     */
    @Override
    public int insertServiceStation(ServiceStation serviceStation)
    {
        serviceStation.setCreateTime(DateUtils.getNowDate());
        return serviceStationMapper.insertServiceStation(serviceStation);
    }

    /**
     * 修改服务站信息
     * 
     * @param serviceStation 服务站信息
     * @return 结果
     */
    @Override
    public int updateServiceStation(ServiceStation serviceStation)
    {
        serviceStation.setUpdateTime(DateUtils.getNowDate());
        return serviceStationMapper.updateServiceStation(serviceStation);
    }

    /**
     * 批量删除服务站信息
     * 
     * @param serviceStationIds 需要删除的服务站信息主键
     * @return 结果
     */
    @Override
    public int deleteServiceStationByServiceStationIds(Long[] serviceStationIds)
    {
        return serviceStationMapper.deleteServiceStationByServiceStationIds(serviceStationIds);
    }

    /**
     * 删除服务站信息信息
     * 
     * @param serviceStationId 服务站信息主键
     * @return 结果
     */
    @Override
    public int deleteServiceStationByServiceStationId(Long serviceStationId)
    {
        return serviceStationMapper.deleteServiceStationByServiceStationId(serviceStationId);
    }
}
