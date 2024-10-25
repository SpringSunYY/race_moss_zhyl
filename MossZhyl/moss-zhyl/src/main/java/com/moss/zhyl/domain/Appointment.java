package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 预约对象 yl_appointment
 * 
 * @author YY
 * @date 2024-10-23
 */
public class Appointment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long appointmentId;

    /** 标题 */
    @Excel(name = "标题")
    private String reportTitle;

    /** 用户 */
    @Excel(name = "用户")
    private Long userInfoId;

    /** 预约内容 */
    @Excel(name = "预约内容")
    private String appointmentContent;

    /** 联系手机 */
    @Excel(name = "联系手机")
    private String contactPhone;

    /** 预约地址 */
    @Excel(name = "预约地址")
    private String appointAddress;

    /** 预约人 */
    @Excel(name = "预约人")
    private Long userId;

    /** 预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentTime;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private String appointmentStatus;

    /** 删除 */
    private String delFlag;

    public void setAppointmentId(Long appointmentId) 
    {
        this.appointmentId = appointmentId;
    }

    public Long getAppointmentId() 
    {
        return appointmentId;
    }
    public void setReportTitle(String reportTitle) 
    {
        this.reportTitle = reportTitle;
    }

    public String getReportTitle() 
    {
        return reportTitle;
    }
    public void setUserInfoId(Long userInfoId) 
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() 
    {
        return userInfoId;
    }
    public void setAppointmentContent(String appointmentContent) 
    {
        this.appointmentContent = appointmentContent;
    }

    public String getAppointmentContent() 
    {
        return appointmentContent;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setAppointAddress(String appointAddress) 
    {
        this.appointAddress = appointAddress;
    }

    public String getAppointAddress() 
    {
        return appointAddress;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAppointmentTime(Date appointmentTime) 
    {
        this.appointmentTime = appointmentTime;
    }

    public Date getAppointmentTime() 
    {
        return appointmentTime;
    }
    public void setAppointmentStatus(String appointmentStatus) 
    {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentStatus() 
    {
        return appointmentStatus;
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
            .append("appointmentId", getAppointmentId())
            .append("reportTitle", getReportTitle())
            .append("userInfoId", getUserInfoId())
            .append("appointmentContent", getAppointmentContent())
            .append("contactPhone", getContactPhone())
            .append("appointAddress", getAppointAddress())
            .append("userId", getUserId())
            .append("appointmentTime", getAppointmentTime())
            .append("appointmentStatus", getAppointmentStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
