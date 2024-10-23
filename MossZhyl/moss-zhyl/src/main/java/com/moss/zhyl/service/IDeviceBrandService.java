package com.moss.zhyl.service;

import java.util.List;
import com.moss.zhyl.domain.DeviceBrand;

/**
 * 设备品牌Service接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface IDeviceBrandService 
{
    /**
     * 查询设备品牌
     * 
     * @param brandId 设备品牌主键
     * @return 设备品牌
     */
    public DeviceBrand selectDeviceBrandByBrandId(Long brandId);

    /**
     * 查询设备品牌列表
     * 
     * @param deviceBrand 设备品牌
     * @return 设备品牌集合
     */
    public List<DeviceBrand> selectDeviceBrandList(DeviceBrand deviceBrand);

    /**
     * 新增设备品牌
     * 
     * @param deviceBrand 设备品牌
     * @return 结果
     */
    public int insertDeviceBrand(DeviceBrand deviceBrand);

    /**
     * 修改设备品牌
     * 
     * @param deviceBrand 设备品牌
     * @return 结果
     */
    public int updateDeviceBrand(DeviceBrand deviceBrand);

    /**
     * 批量删除设备品牌
     * 
     * @param brandIds 需要删除的设备品牌主键集合
     * @return 结果
     */
    public int deleteDeviceBrandByBrandIds(Long[] brandIds);

    /**
     * 删除设备品牌信息
     * 
     * @param brandId 设备品牌主键
     * @return 结果
     */
    public int deleteDeviceBrandByBrandId(Long brandId);
}
