package com.moss.zhyl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.AddressInfoMapper;
import com.moss.zhyl.domain.AddressInfo;
import com.moss.zhyl.service.IAddressInfoService;

/**
 * 地址信息Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class AddressInfoServiceImpl implements IAddressInfoService 
{
    @Autowired
    private AddressInfoMapper addressInfoMapper;

    /**
     * 查询地址信息
     * 
     * @param addressId 地址信息主键
     * @return 地址信息
     */
    @Override
    public AddressInfo selectAddressInfoByAddressId(Long addressId)
    {
        return addressInfoMapper.selectAddressInfoByAddressId(addressId);
    }

    /**
     * 查询地址信息列表
     * 
     * @param addressInfo 地址信息
     * @return 地址信息
     */
    @Override
    public List<AddressInfo> selectAddressInfoList(AddressInfo addressInfo)
    {
        return addressInfoMapper.selectAddressInfoList(addressInfo);
    }

    /**
     * 新增地址信息
     * 
     * @param addressInfo 地址信息
     * @return 结果
     */
    @Override
    public int insertAddressInfo(AddressInfo addressInfo)
    {
        return addressInfoMapper.insertAddressInfo(addressInfo);
    }

    /**
     * 修改地址信息
     * 
     * @param addressInfo 地址信息
     * @return 结果
     */
    @Override
    public int updateAddressInfo(AddressInfo addressInfo)
    {
        return addressInfoMapper.updateAddressInfo(addressInfo);
    }

    /**
     * 批量删除地址信息
     * 
     * @param addressIds 需要删除的地址信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressInfoByAddressIds(Long[] addressIds)
    {
        return addressInfoMapper.deleteAddressInfoByAddressIds(addressIds);
    }

    /**
     * 删除地址信息信息
     * 
     * @param addressId 地址信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressInfoByAddressId(Long addressId)
    {
        return addressInfoMapper.deleteAddressInfoByAddressId(addressId);
    }
}
