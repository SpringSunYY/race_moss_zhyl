package com.moss.zhyl.service;

import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.zhyl.domain.statics.vo.BarStaticVo;
import com.moss.zhyl.domain.statics.vo.CountStaticVo;
import com.moss.zhyl.domain.statics.vo.LineStaticVo;
import com.moss.zhyl.domain.statics.vo.PieStaticVo;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl.service
 * Author: YY
 * CreateTime: 2024-11-01  22:15
 * Description: IStaticsService
 * Version: 1.0
 */
public interface IStaticsService {
    /**
     * description:  获取统计总数
     * author: YY
     * method: getCounts
     * date: 2024/11/1 22:18
     * param:
     * return: com.moss.zhyl.domain.statics.CountStatics
     **/
    public CountStaticVo getCounts();

    /**
     * description: 获取家属每日创建总数
     * author: YY
     * method: getUserinfoFamilyStaticByCreateTimeCounts
     * date: 2024/11/2 18:55
     * param:
     * return: com.moss.zhyl.domain.statics.vo.LineStaticVo
     **/
    public LineStaticVo getUserinfoFamilyStaticByCreateTimeCounts(UserInfo userInfo);

    public LineStaticVo getUserinfoElderlyStaticByCreateTimeCounts(UserInfo userInfo);

    public LineStaticVo getUploadingDateStaticByCreateTimeCounts(DeviceUploadingData deviceUploadingData);

    public LineStaticVo getBindingStaticByCreateTimeCounts(ElderlyDeviceBinding elderlyDeviceBinding);


    /**
     * description: 获取长者信息年龄分布
     * author: YY
     * method: getUserInfoElderlyAge
     * date: 2024/11/3 18:43
     * param:
     *
     * @param: userInfo
     * return: com.moss.zhyl.domain.statics.vo.BarStaticVo
     **/
    public BarStaticVo getUserInfoAge(UserInfo userInfo);

    /**
     * description: 获取设备每种类型总数
     * author: YY
     * method: getDeviceByDeviceTypeCount
     * date: 2024/11/3 19:38
     * param:
     *
     * @param: elderlyDeviceBinding
     * return: com.moss.zhyl.domain.statics.vo.PieStaticVo
     **/
    public PieStaticVo getDeviceByDeviceTypeCount(ElderlyDeviceBinding elderlyDeviceBinding);
}
