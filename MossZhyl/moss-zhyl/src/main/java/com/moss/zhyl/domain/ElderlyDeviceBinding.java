package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 长者设备绑定对象 yl_elderly_device_binding
 *
 * @author YY
 * @date 2024-10-26
 */
public class ElderlyDeviceBinding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long bindingId;

    /** 长者 */
    @Excel(name = "长者")
    private String userInfoName;
    private Long userInfoId;

    /** 设备 */
    @Excel(name = "设备")
    private String deviceName;
    private Long deviceId;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceTypeName;
    private String deviceType;

    /** 设备IMEI号 */
    @Excel(name = "设备IMEI号")
    private String deviceImei;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private String deviceStatus;

    /** 绑定时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "绑定时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bindTime;

    /** 解绑时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "解绑时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date unbindTime;

    /** 绑定状态 */
    @Excel(name = "绑定状态")
    private String bindingStatus;

    /** 服务人员 */
    @Excel(name = "服务人员")
    private String userName;
    private Long userId;

    /** 删除 */
    @Excel(name = "删除")
    private String delFlag;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getUserInfoName() {
        return userInfoName;
    }

    public void setUserInfoName(String userInfoName) {
        this.userInfoName = userInfoName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

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
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType()
    {
        return deviceType;
    }
    public void setDeviceImei(String deviceImei)
    {
        this.deviceImei = deviceImei;
    }

    @NotBlank(message = "设备IMEI不能为空！！！")
    public String getDeviceImei()
    {
        return deviceImei;
    }
    public void setDeviceStatus(String deviceStatus)
    {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceStatus()
    {
        return deviceStatus;
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

    @NotBlank(message = "绑定状态不能为空！！！")
    public String getBindingStatus()
    {
        return bindingStatus;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    @NotNull(message = "必须选择服务用户！！！")
    public Long getUserId()
    {
        return userId;
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
                .append("deviceType", getDeviceType())
                .append("deviceImei", getDeviceImei())
                .append("deviceStatus", getDeviceStatus())
                .append("bindTime", getBindTime())
                .append("unbindTime", getUnbindTime())
                .append("bindingStatus", getBindingStatus())
                .append("userId", getUserId())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
