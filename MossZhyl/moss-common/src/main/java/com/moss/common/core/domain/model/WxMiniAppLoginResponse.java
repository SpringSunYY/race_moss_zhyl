package com.moss.common.core.domain.model;

import lombok.Data;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl.domain.wx
 * Author: YY
 * CreateTime: 2024-11-04  15:38
 * Description: WxMiniAppLoginResponse
 * 微信登录返回
 * Version: 1.0
 */
@Data
public class WxMiniAppLoginResponse {
    private String openid;

    private String sessionKey;

    private String unionid;

    private String errcode;

    private String errmsg;
}
