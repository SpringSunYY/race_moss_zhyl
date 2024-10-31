package com.moss.zhyl;

import com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.service.IDeviceUploadingDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    @Test
    public void testGetAIReportData(){
        DeviceUploadingData deviceUploadingData = new DeviceUploadingData();
//        deviceUploadingData.setCreateTime(new Date());
        HashMap<String, Object> params = new HashMap<>();
        params.put("beginCreateTime","2024-10-29");
        params.put("endCreateTime","2024-10-31");
        deviceUploadingData.setParams(params);
        List<DeviceUploadingData> uploadingDataList = deviceUploadingDataService.selectDeviceUploadingDataListByDays(deviceUploadingData);
        for (DeviceUploadingData uploadingData : uploadingDataList) {
            System.out.println(uploadingData);
        }
    }
}
