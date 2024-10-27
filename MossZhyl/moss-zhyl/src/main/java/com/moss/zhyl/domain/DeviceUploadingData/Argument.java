package com.moss.zhyl.domain.DeviceUploadingData;

import com.alibaba.fastjson2.JSON;

/**
 * @description: 上传数据argument
 * 根节点抽象类：定义系统各层次对象的共有方法和属性，可以预先定义一些默认行为和属性。
 * 设备上下线目前不要，因为他没有
 * @author: YY
 * @method:
 * @date: 2024/10/27 19:04
 * @param:
 * @param: null
 * @return:
 **/
public abstract class Argument {
    /**
     * @description: 定义返回本对象的方法
     * @author: YY
     * @method: jsonFormatObject
     * @date: 2024/10/27 19:18
     * @param:
     * @param: json
     * @return: com.moss.zhyl.domain.DeviceUploadingData.Argument
     **/
    public Argument jsonFormatObject(String json) {
        return JSON.parseObject(json, Argument.class);
    }
}
