package com.moss.zhyl.wx;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.moss.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl.wx
 * Author: YY
 * CreateTime: 2024-11-04  15:40
 * Description: WxLoginServiceImpl
 * Version: 1.0
 */
@Service
public class WxLoginServiceImpl implements WxLoginService {
    //微信小程序appId
    @Value("${wx.minApp.appId}")
    private String appId;

    //微信小程序密钥
    @Value("${wx.minApp.appSecret}")
    private String appSecret;

    /**
     * 小程序一键登录
     * @return token
     */
    public String miniProgramLogin(String code){
        //微信小程序获取openId请求地址
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", appId).replace("{1}", appSecret).replace("{2}", code);
        String res = HttpUtil.get(replaceUrl);
        System.err.println(res);
        WxMiniAppLoginResponse response = JSONUtil.toBean(res, WxMiniAppLoginResponse.class);
        if (StrUtil.isEmpty(response.getErrcode())){
            System.err.println(response);
            return response.toString();
        }else {
            throw new ServiceException(StrUtil.format("获取微信授权信息失败，错误编码{}，错误信息：{}",response.getErrcode(),response.getErrmsg()));
        }
    }
}
