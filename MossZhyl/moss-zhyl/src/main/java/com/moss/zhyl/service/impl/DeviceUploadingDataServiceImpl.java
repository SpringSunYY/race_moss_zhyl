package com.moss.zhyl.service.impl;

import com.moss.common.exception.ServiceException;
import com.moss.common.utils.CalculateUtils;
import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.zhyl.domain.deviceUploadingData.Argument;
import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.deviceUploadingData.ElderlyHealthData;
import com.moss.zhyl.domain.deviceUploadingData.argument.HealthArgument;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.zhyl.domain.dto.UserInfoElderlyDto;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.domain.enums.DisabilityStatusEnum;
import com.moss.zhyl.domain.enums.LivingConditionEnum;
import com.moss.zhyl.domain.enums.UserSexEnum;
import com.moss.zhyl.mapper.DeviceUploadingDataMapper;
import com.moss.zhyl.service.IDeviceUploadingDataService;
import com.moss.zhyl.service.IElderlyDeviceBindingService;
import com.moss.zhyl.service.IUserInfoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.moss.common.constant.PermissionsConstants.ZHYL_DEVICE_UPLOADING_DATA_LOOK_DELETE;

/**
 * 设备上传数据Service业务层处理
 *
 * @author YY
 * @date 2024-10-26
 */
@Log
@Service
public class DeviceUploadingDataServiceImpl implements IDeviceUploadingDataService {
    @Autowired
    private DeviceUploadingDataMapper deviceUploadingDataMapper;

    @Autowired
    private IElderlyDeviceBindingService elderlyDeviceBindingService;

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 查询设备上传数据
     *
     * @param dataId 设备上传数据主键
     * @return 设备上传数据
     */
    @Override
    public DeviceUploadingData selectDeviceUploadingDataByDataId(Long dataId) {
        DeviceUploadingData deviceUploadingData = deviceUploadingDataMapper.selectDeviceUploadingDataByDataId(dataId);
        Argument.returnArgumentData(deviceUploadingData);
        return deviceUploadingData;
    }

    /**
     * 查询设备上传数据列表
     *
     * @param deviceUploadingData 设备上传数据
     * @return 设备上传数据
     */
    @Override
    public List<DeviceUploadingData> selectDeviceUploadingDataList(DeviceUploadingData deviceUploadingData) {
        if (!SecurityUtils.hasPermi(ZHYL_DEVICE_UPLOADING_DATA_LOOK_DELETE)) {
            deviceUploadingData.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        }
        List<DeviceUploadingData> uploadingDataList = deviceUploadingDataMapper.selectDeviceUploadingDataList(deviceUploadingData);
        for (DeviceUploadingData info : uploadingDataList) {
            UserInfo userInfo = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(info.getUserInfoId());
            if (StringUtils.isNotNull(userInfo)) {
                info.setUserInfoName(userInfo.getUserInfoName());
            }
        }
        return uploadingDataList;
    }

    /**
     * 新增设备上传数据
     *
     * @param deviceUploadingData 设备上传数据
     * @return 结果
     */
    @Override
    public int insertDeviceUploadingData(DeviceUploadingData deviceUploadingData) {
        //根据设备查询老者信息
        ElderlyDeviceBinding binding = elderlyDeviceBindingService.selectElderlyDeviceBindingByDeviceIMEI(deviceUploadingData.getDeviceId());
        if (StringUtils.isNull(binding)) {
            throw new ServiceException("此设备不存在！！！");
        }
        deviceUploadingData.setUserInfoId(binding.getUserInfoId());

        deviceUploadingData.setDataId(IdUtils.snowflakeId());
        deviceUploadingData.setCreateTime(DateUtils.getNowDate());
        deviceUploadingData.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        return deviceUploadingDataMapper.insertDeviceUploadingData(deviceUploadingData);
    }

    /**
     * 修改设备上传数据
     *
     * @param deviceUploadingData 设备上传数据
     * @return 结果
     */
    @Override
    public int updateDeviceUploadingData(DeviceUploadingData deviceUploadingData) {
        deviceUploadingData.setUpdateTime(DateUtils.getNowDate());
        return deviceUploadingDataMapper.updateDeviceUploadingData(deviceUploadingData);
    }

