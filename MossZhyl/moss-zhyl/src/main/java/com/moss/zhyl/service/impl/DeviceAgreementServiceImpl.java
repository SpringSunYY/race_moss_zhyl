package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.DeviceAgreementMapper;
import com.moss.zhyl.domain.DeviceAgreement;
import com.moss.zhyl.service.IDeviceAgreementService;

/**
 * 设备协议Service业务层处理
 *
 * @author YY
 * @date 2024-10-23
 */
@Service
public class DeviceAgreementServiceImpl implements IDeviceAgreementService {
    @Autowired
    private DeviceAgreementMapper deviceAgreementMapper;

    /**
     * 查询设备协议
     *
     * @param agreementId 设备协议主键
     * @return 设备协议
     */
    @Override
    public DeviceAgreement selectDeviceAgreementByAgreementId(Long agreementId) {
        return deviceAgreementMapper.selectDeviceAgreementByAgreementId(agreementId);
    }

    /**
     * 查询设备协议列表
     *
     * @param deviceAgreement 设备协议
     * @return 设备协议
     */
    @Override
    public List<DeviceAgreement> selectDeviceAgreementList(DeviceAgreement deviceAgreement) {
        return deviceAgreementMapper.selectDeviceAgreementList(deviceAgreement);
    }

    /**
     * 新增设备协议
     *
     * @param deviceAgreement 设备协议
     * @return 结果
     */
    @Override
    public int insertDeviceAgreement(DeviceAgreement deviceAgreement) {
        deviceAgreement.setAgreementId(IdUtils.snowflakeId());
        deviceAgreement.setCreateBy(SecurityUtils.getUsername());
        deviceAgreement.setCreateTime(DateUtils.getNowDate());
        return deviceAgreementMapper.insertDeviceAgreement(deviceAgreement);
    }

    /**
     * 修改设备协议
     *
     * @param deviceAgreement 设备协议
     * @return 结果
     */
    @Override
    public int updateDeviceAgreement(DeviceAgreement deviceAgreement) {
        deviceAgreement.setUpdateTime(DateUtils.getNowDate());
        return deviceAgreementMapper.updateDeviceAgreement(deviceAgreement);
    }

    /**
     * 批量删除设备协议
     *
     * @param agreementIds 需要删除的设备协议主键
     * @return 结果
     */
    @Override
    public int deleteDeviceAgreementByAgreementIds(Long[] agreementIds) {
        return deviceAgreementMapper.deleteDeviceAgreementByAgreementIds(agreementIds);
    }

    /**
     * 删除设备协议信息
     *
     * @param agreementId 设备协议主键
     * @return 结果
     */
    @Override
    public int deleteDeviceAgreementByAgreementId(Long agreementId) {
        return deviceAgreementMapper.deleteDeviceAgreementByAgreementId(agreementId);
    }
}
