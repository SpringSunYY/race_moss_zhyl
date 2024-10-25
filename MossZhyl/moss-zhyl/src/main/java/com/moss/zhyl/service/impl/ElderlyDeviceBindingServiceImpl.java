package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.exception.ServiceException;
import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.zhyl.domain.Device;
import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.domain.enums.BindingStatusEnum;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.domain.enums.DeviceBindingStatusEnum;
import com.moss.zhyl.service.IDeviceService;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.ElderlyDeviceBindingMapper;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.zhyl.service.IElderlyDeviceBindingService;
import org.springframework.transaction.annotation.Transactional;

import static com.moss.common.constant.PermissionsConstants.ZHYL_ELDERLY_DEVICE_BINDING_LOOK_DELETE;
import static com.moss.zhyl.domain.enums.BindingStatusEnum.BINDING_STATUS_ENUM_0;
import static com.moss.zhyl.domain.enums.DeviceBindingStatusEnum.DEVICE_BINDING_STATUS_0;
import static com.moss.zhyl.domain.enums.DeviceBindingStatusEnum.DEVICE_BINDING_STATUS_1;

/**
 * 长者设备绑定Service业务层处理
 *
 * @author YY
 * @date 2024-10-23
 */
@Service
public class ElderlyDeviceBindingServiceImpl implements IElderlyDeviceBindingService {
    @Autowired
    private ElderlyDeviceBindingMapper elderlyDeviceBindingMapper;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IDeviceService deviceService;

    /**
     * 查询长者设备绑定
     *
     * @param bindingId 长者设备绑定主键
     * @return 长者设备绑定
     */
    @Override
    public ElderlyDeviceBinding selectElderlyDeviceBindingByBindingId(Long bindingId) {
        return elderlyDeviceBindingMapper.selectElderlyDeviceBindingByBindingId(bindingId);
    }

    /**
     * 查询长者设备绑定列表
     *
     * @param elderlyDeviceBinding 长者设备绑定
     * @return 长者设备绑定
     */
    @Override
    public List<ElderlyDeviceBinding> selectElderlyDeviceBindingList(ElderlyDeviceBinding elderlyDeviceBinding) {
        if (!SecurityUtils.hasPermi(ZHYL_ELDERLY_DEVICE_BINDING_LOOK_DELETE)) {
            elderlyDeviceBinding.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        }
        List<ElderlyDeviceBinding> elderlyDeviceBindings = elderlyDeviceBindingMapper.selectElderlyDeviceBindingList(elderlyDeviceBinding);
        for (ElderlyDeviceBinding info : elderlyDeviceBindings) {
            UserInfo userInfo = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(info.getUserInfoId());
            if (StringUtils.isNotNull(userInfo)) {
                info.setUserInfoName(userInfo.getUserInfoName());
            }
            Device device = deviceService.selectDeviceByDeviceId(info.getDeviceId());
            if (StringUtils.isNotNull(device)) {
                info.setDeviceName(device.getDeviceModel());
            }
        }
        return elderlyDeviceBindings;
    }

    /**
     * 新增长者设备绑定
     *
     * @param elderlyDeviceBinding 长者设备绑定
     * @return 结果
     */
    @Transactional
    @Override
    public int insertElderlyDeviceBinding(ElderlyDeviceBinding elderlyDeviceBinding) {
        Device device = deviceService.selectDeviceByDeviceId(elderlyDeviceBinding.getDeviceId());
        //判断状态是否已经绑定
        if (device.getIsBinding().equals(DEVICE_BINDING_STATUS_1.getValue())) {
            throw new ServiceException("该设备已经绑定");
        }
        //更新状态为已绑定
        device.setIsBinding(DEVICE_BINDING_STATUS_1.getValue());
        deviceService.updateDevice(device);

        elderlyDeviceBinding.setBindingId(IdUtils.snowflakeId());
        elderlyDeviceBinding.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        elderlyDeviceBinding.setDeviceImei(device.getDeviceImei());
        elderlyDeviceBinding.setCreateBy(SecurityUtils.getUsername());
        elderlyDeviceBinding.setBindingStatus(BINDING_STATUS_ENUM_0.getValue());
        elderlyDeviceBinding.setCreateTime(DateUtils.getNowDate());
        return elderlyDeviceBindingMapper.insertElderlyDeviceBinding(elderlyDeviceBinding);
    }

    /**
     * 修改长者设备绑定
     *
     * @param elderlyDeviceBinding 长者设备绑定
     * @return 结果
     */
    @Override
    public int updateElderlyDeviceBinding(ElderlyDeviceBinding elderlyDeviceBinding) {
        //不可以修改长者信息和设备信息
        ElderlyDeviceBinding old = elderlyDeviceBindingMapper.selectElderlyDeviceBindingByBindingId(elderlyDeviceBinding.getBindingId());
        if (!old.getDeviceId().equals(elderlyDeviceBinding.getDeviceId())
                || !old.getUserInfoId().equals(elderlyDeviceBinding.getUserInfoId())) {
            throw new ServiceException("不可修改用户或者设备信息！！！");
        }
        elderlyDeviceBinding.setUpdateBy(SecurityUtils.getUsername());
        elderlyDeviceBinding.setUpdateTime(DateUtils.getNowDate());
        return elderlyDeviceBindingMapper.updateElderlyDeviceBinding(elderlyDeviceBinding);
    }

    /**
     * 批量删除长者设备绑定
     *
     * @param bindingIds 需要删除的长者设备绑定主键
     * @return 结果
     */
    @Override
    public int deleteElderlyDeviceBindingByBindingIds(Long[] bindingIds) {
        return elderlyDeviceBindingMapper.deleteElderlyDeviceBindingByBindingIds(bindingIds);
    }

    /**
     * 删除长者设备绑定信息
     *
     * @param bindingId 长者设备绑定主键
     * @return 结果
     */
    @Override
    public int deleteElderlyDeviceBindingByBindingId(Long bindingId) {
        return elderlyDeviceBindingMapper.deleteElderlyDeviceBindingByBindingId(bindingId);
    }
}