    /**
     * 批量删除设备上传数据
     *
     * @param dataIds 需要删除的设备上传数据主键
     * @return 结果
     */
    @Override
    public int deleteDeviceUploadingDataByDataIds(Long[] dataIds) {
        return deviceUploadingDataMapper.deleteDeviceUploadingDataByDataIds(dataIds);
    }

    /**
     * 删除设备上传数据信息
     *
     * @param dataId 设备上传数据主键
     * @return 结果
     */
    @Override
    public int deleteDeviceUploadingDataByDataId(Long dataId) {
        return deviceUploadingDataMapper.deleteDeviceUploadingDataByDataId(dataId);
    }

    @Override
    public List<DeviceUploadingData> selectDeviceUploadingDataListByDays(DeviceUploadingData deviceUploadingData) {
        return deviceUploadingDataMapper.selectDeviceUploadingDataListByDays(deviceUploadingData);
    }

    @Override
    public ElderlyHealthData getElderlyHealthData(DeviceUploadingData deviceUploadingData) {
        ElderlyHealthData elderlyHealthData = new ElderlyHealthData();
        //获取长者基本信息
        if (!getElderlyBaseInfo(deviceUploadingData, elderlyHealthData)) {
            return null;
        }
        List<DeviceUploadingData> uploadingDataList = deviceUploadingDataMapper.selectDeviceUploadingDataListByDays(deviceUploadingData);
        if (StringUtils.isEmpty(uploadingDataList)) {
            return null;
        }
        // 平均值初始化
        Long avgHeartRate = 0L, avgDbp = 0L, avgSdp = 0L, avgOxygen = 0L;
        Float avgBloodSugar = 0F, avgTemperature = 0F;

        // 最大值初始化
        int maxHeartRate = 0, maxDbp = 0, maxSdp = 0, maxOxygen = 0;
        float maxBloodSugar = 0, maxTemperature = 0; // 初始化为最小正浮点值

        // 最小值初始化
        int lowerHeartRate = 0, lowerDbp = 0, lowerSdp = 0, lowerOxygen = 0;
        float lowerBloodSugar = 0, lowerTemperature = 0; // 初始化为最大浮点值

        // 众数初始化
        Map<Integer, Integer> heartRateCount = new HashMap<>();
        Map<Integer, Integer> dbpCount = new HashMap<>();
        Map<Integer, Integer> sdpCount = new HashMap<>();
        Map<Integer, Integer> oxygenCount = new HashMap<>();
        Map<Float, Integer> bloodSugarCount = new HashMap<>();
        Map<Float, Integer> temperatureCount = new HashMap<>();
        for (DeviceUploadingData uploadingData : uploadingDataList) {
            HealthArgument healthArgument = new HealthArgument().jsonFormatObject(uploadingData.getArgument());
            int heartRate = healthArgument.getHeartRate();
            int dbp = healthArgument.getDbp();
            int sdp = healthArgument.getSdp();
            int oxygen = healthArgument.getOxygen();
            String bloodSugar = healthArgument.getBloodSugar();
            String strTemperature = healthArgument.getStr_temperature();

            avgHeartRate += heartRate;
            avgDbp += dbp;
            avgSdp += sdp;
            avgOxygen += oxygen;
            avgBloodSugar += Float.parseFloat(bloodSugar);
            avgTemperature += Float.parseFloat(strTemperature);

            maxHeartRate = Math.max(maxHeartRate, heartRate);
            maxDbp = Math.max(maxDbp, dbp);
            maxSdp = Math.max(maxSdp, sdp);
            maxOxygen = Math.max(maxOxygen, oxygen);
            maxBloodSugar = Math.max(maxBloodSugar, Float.parseFloat(bloodSugar));
            maxTemperature = Math.max(maxTemperature, Float.parseFloat(strTemperature));

            lowerHeartRate = Math.min(lowerHeartRate, heartRate);
            lowerDbp = Math.min(lowerDbp, dbp);
            lowerSdp = Math.min(lowerSdp, sdp);
            lowerOxygen = Math.min(lowerOxygen, oxygen);
            lowerBloodSugar = Math.min(lowerBloodSugar, Float.parseFloat(bloodSugar));
            lowerTemperature = Math.min(lowerTemperature, Float.parseFloat(strTemperature));

            heartRateCount.put(heartRate, heartRateCount.getOrDefault(heartRate, 0) + 1);
            dbpCount.put(dbp, dbpCount.getOrDefault(dbp, 0) + 1);
            sdpCount.put(sdp, sdpCount.getOrDefault(sdp, 0) + 1);
            oxygenCount.put(oxygen, oxygenCount.getOrDefault(oxygen, 0) + 1);
            float bloodSugarValue = Float.parseFloat(bloodSugar);
            bloodSugarCount.put(bloodSugarValue, bloodSugarCount.getOrDefault(bloodSugarValue, 0) + 1);
            float temperatureValue = Float.parseFloat(strTemperature);
            temperatureCount.put(temperatureValue, temperatureCount.getOrDefault(temperatureValue, 0) + 1);
        }

        Integer size = uploadingDataList.size();
        ElderlyHealthData.VitalSigns avgSigns = getAvg(avgHeartRate, avgDbp, avgSdp, avgOxygen, avgBloodSugar, avgTemperature, size);
        elderlyHealthData.setAvg(avgSigns);

        // 计算众数
        ElderlyHealthData.VitalSigns modeSigns = getMode(heartRateCount, dbpCount, sdpCount, oxygenCount, bloodSugarCount, temperatureCount);
        elderlyHealthData.setMode(modeSigns);

        // 设置最大值
        ElderlyHealthData.VitalSigns maxSigns = getMax(maxHeartRate, maxDbp, maxSdp, maxOxygen, maxBloodSugar, maxTemperature);
        elderlyHealthData.setMax(maxSigns);

        // 设置最小值
        ElderlyHealthData.VitalSigns lowerSigns = getLower(lowerHeartRate, lowerDbp, lowerSdp, lowerOxygen, lowerBloodSugar, lowerTemperature);
        elderlyHealthData.setLower(lowerSigns);
        return elderlyHealthData;
    }


