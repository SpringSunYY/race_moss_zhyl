package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.core.domain.entity.SysUser;
import com.moss.common.exception.ServiceException;
import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.system.service.ISysUserService;
import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.domain.enums.DeviceUploadingDataCommandEnum;
import com.moss.zhyl.service.IDeviceUploadingDataService;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.DeviceSosAlarmDisposeMapper;
import com.moss.zhyl.domain.DeviceSosAlarmDispose;
import com.moss.zhyl.service.IDeviceSosAlarmDisposeService;
import org.springframework.transaction.annotation.Transactional;

import static com.moss.common.constant.PermissionsConstants.ZHYL_DEVICE_SOS_ALARM_DISPOSE_LOOK_DELETE;

/**
 * 设备预警处理Service业务层处理
 *
 * @author YY
 * @date 2024-10-23
 */
@Service
public class DeviceSosAlarmDisposeServiceImpl implements IDeviceSosAlarmDisposeService {
    @Autowired
    private DeviceSosAlarmDisposeMapper deviceSosAlarmDisposeMapper;

    @Autowired
    private IDeviceUploadingDataService deviceUploadingDataService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 查询设备预警处理
     *
     * @param disposeId 设备预警处理主键
     * @return 设备预警处理
     */
    @Override
    public DeviceSosAlarmDispose selectDeviceSosAlarmDisposeByDisposeId(Long disposeId) {
        return deviceSosAlarmDisposeMapper.selectDeviceSosAlarmDisposeByDisposeId(disposeId);
    }

    /**
     * 查询设备预警处理列表
     *
     * @param deviceSosAlarmDispose 设备预警处理
     * @return 设备预警处理
     */
    @Override
    public List<DeviceSosAlarmDispose> selectDeviceSosAlarmDisposeList(DeviceSosAlarmDispose deviceSosAlarmDispose) {
        if (!SecurityUtils.hasPermi(ZHYL_DEVICE_SOS_ALARM_DISPOSE_LOOK_DELETE)) {
            deviceSosAlarmDispose.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        }
        List<DeviceSosAlarmDispose> deviceSosAlarmDisposes = deviceSosAlarmDisposeMapper.selectDeviceSosAlarmDisposeList(deviceSosAlarmDispose);
        for (DeviceSosAlarmDispose info : deviceSosAlarmDisposes) {
            UserInfo userInfo = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(info.getUserInfoId());
            if (StringUtils.isNotNull(userInfo)) {
                info.setUserInfoName(userInfo.getUserInfoName());
            }
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getUserName());
            }
        }
        return deviceSosAlarmDisposes;
    }

    /**
     * 新增设备预警处理
     *
     * @param deviceSosAlarmDispose 设备预警处理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDeviceSosAlarmDispose(DeviceSosAlarmDispose deviceSosAlarmDispose) {
        updateDeviceUploadingData(deviceSosAlarmDispose);

        deviceSosAlarmDispose.setDisposeId(IdUtils.snowflakeId());
        deviceSosAlarmDispose.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        deviceSosAlarmDispose.setCreateBy(SecurityUtils.getUsername());
        deviceSosAlarmDispose.setCreateTime(DateUtils.getNowDate());
        return deviceSosAlarmDisposeMapper.insertDeviceSosAlarmDispose(deviceSosAlarmDispose);
    }

    private void updateDeviceUploadingData(DeviceSosAlarmDispose deviceSosAlarmDispose) {
        //查询传过来的报警信息
        DeviceUploadingData deviceUploadingData = deviceUploadingDataService.selectDeviceUploadingDataByDataId(deviceSosAlarmDispose.getDataId());
        if (StringUtils.isNull(deviceUploadingData)
                || !deviceUploadingData.getCommand().equals(DeviceUploadingDataCommandEnum.DEVICE_UPLOADING_DATA_COMMAND_WARNING.getValue()))
            throw new ServiceException("设备预警数据不存在！！！");
        if (!deviceUploadingData.getUserInfoId().equals(deviceSosAlarmDispose.getUserInfoId())) {
            throw new ServiceException("报警信息长者与处理长者信息不符！！！");
        }
        //更新对应的信息 因为他们状态数据对应一致，所以直接更新
        deviceUploadingData.setProcessingStatus(deviceSosAlarmDispose.getDisposeStatus());
        deviceUploadingDataService.updateDeviceUploadingData(deviceUploadingData);
    }

    /**
     * 修改设备预警处理
     *
     * @param deviceSosAlarmDispose 设备预警处理
     * @return 结果
     */
    @Override
    public int updateDeviceSosAlarmDispose(DeviceSosAlarmDispose deviceSosAlarmDispose) {
        updateDeviceUploadingData(deviceSosAlarmDispose);
        return deviceSosAlarmDisposeMapper.updateDeviceSosAlarmDispose(deviceSosAlarmDispose);
    }

    /**
     * 批量删除设备预警处理
     *
     * @param disposeIds 需要删除的设备预警处理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceSosAlarmDisposeByDisposeIds(Long[] disposeIds) {
        return deviceSosAlarmDisposeMapper.deleteDeviceSosAlarmDisposeByDisposeIds(disposeIds);
    }

    /**
     * 删除设备预警处理信息
     *
     * @param disposeId 设备预警处理主键
     * @return 结果
     */
    @Override
    public int deleteDeviceSosAlarmDisposeByDisposeId(Long disposeId) {
        return deviceSosAlarmDisposeMapper.deleteDeviceSosAlarmDisposeByDisposeId(disposeId);
    }
}
