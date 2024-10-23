package com.moss.zhyl.service;

import java.util.List;
import com.moss.zhyl.domain.AddressInfo;

/**
 * 地址信息Service接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface IAddressInfoService 
{
    /**
     * 查询地址信息
     * 
     * @param addressId 地址信息主键
     * @return 地址信息
     */
    public AddressInfo selectAddressInfoByAddressId(Long addressId);

    /**
     * 查询地址信息列表
     * 
     * @param addressInfo 地址信息
     * @return 地址信息集合
     */
    public List<AddressInfo> selectAddressInfoList(AddressInfo addressInfo);

    /**
     * 新增地址信息
     * 
     * @param addressInfo 地址信息
     * @return 结果
     */
    public int insertAddressInfo(AddressInfo addressInfo);

    /**
     * 修改地址信息
     * 
     * @param addressInfo 地址信息
     * @return 结果
     */
    public int updateAddressInfo(AddressInfo addressInfo);

    /**
     * 批量删除地址信息
     * 
     * @param addressIds 需要删除的地址信息主键集合
     * @return 结果
     */
    public int deleteAddressInfoByAddressIds(Long[] addressIds);

    /**
     * 删除地址信息信息
     * 
     * @param addressId 地址信息主键
     * @return 结果
     */
    public int deleteAddressInfoByAddressId(Long addressId);
}
