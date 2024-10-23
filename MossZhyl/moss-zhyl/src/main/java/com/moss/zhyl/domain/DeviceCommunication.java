package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 设备通信记录对象 yl_device_communication
 * 
 * @author YY
 * @date 2024-10-23
 */
public class DeviceCommunication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long deviceCommunicationId;

    /** 设备IMEI */
    @Excel(name = "设备IMEI")
    private String deviceImei;

    /** 通讯方式 */
    @Excel(name = "通讯方式")
    private Long communicationMode;

    /** 用户 */
    @Excel(name = "用户")
    private Long userInfoId;

    /** 事件类型 */
    @Excel(name = "事件类型")
    private Long eventType;

    /** 通信内容 */
    @Excel(name = "通信内容")
    private String communicationContent;

    /** 重要程度 */
    @Excel(name = "重要程度")
    private Long importanceLevel;

    /** 通信结果 */
    @Excel(name = "通信结果")
    private Long communicationResult;

    /** 通信时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "通信时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date communicationTime;

    /** 删除 */
    private String delFlag;

    public void setDeviceCommunicationId(Long deviceCommunicationId) 
    {
        this.deviceCommunicationId = deviceCommunicationId;
    }

    public Long getDeviceCommunicationId() 
    {
        return deviceCommunicationId;
    }
    public void setDeviceImei(String deviceImei) 
    {
        this.deviceImei = deviceImei;
    }

    public String getDeviceImei() 
    {
        return deviceImei;
    }
    public void setCommunicationMode(Long communicationMode) 
    {
        this.communicationMode = communicationMode;
    }

    public Long getCommunicationMode() 
    {
        return communicationMode;
    }
    public void setUserInfoId(Long userInfoId) 
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() 
    {
        return userInfoId;
    }
    public void setEventType(Long eventType) 
    {
        this.eventType = eventType;
    }

    public Long getEventType() 
    {
        return eventType;
    }
    public void setCommunicationContent(String communicationContent) 
    {
        this.communicationContent = communicationContent;
    }

    public String getCommunicationContent() 
    {
        return communicationContent;
    }
    public void setImportanceLevel(Long importanceLevel) 
    {
        this.importanceLevel = importanceLevel;
    }

    public Long getImportanceLevel() 
    {
        return importanceLevel;
    }
    public void setCommunicationResult(Long communicationResult) 
    {
        this.communicationResult = communicationResult;
    }

    public Long getCommunicationResult() 
    {
        return communicationResult;
    }
    public void setCommunicationTime(Date communicationTime) 
    {
        this.communicationTime = communicationTime;
    }

    public Date getCommunicationTime() 
    {
        return communicationTime;
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
            .append("deviceCommunicationId", getDeviceCommunicationId())
            .append("deviceImei", getDeviceImei())
            .append("communicationMode", getCommunicationMode())
            .append("userInfoId", getUserInfoId())
            .append("eventType", getEventType())
            .append("communicationContent", getCommunicationContent())
            .append("importanceLevel", getImportanceLevel())
            .append("communicationResult", getCommunicationResult())
            .append("remark", getRemark())
            .append("communicationTime", getCommunicationTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
