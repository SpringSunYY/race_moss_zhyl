package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 长者设备绑定对象 yl_elderly_device_binding
 * 
 * @author YY
 * @date 2024-10-23
 */
public class ElderlyDeviceBinding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long bindingId;

    /** 长者 */
    @Excel(name = "长者")
    private Long userInfoId;

    /** 设备 */
    @Excel(name = "设备")
    private Long deviceId;

    /** 设备IMEI号 */
    @Excel(name = "设备IMEI号")
    private String deviceImei;

    /** 绑定时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "绑定时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bindTime;

    /** 解绑时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "解绑时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date unbindTime;

    /** 绑定状态 */
    @Excel(name = "绑定状态")
    private String bindingStatus;

    /** 删除 */
    private String delFlag;

    public void setBindingId(Long bindingId) 
    {
        this.bindingId = bindingId;
    }

    public Long getBindingId() 
    {
        return bindingId;
    }
    public void setUserInfoId(Long userInfoId) 
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() 
    {
        return userInfoId;
    }
    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }
    public void setDeviceImei(String deviceImei) 
    {
        this.deviceImei = deviceImei;
    }

    public String getDeviceImei() 
    {
        return deviceImei;
    }
    public void setBindTime(Date bindTime) 
    {
        this.bindTime = bindTime;
    }

    public Date getBindTime() 
    {
        return bindTime;
    }
    public void setUnbindTime(Date unbindTime) 
    {
        this.unbindTime = unbindTime;
    }

    public Date getUnbindTime() 
    {
        return unbindTime;
    }
    public void setBindingStatus(String bindingStatus) 
    {
        this.bindingStatus = bindingStatus;
    }

    public String getBindingStatus() 
    {
        return bindingStatus;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bindingId", getBindingId())
            .append("userInfoId", getUserInfoId())
            .append("deviceId", getDeviceId())
            .append("deviceImei", getDeviceImei())
            .append("bindTime", getBindTime())
            .append("unbindTime", getUnbindTime())
            .append("bindingStatus", getBindingStatus())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
