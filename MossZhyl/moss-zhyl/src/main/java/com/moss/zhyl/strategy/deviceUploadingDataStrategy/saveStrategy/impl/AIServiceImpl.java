package com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.impl;

import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.domain.enums.UserInfoRoleEnum;
import com.moss.zhyl.service.IAIService;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.impl
 * @Author: YY
 * @CreateTime: 2024-11-01  15:00
 * @Description: AIServiceImpl
 * @Version: 1.0
 */
@Service
public class AIServiceImpl implements IAIService {
    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public void AIGenerateHealthReport(Integer threads) {
        //1、获取所有长者信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoRole(UserInfoRoleEnum.ELDERLY.getValue());
        List<UserInfo> userInfos = userInfoService.selectUserInfoList(userInfo);
        for (UserInfo info : userInfos) {
            System.out.println("info = " + info);
        }
        //2、获取长者健康信息
        //3、发送异步生成健康报告
        //4、获取生成报告结果
    }

    @Override
    public void GetAIGenerateHealthReportResult() {
        //1、查询任务状态还是没有完成的结果
        //2、获取任务结果
        //3、保存任务信息
    }
}
