package com.moss.zhyl;

import com.moss.common.utils.CalculateUtils;
import com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.DeviceUploadingData.ElderlyHealthData;
import com.moss.zhyl.domain.DeviceUploadingData.argument.HealthArgument;
import com.moss.zhyl.service.IDeviceUploadingDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.moss.zhyl.domain.enums.DeviceUploadingDataCommandEnum.DEVICE_UPLOADING_DATA_COMMAND_HEALTH;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl
 * @Author: YY
 * @CreateTime: 2024-10-31  15:56
 * @Description: DeviceUploadingDataTest
 * @Version: 1.0
 */
@SpringBootTest
public class DeviceUploadingDataTest {
    @Autowired
    private IDeviceUploadingDataService deviceUploadingDataService;

    public static void main(String[] args) {
        String idCard = "522624200211011211";
        System.out.println(CalculateUtils.calculateBirthdayByIdCard(idCard));
        System.out.println(CalculateUtils.calculateAgeByIdCard(idCard));
    }

    @Test
    public void testGetElderlyHealthData(){
        DeviceUploadingData deviceUploadingData = new DeviceUploadingData();
        deviceUploadingData.setUserInfoId(1849308235669049352L);
        deviceUploadingData.setCommand("health");
        deviceUploadingData.setCreateTime(new Date());
        ElderlyHealthData elderlyHealthData = deviceUploadingDataService.getElderlyHealthData(deviceUploadingData);
        System.out.println(elderlyHealthData);

    }
    @Test
    public void testGetAIReportData() {

        ElderlyHealthData elderlyHealthData = new ElderlyHealthData();
        ElderlyHealthData.VitalSigns avgSigns = new ElderlyHealthData.VitalSigns();
        ElderlyHealthData.VitalSigns maxSigns = new ElderlyHealthData.VitalSigns();
        ElderlyHealthData.VitalSigns lowerSigns = new ElderlyHealthData.VitalSigns();
        ElderlyHealthData.VitalSigns modeSigns = new ElderlyHealthData.VitalSigns();

        // 平均值初始化
        Long avgHeartRate = 0L;
        Long avgDbp = 0L;
        Long avgSdp = 0L;
        Long avgOxygen = 0L;
        Float avgBloodSugar = 0F;
        Float avgTemperature = 0F;

        // 最大值初始化
        int maxHeartRate = 0;
        int maxDbp = 0;
        int maxSdp = 0;
        int maxOxygen = 0;
        float maxBloodSugar = 0;
        float maxTemperature = Float.MIN_VALUE; // 初始化为最小正浮点值

        // 最小值初始化
        int lowerHeartRate = Integer.MAX_VALUE;
        int lowerDbp = Integer.MAX_VALUE;
        int lowerSdp = Integer.MAX_VALUE;
        int lowerOxygen = Integer.MAX_VALUE;
        float lowerBloodSugar = Float.MAX_VALUE;
        float lowerTemperature = Float.MAX_VALUE; // 初始化为最大浮点值

        // 众数初始化
        Map<Integer, Integer> heartRateCount = new HashMap<>();
        Map<Integer, Integer> dbpCount = new HashMap<>();
        Map<Integer, Integer> sdpCount = new HashMap<>();
        Map<Integer, Integer> oxygenCount = new HashMap<>();
        Map<Float, Integer> bloodSugarCount = new HashMap<>();
        Map<Float, Integer> temperatureCount = new HashMap<>();

        DeviceUploadingData deviceUploadingData = new DeviceUploadingData();
        deviceUploadingData.setCreateTime(new Date());
        HashMap<String, Object> params = new HashMap<>();
        params.put("beginCreateTime", "2024-10-20");
        params.put("endCreateTime", "2024-10-30");
        deviceUploadingData.setCommand(DEVICE_UPLOADING_DATA_COMMAND_HEALTH.getValue());
        deviceUploadingData.setParams(params);

        List<DeviceUploadingData> uploadingDataList = deviceUploadingDataService.selectDeviceUploadingDataListByDays(deviceUploadingData);

        for (DeviceUploadingData uploadingData : uploadingDataList) {
            System.err.println(uploadingData);
            HealthArgument healthArgument = new HealthArgument().jsonFormatObject(uploadingData.getArgument());
            int heartRate = healthArgument.getHeartRate();
            int dbp = healthArgument.getDbp();
            int sdp = healthArgument.getSdp();
            int oxygen = healthArgument.getOxygen();
            String bloodSugar = healthArgument.getBloodSugar();
            String strTemperature = healthArgument.getStr_temperature();

            // 计算平均值
            avgOxygen += oxygen;
            avgHeartRate += heartRate;
            avgDbp += dbp;
            avgSdp += sdp;
            avgBloodSugar += Float.parseFloat(bloodSugar);
            avgTemperature += Float.parseFloat(strTemperature);

            // 更新最大值和最小值
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

            // 记录众数
            heartRateCount.put(heartRate, heartRateCount.getOrDefault(heartRate, 0) + 1);
            dbpCount.put(dbp, dbpCount.getOrDefault(dbp, 0) + 1);
            sdpCount.put(sdp, sdpCount.getOrDefault(sdp, 0) + 1);
            oxygenCount.put(oxygen, oxygenCount.getOrDefault(oxygen, 0) + 1);
            float bloodSugarValue = Float.parseFloat(bloodSugar);
            bloodSugarCount.put(bloodSugarValue, bloodSugarCount.getOrDefault(bloodSugarValue, 0) + 1);
            float temperatureValue = Float.parseFloat(strTemperature);
            temperatureCount.put(temperatureValue, temperatureCount.getOrDefault(temperatureValue, 0) + 1);
        }

        // 计算平均值
        int size = uploadingDataList.size();
        if (size > 0) {
            avgOxygen /= size;
            avgHeartRate /= size;
            avgDbp /= size;
            avgSdp /= size;
            avgBloodSugar /= size;
            avgTemperature /= size;
        }

        // 计算众数
        int modeHeartRate = getMode(heartRateCount);
        int modeDbp = getMode(dbpCount);
        int modeSdp = getMode(sdpCount);
        int modeOxygen = getMode(oxygenCount);
        float modeBloodSugar = getMode(bloodSugarCount);
        float modeTemperature = getMode(temperatureCount);


        // 输出结果
        System.out.println("Average Values:");
        System.out.println("avgOxygen = " + avgOxygen);
        System.out.println("avgHeartRate = " + avgHeartRate);
        System.out.println("avgDbp = " + avgDbp);
        System.out.println("avgSdp = " + avgSdp);
        System.out.println("avgBloodSugar = " + avgBloodSugar);
        System.out.println("avgTemperature = " + avgTemperature);

        System.out.println("Maximum Values:");
        System.out.println("maxHeartRate = " + maxHeartRate);
        System.out.println("maxDbp = " + maxDbp);
        System.out.println("maxSdp = " + maxSdp);
        System.out.println("maxOxygen = " + maxOxygen);
        System.out.println("maxBloodSugar = " + maxBloodSugar);
        System.out.println("maxTemperature = " + maxTemperature);

        System.out.println("Minimum Values:");
        System.out.println("lowerHeartRate = " + lowerHeartRate);
        System.out.println("lowerDbp = " + lowerDbp);
        System.out.println("lowerSdp = " + lowerSdp);
        System.out.println("lowerOxygen = " + lowerOxygen);
        System.out.println("lowerBloodSugar = " + lowerBloodSugar);
        System.out.println("lowerTemperature = " + lowerTemperature);

        System.out.println("Mode Values:");
        System.out.println("modeHeartRate = " + modeHeartRate);
        System.out.println("modeDbp = " + modeDbp);
        System.out.println("modeSdp = " + modeSdp);
        System.out.println("modeOxygen = " + modeOxygen);
        System.out.println("modeBloodSugar = " + modeBloodSugar);
        System.out.println("modeTemperature = " + modeTemperature);

        // 设置平均值
        avgSigns.setHeartRate(Integer.parseInt(avgHeartRate.toString()));
        avgSigns.setDbp(Integer.parseInt(avgDbp.toString()));
        avgSigns.setSdp(Integer.parseInt(avgSdp.toString()));
        avgSigns.setOxygen(Integer.parseInt(avgOxygen.toString()));
        avgSigns.setBloodSugar(String.valueOf(avgBloodSugar));
        avgSigns.setStrTemperature(String.valueOf(avgTemperature));

        // 设置最大值
        maxSigns.setHeartRate(maxHeartRate);
        maxSigns.setDbp(maxDbp);
        maxSigns.setSdp(maxSdp);
        maxSigns.setOxygen(maxOxygen);
        maxSigns.setBloodSugar(String.valueOf(maxBloodSugar));
        maxSigns.setStrTemperature(String.valueOf(maxTemperature));

        // 设置最小值
        lowerSigns.setHeartRate(lowerHeartRate);
        lowerSigns.setDbp(lowerDbp);
        lowerSigns.setSdp(lowerSdp);
        lowerSigns.setOxygen(lowerOxygen);
        lowerSigns.setBloodSugar(String.valueOf(lowerBloodSugar));
        lowerSigns.setStrTemperature(String.valueOf(lowerTemperature));

        // 设置众数
        modeSigns.setHeartRate(modeHeartRate);
        modeSigns.setDbp(modeDbp);
        modeSigns.setSdp(modeSdp);
        modeSigns.setOxygen(modeOxygen);
        modeSigns.setBloodSugar(String.valueOf(modeBloodSugar));
        modeSigns.setStrTemperature(String.valueOf(modeTemperature));

        // 设置到老人健康数据
        elderlyHealthData.setAvg(avgSigns);
        elderlyHealthData.setMax(maxSigns);
        elderlyHealthData.setLower(lowerSigns);
        elderlyHealthData.setMode(modeSigns);

        // 设置其他信息
        elderlyHealthData.setName("YY");
        elderlyHealthData.setSex("男");
        elderlyHealthData.setAge(76);
        elderlyHealthData.setLivingCondition("与配偶居住");
        elderlyHealthData.setDisabilityStatus("轻度");
        elderlyHealthData.setDisabilityCondition("身体情况良好，不适合长跑，腿部残疾，坐轮椅,家住农村");
        elderlyHealthData.setData("2024-10-27");
    }

    private <T> T getMode(Map<T, Integer> countMap) {
        T mode = null;
        int maxCount = 0;
        for (Map.Entry<T, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mode = entry.getKey();
            }
        }
        return mode;
    }
}


