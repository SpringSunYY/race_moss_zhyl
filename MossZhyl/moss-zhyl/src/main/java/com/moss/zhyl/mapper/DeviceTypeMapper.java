package com.moss.zhyl.mapper;

import java.util.List;

import com.moss.zhyl.domain.DeviceType;
import org.apache.ibatis.annotations.Param;

/**
 * 设备类型Mapper接口
 *
 * @author YY
 * @date 2024-10-26
 */
public interface DeviceTypeMapper {
    /**
     * 查询设备类型
     *
     * @param typeId 设备类型主键
     * @return 设备类型
     */
    public DeviceType selectDeviceTypeByTypeId(Long typeId);

    /**
     * 查询设备类型列表
     *
     * @param deviceType 设备类型
     * @return 设备类型集合
     */
    public List<DeviceType> selectDeviceTypeList(DeviceType deviceType);

    /**
     * 新增设备类型
     *
     * @param deviceType 设备类型
     * @return 结果
     */
    public int insertDeviceType(DeviceType deviceType);

    /**
     * 修改设备类型
     *
     * @param deviceType 设备类型
     * @return 结果
     */
    public int updateDeviceType(DeviceType deviceType);

    /**
     * 删除设备类型
     *
     * @param typeId 设备类型主键
     * @return 结果
     */
    public int deleteDeviceTypeByTypeId(Long typeId);

    /**
     * 批量删除设备类型
     *
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceTypeByTypeIds(Long[] typeIds);

    /**
     * @description: 根据设备类型查询是否有此类型
     * @author: YY
     * @method: selectDeviceTypeByDeviceType
     * @date: 2024/10/26 22:28
     * @param:
     * @param: deviceType
     * @return: com.moss.zhyl.domain.DeviceType
     **/
    DeviceType selectDeviceTypeByDeviceType(@Param("deviceType") String deviceType);
}
