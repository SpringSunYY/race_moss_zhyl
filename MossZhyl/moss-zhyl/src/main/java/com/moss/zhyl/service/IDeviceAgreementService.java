package com.moss.zhyl.service;

import java.util.List;
import com.moss.zhyl.domain.DeviceAgreement;

/**
 * 设备协议Service接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface IDeviceAgreementService 
{
    /**
     * 查询设备协议
     * 
     * @param agreementId 设备协议主键
     * @return 设备协议
     */
    public DeviceAgreement selectDeviceAgreementByAgreementId(Long agreementId);

    /**
     * 查询设备协议列表
     * 
     * @param deviceAgreement 设备协议
     * @return 设备协议集合
     */
    public List<DeviceAgreement> selectDeviceAgreementList(DeviceAgreement deviceAgreement);

    /**
     * 新增设备协议
     * 
     * @param deviceAgreement 设备协议
     * @return 结果
     */
    public int insertDeviceAgreement(DeviceAgreement deviceAgreement);

    /**
     * 修改设备协议
     * 
     * @param deviceAgreement 设备协议
     * @return 结果
     */
    public int updateDeviceAgreement(DeviceAgreement deviceAgreement);

    /**
     * 批量删除设备协议
     * 
     * @param agreementIds 需要删除的设备协议主键集合
     * @return 结果
     */
    public int deleteDeviceAgreementByAgreementIds(Long[] agreementIds);

    /**
     * 删除设备协议信息
     * 
     * @param agreementId 设备协议主键
     * @return 结果
     */
    public int deleteDeviceAgreementByAgreementId(Long agreementId);
}
