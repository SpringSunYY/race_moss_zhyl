package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 设备预警对象 yl_device_sos_alarm
 * 
 * @author YY
 * @date 2024-10-23
 */
public class DeviceSosAlarm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long sosAlarmId;

    /** 长者 */
    @Excel(name = "长者")
    private Long userInfoId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userInfoName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactNumber;

    /** 警报时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "警报时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date alarmTime;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String deviceModel;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private String processingStatus;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String processingResult;

    /** 设备IMEI号 */
    @Excel(name = "设备IMEI号")
    private String deviceImei;

    /** 警报内容 */
    @Excel(name = "警报内容")
    private String alarmContent;

    /** 警报地址 */
    @Excel(name = "警报地址")
    private String alarmAddress;

    /** 删除 */
    private String delFlag;

    public void setSosAlarmId(Long sosAlarmId) 
    {
        this.sosAlarmId = sosAlarmId;
    }

    public Long getSosAlarmId() 
    {
        return sosAlarmId;
    }
    public void setUserInfoId(Long userInfoId) 
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() 
    {
        return userInfoId;
    }
    public void setUserInfoName(String userInfoName) 
    {
        this.userInfoName = userInfoName;
    }

    public String getUserInfoName() 
    {
        return userInfoName;
    }
    public void setContactNumber(String contactNumber) 
    {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() 
    {
        return contactNumber;
    }
    public void setAlarmTime(Date alarmTime) 
    {
        this.alarmTime = alarmTime;
    }

    public Date getAlarmTime() 
    {
        return alarmTime;
    }
    public void setDeviceModel(String deviceModel) 
    {
        this.deviceModel = deviceModel;
    }

    public String getDeviceModel() 
    {
        return deviceModel;
    }
    public void setProcessingStatus(String processingStatus) 
    {
        this.processingStatus = processingStatus;
    }

    public String getProcessingStatus() 
    {
        return processingStatus;
    }
    public void setProcessingResult(String processingResult) 
    {
        this.processingResult = processingResult;
    }

    public String getProcessingResult() 
    {
        return processingResult;
    }
    public void setDeviceImei(String deviceImei) 
    {
        this.deviceImei = deviceImei;
    }

    public String getDeviceImei() 
    {
        return deviceImei;
    }
    public void setAlarmContent(String alarmContent) 
    {
        this.alarmContent = alarmContent;
    }

    public String getAlarmContent() 
    {
        return alarmContent;
    }
    public void setAlarmAddress(String alarmAddress) 
    {
        this.alarmAddress = alarmAddress;
    }

    public String getAlarmAddress() 
    {
        return alarmAddress;
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
            .append("sosAlarmId", getSosAlarmId())
            .append("userInfoId", getUserInfoId())
            .append("userInfoName", getUserInfoName())
            .append("contactNumber", getContactNumber())
            .append("alarmTime", getAlarmTime())
            .append("deviceModel", getDeviceModel())
            .append("processingStatus", getProcessingStatus())
            .append("processingResult", getProcessingResult())
            .append("deviceImei", getDeviceImei())
            .append("alarmContent", getAlarmContent())
            .append("alarmAddress", getAlarmAddress())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
