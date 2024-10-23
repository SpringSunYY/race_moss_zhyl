package com.moss.zhyl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.DeviceBrandMapper;
import com.moss.zhyl.domain.DeviceBrand;
import com.moss.zhyl.service.IDeviceBrandService;

/**
 * 设备品牌Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class DeviceBrandServiceImpl implements IDeviceBrandService 
{
    @Autowired
    private DeviceBrandMapper deviceBrandMapper;

    /**
     * 查询设备品牌
     * 
     * @param brandId 设备品牌主键
     * @return 设备品牌
     */
    @Override
    public DeviceBrand selectDeviceBrandByBrandId(Long brandId)
    {
        return deviceBrandMapper.selectDeviceBrandByBrandId(brandId);
    }

    /**
     * 查询设备品牌列表
     * 
     * @param deviceBrand 设备品牌
     * @return 设备品牌
     */
    @Override
    public List<DeviceBrand> selectDeviceBrandList(DeviceBrand deviceBrand)
    {
        return deviceBrandMapper.selectDeviceBrandList(deviceBrand);
    }

    /**
     * 新增设备品牌
     * 
     * @param deviceBrand 设备品牌
     * @return 结果
     */
    @Override
    public int insertDeviceBrand(DeviceBrand deviceBrand)
    {
        return deviceBrandMapper.insertDeviceBrand(deviceBrand);
    }

    /**
     * 修改设备品牌
     * 
     * @param deviceBrand 设备品牌
     * @return 结果
     */
    @Override
    public int updateDeviceBrand(DeviceBrand deviceBrand)
    {
        return deviceBrandMapper.updateDeviceBrand(deviceBrand);
    }

    /**
     * 批量删除设备品牌
     * 
     * @param brandIds 需要删除的设备品牌主键
     * @return 结果
     */
    @Override
    public int deleteDeviceBrandByBrandIds(Long[] brandIds)
    {
        return deviceBrandMapper.deleteDeviceBrandByBrandIds(brandIds);
    }

    /**
     * 删除设备品牌信息
     * 
     * @param brandId 设备品牌主键
     * @return 结果
     */
    @Override
    public int deleteDeviceBrandByBrandId(Long brandId)
    {
        return deviceBrandMapper.deleteDeviceBrandByBrandId(brandId);
    }
}
