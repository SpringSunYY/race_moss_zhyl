package com.moss.zhyl.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 设备上传数据对象 yl_device_uploading_data
 * 
 * @author YY
 * @date 2024-10-26
 */
public class DeviceUploadingData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long dataId;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceId;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 命令 */
    @Excel(name = "命令")
    private String command;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceType;

    /** 长者 */
    @Excel(name = "长者")
    private Long userInfoId;

    /** 对象参数 */
    @Excel(name = "对象参数")
    private String argument;

    /** 报警类型 */
    @Excel(name = "报警类型")
    private String warningType;

    /** 经度 */
    @Excel(name = "经度")
    private String lon;

    /** 纬度 */
    @Excel(name = "纬度")
    private String lat;

    /** 状态 */
    @Excel(name = "状态")
    private String processingStatus;

    /** 删除 */
    @Excel(name = "删除")
    private String delFlag;

    public void setDataId(Long dataId) 
    {
        this.dataId = dataId;
    }

    public Long getDataId() 
    {
        return dataId;
    }
    public void setDeviceId(String deviceId) 
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId() 
    {
        return deviceId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setCommand(String command) 
    {
        this.command = command;
    }

    public String getCommand() 
    {
        return command;
    }
    public void setDeviceType(String deviceType) 
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType() 
    {
        return deviceType;
    }
    public void setUserInfoId(Long userInfoId) 
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() 
    {
        return userInfoId;
    }
    public void setArgument(String argument) 
    {
        this.argument = argument;
    }

    public String getArgument() 
    {
        return argument;
    }
    public void setWarningType(String warningType) 
    {
        this.warningType = warningType;
    }

    public String getWarningType() 
    {
        return warningType;
    }
    public void setLon(String lon) 
    {
        this.lon = lon;
    }

    public String getLon() 
    {
        return lon;
    }
    public void setLat(String lat) 
    {
        this.lat = lat;
    }

    public String getLat() 
    {
        return lat;
    }
    public void setProcessingStatus(String processingStatus) 
    {
        this.processingStatus = processingStatus;
    }

    public String getProcessingStatus() 
    {
        return processingStatus;
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
            .append("dataId", getDataId())
            .append("deviceId", getDeviceId())
            .append("type", getType())
            .append("command", getCommand())
            .append("deviceType", getDeviceType())
            .append("userInfoId", getUserInfoId())
            .append("argument", getArgument())
            .append("warningType", getWarningType())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("processingStatus", getProcessingStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