    private ElderlyHealthData.VitalSigns getMode(Map<Integer, Integer> heartRateCount, Map<Integer, Integer> dbpCount,
                                                 Map<Integer, Integer> sdpCount, Map<Integer, Integer> oxygenCount,
                                                 Map<Float, Integer> bloodSugarCount, Map<Float, Integer> temperatureCount) {
        ElderlyHealthData.VitalSigns modeSigns = new ElderlyHealthData.VitalSigns();
        modeSigns.setHeartRate(CalculateUtils.getMode(heartRateCount));
        modeSigns.setDbp(CalculateUtils.getMode(dbpCount));
        modeSigns.setSdp(CalculateUtils.getMode(sdpCount));
        modeSigns.setOxygen(CalculateUtils.getMode(oxygenCount));
        modeSigns.setBloodSugar(String.valueOf(CalculateUtils.getMode(bloodSugarCount)));
        modeSigns.setStrTemperature(String.valueOf(CalculateUtils.getMode(temperatureCount)));
        return modeSigns;
    }

    private ElderlyHealthData.VitalSigns getMax(int maxHeartRate, int maxDbp, int maxSdp, int maxOxygen,
                                                float maxBloodSugar, float maxTemperature) {
        ElderlyHealthData.VitalSigns maxSigns = new ElderlyHealthData.VitalSigns();
        maxSigns.setHeartRate(maxHeartRate);
        maxSigns.setDbp(maxDbp);
        maxSigns.setSdp(maxSdp);
        maxSigns.setOxygen(maxOxygen);
        maxSigns.setBloodSugar(String.valueOf(maxBloodSugar));
        maxSigns.setStrTemperature(String.valueOf(maxTemperature));
        return maxSigns;
    }

    private ElderlyHealthData.VitalSigns getLower(int lowerHeartRate, int lowerDbp, int lowerSdp, int lowerOxygen,
                                                  float lowerBloodSugar, float lowerTemperature) {
        ElderlyHealthData.VitalSigns lowerSigns = new ElderlyHealthData.VitalSigns();
        lowerSigns.setHeartRate(lowerHeartRate);
        lowerSigns.setDbp(lowerDbp);
        lowerSigns.setSdp(lowerSdp);
        lowerSigns.setOxygen(lowerOxygen);
        lowerSigns.setBloodSugar(String.valueOf(lowerBloodSugar));
        lowerSigns.setStrTemperature(String.valueOf(lowerTemperature));
        return lowerSigns;
    }

