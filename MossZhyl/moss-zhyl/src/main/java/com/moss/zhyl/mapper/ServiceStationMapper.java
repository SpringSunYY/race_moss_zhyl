package com.moss.zhyl.mapper;

import java.util.List;
import com.moss.zhyl.domain.ServiceStation;

/**
 * 服务站信息Mapper接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface ServiceStationMapper 
{
    /**
     * 查询服务站信息
     * 
     * @param serviceStationId 服务站信息主键
     * @return 服务站信息
     */
    public ServiceStation selectServiceStationByServiceStationId(Long serviceStationId);

    /**
     * 查询服务站信息列表
     * 
     * @param serviceStation 服务站信息
     * @return 服务站信息集合
     */
    public List<ServiceStation> selectServiceStationList(ServiceStation serviceStation);

    /**
     * 新增服务站信息
     * 
     * @param serviceStation 服务站信息
     * @return 结果
     */
    public int insertServiceStation(ServiceStation serviceStation);

    /**
     * 修改服务站信息
     * 
     * @param serviceStation 服务站信息
     * @return 结果
     */
    public int updateServiceStation(ServiceStation serviceStation);

    /**
     * 删除服务站信息
     * 
     * @param serviceStationId 服务站信息主键
     * @return 结果
     */
    public int deleteServiceStationByServiceStationId(Long serviceStationId);

    /**
     * 批量删除服务站信息
     * 
     * @param serviceStationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServiceStationByServiceStationIds(Long[] serviceStationIds);
}
