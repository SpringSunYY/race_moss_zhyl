package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.zhyl.domain.DeviceBrand;
import com.moss.zhyl.domain.DeviceType;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.domain.enums.DeviceBindingStatusEnum;
import com.moss.zhyl.service.IDeviceBrandService;
import com.moss.zhyl.service.IDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.DeviceMapper;
import com.moss.zhyl.domain.Device;
import com.moss.zhyl.service.IDeviceService;

import static com.moss.common.constant.PermissionsConstants.ZHYL_DEVICE_LOOK_DELETE;
import static com.moss.common.constant.PermissionsConstants.ZHYL_USER_INFO_LOOK_DELETE;
import static com.moss.zhyl.domain.enums.DelFlagEnum.DEL_FLAG_0;

/**
 * 设备Service业务层处理
 *
 * @author YY
 * @date 2024-10-23
 */
@Service
public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private IDeviceBrandService deviceBrandService;

    @Autowired
    private IDeviceTypeService deviceTypeService;

    /**
     * 查询设备
     *
     * @param deviceId 设备主键
     * @return 设备
     */
    @Override
    public Device selectDeviceByDeviceId(Long deviceId) {
        return deviceMapper.selectDeviceByDeviceId(deviceId);
    }

    /**
     * 查询设备列表
     *
     * @param device 设备
     * @return 设备
     */
    @Override
    public List<Device> selectDeviceList(Device device) {
        if (!SecurityUtils.hasPermi(ZHYL_DEVICE_LOOK_DELETE)) {
            device.setDelFlag(DEL_FLAG_0.getValue());
        }
        List<Device> devices = deviceMapper.selectDeviceList(device);
        for (Device info : devices) {
            DeviceBrand deviceBrand = deviceBrandService.selectDeviceBrandByBrandId(info.getBrandId());
            if (StringUtils.isNotNull(deviceBrand)) {
                info.setBrandName(deviceBrand.getBrandName());
            }
            DeviceType type = deviceTypeService.selectDeviceTypeByDeviceType(info.getDeviceType());
            if (StringUtils.isNotNull(type)) {
                info.setDeviceTypeName(type.getName());
            }
        }
        return devices;
    }

    /**
     * 新增设备
     *
     * @param device 设备
     * @return 结果
     */
    @Override
    public int insertDevice(Device device) {
        device.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        device.setCreateBy(SecurityUtils.getUsername());
        device.setDeviceId(IdUtils.snowflakeId());
        device.setCreateTime(DateUtils.getNowDate());
        return deviceMapper.insertDevice(device);
    }

    /**
     * 修改设备
     *
     * @param device 设备
     * @return 结果
     */
    @Override
    public int updateDevice(Device device) {
        device.setUpdateBy(SecurityUtils.getUsername());
        device.setUpdateTime(DateUtils.getNowDate());
        return deviceMapper.updateDevice(device);
    }

    /**
     * 批量删除设备
     *
     * @param deviceIds 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteDeviceByDeviceIds(Long[] deviceIds) {
        return deviceMapper.deleteDeviceByDeviceIds(deviceIds);
    }

    /**
     * 删除设备信息
     *
     * @param deviceId 设备主键
     * @return 结果
     */
    @Override
    public int deleteDeviceByDeviceId(Long deviceId) {
        return deviceMapper.deleteDeviceByDeviceId(deviceId);
    }
}