    /**
     * @description: 获取长者基本信息
     * @author: YY
     * @method: getElderlyBaseInfo
     * @date: 2024/10/31 21:51
     * @param:
     * @param: deviceUploadingData
     * @param: elderlyHealthData
     * @return: void
     **/
    private boolean getElderlyBaseInfo(DeviceUploadingData deviceUploadingData, ElderlyHealthData elderlyHealthData) {
        UserInfoElderlyDto userInfoElderlyDto = userInfoService.selectUserInfoByUserInfoIdResultDto(deviceUploadingData.getUserInfoId());
        if (StringUtils.isNull(userInfoElderlyDto)) {
            return false;
        }
        elderlyHealthData.setName(userInfoElderlyDto.getUserInfoName());
        elderlyHealthData.setAge(CalculateUtils.calculateAgeByIdCard(userInfoElderlyDto.getIdCard()));
        UserSexEnum userSexEnum = UserSexEnum.getEnumByValue(userInfoElderlyDto.getSex());
        if (StringUtils.isNotNull(userSexEnum)) {
            elderlyHealthData.setSex(userSexEnum.getText());
        }
        LivingConditionEnum livingConditionEnum = LivingConditionEnum.getEnumByValue(userInfoElderlyDto.getLivingCondition());
        if (StringUtils.isNotNull(livingConditionEnum)) {
            elderlyHealthData.setLivingCondition(livingConditionEnum.getText());
        }
        DisabilityStatusEnum disabilityStatusEnum = DisabilityStatusEnum.getEnumByValue(userInfoElderlyDto.getDisabilityStatus());
        if (StringUtils.isNotNull(disabilityStatusEnum)) {
            elderlyHealthData.setDisabilityStatus(disabilityStatusEnum.getText());
        }
        elderlyHealthData.setDisabilityCondition(userInfoElderlyDto.getDisabilityCondition());
        //如果传来的是当天
        if (StringUtils.isNotNull(deviceUploadingData.getCreateTime())) {
            elderlyHealthData.setData(DateUtils.dateTime(deviceUploadingData.getCreateTime()));
        } else { //是时间范围
            Map<String, Object> params = deviceUploadingData.getParams();
            if (StringUtils.isEmpty(params) || StringUtils.isNull(params.get("beginCreateTime")) || StringUtils.isNull(params.get("endCreateTime"))) {
                String msg = "ai健康报告出现异常，请传入正确的时间范围！！！";
                log.info(msg);
                return false;
            }
            elderlyHealthData.setData(params.get("beginCreateTime").toString() + "——" + params.get("endCreateTime").toString());
        }
        return true;
    }

    /**
     * @description: 获取平均数
     * @author: YY
     * @method: getAvg
     * @date: 2024/10/31 22:07
     * @param:
     * @param: avgHeartRate
     * @param: avgDbp
     * @param: avgSdp
     * @param: avgOxygen
     * @param: avgBloodSugar
     * @param: avgTemperature
     * @param: size
     * @return: com.moss.zhyl.domain.DeviceUploadingData.ElderlyHealthData.VitalSigns
     **/
    private ElderlyHealthData.VitalSigns getAvg(Long avgHeartRate, Long avgDbp, Long avgSdp, Long avgOxygen,
                                                Float avgBloodSugar, Float avgTemperature, Integer size) {
        ElderlyHealthData.VitalSigns avgSigns = new ElderlyHealthData.VitalSigns();
        avgOxygen /= size;
        avgHeartRate /= size;
        avgDbp /= size;
        avgSdp /= size;
        avgBloodSugar /= size;
        avgTemperature /= size;
        // 设置平均值
        avgSigns.setHeartRate(Integer.parseInt(avgHeartRate.toString()));
        avgSigns.setDbp(Integer.parseInt(avgDbp.toString()));
        avgSigns.setSdp(Integer.parseInt(avgSdp.toString()));
        avgSigns.setOxygen(Integer.parseInt(avgOxygen.toString()));
        avgSigns.setBloodSugar(String.valueOf(avgBloodSugar));
        avgSigns.setStrTemperature(String.valueOf(avgTemperature));
        return avgSigns;
    }
}
