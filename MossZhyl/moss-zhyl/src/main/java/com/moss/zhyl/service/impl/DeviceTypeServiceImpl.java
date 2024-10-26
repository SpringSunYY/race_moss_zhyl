package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.exception.ServiceException;
import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.DeviceTypeMapper;
import com.moss.zhyl.domain.DeviceType;
import com.moss.zhyl.service.IDeviceTypeService;

/**
 * 设备类型Service业务层处理
 *
 * @author YY
 * @date 2024-10-26
 */
@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    /**
     * 查询设备类型
     *
     * @param typeId 设备类型主键
     * @return 设备类型
     */
    @Override
    public DeviceType selectDeviceTypeByTypeId(Long typeId) {
        return deviceTypeMapper.selectDeviceTypeByTypeId(typeId);
    }

    /**
     * 查询设备类型列表
     *
     * @param deviceType 设备类型
     * @return 设备类型
     */
    @Override
    public List<DeviceType> selectDeviceTypeList(DeviceType deviceType) {
        return deviceTypeMapper.selectDeviceTypeList(deviceType);
    }

    /**
     * 新增设备类型
     *
     * @param deviceType 设备类型
     * @return 结果
     */
    @Override
    public int insertDeviceType(DeviceType deviceType) {
        DeviceType type = deviceTypeMapper.selectDeviceTypeByDeviceType(deviceType.getDeviceType());
        if (StringUtils.isNotNull(type)) {
            throw new ServiceException("该类型已经存在！！！");
        }
        deviceType.setTypeId(IdUtils.snowflakeId());
        deviceType.setCreateBy(SecurityUtils.getUsername());
        deviceType.setCreateTime(DateUtils.getNowDate());
        return deviceTypeMapper.insertDeviceType(deviceType);
    }

    /**
     * 修改设备类型
     *
     * @param deviceType 设备类型
     * @return 结果
     */
    @Override
    public int updateDeviceType(DeviceType deviceType) {
        //查询类型并且判断是否是老的
        DeviceType oldType = deviceTypeMapper.selectDeviceTypeByDeviceType(deviceType.getDeviceType());
        if (StringUtils.isNotNull(oldType) && !oldType.getDeviceType().equals(deviceType.getDeviceType())) {
            throw new ServiceException("该类型已经存在！！！");
        }
        deviceType.setUpdateTime(DateUtils.getNowDate());
        return deviceTypeMapper.updateDeviceType(deviceType);
    }

    /**
     * 批量删除设备类型
     *
     * @param typeIds 需要删除的设备类型主键
     * @return 结果
     */
    @Override
    public int deleteDeviceTypeByTypeIds(Long[] typeIds) {
        return deviceTypeMapper.deleteDeviceTypeByTypeIds(typeIds);
    }

    /**
     * 删除设备类型信息
     *
     * @param typeId 设备类型主键
     * @return 结果
     */
    @Override
    public int deleteDeviceTypeByTypeId(Long typeId) {
        return deviceTypeMapper.deleteDeviceTypeByTypeId(typeId);
    }

    @Override
    public DeviceType selectDeviceTypeByDeviceType(String deviceType) {
        return deviceTypeMapper.selectDeviceTypeByDeviceType(deviceType);
    }
}
