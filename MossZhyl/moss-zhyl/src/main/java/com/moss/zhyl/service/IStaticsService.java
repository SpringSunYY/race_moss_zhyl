package com.moss.zhyl.service;

import com.moss.zhyl.domain.statics.CountStatics;

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
    public CountStatics getCounts();
}
