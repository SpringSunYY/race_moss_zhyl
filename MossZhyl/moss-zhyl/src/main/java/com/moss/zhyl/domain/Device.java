package com.moss.zhyl.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 设备对象 yl_device
 *
 * @author YY
 * @date 2024-10-26
 */
public class Device extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    private Long deviceId;

    /** 设备品牌 */
    @Excel(name = "设备品牌")
    private String brandName;
    private Long brandId;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceTypeName;
    private String deviceType;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String deviceModel;

    /** 通信协议 */
    @Excel(name = "通信协议")
    private Long communicationProtocol;

    /** 联网方式 */
    @Excel(name = "联网方式")
    private Long networkMode;

    /** 供电方式 */
    @Excel(name = "供电方式")
    private Long powerSupplyMode;

    /** 安装方式 */
    @Excel(name = "安装方式")
    private Long installationMode;

    /** 设备价格 */
    @Excel(name = "设备价格")
    private BigDecimal devicePrice;

    /** 质保时间 */
    @Excel(name = "质保时间")
    private Long warrantyPeriod;

    /** 设备功能 */
    @Excel(name = "设备功能")
    private String deviceFunction;

    /** 设备参数 */
    @Excel(name = "设备参数")
    private String deviceParameters;

    /** 设备图片 */
    @Excel(name = "设备图片")
    private String deviceImageUrl;

    /** 删除标记 */
    @Excel(name = "删除标记")
    private String delFlag;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }
    public void setBrandId(Long brandId)
    {
        this.brandId = brandId;
    }

    public Long getBrandId()
    {
        return brandId;
    }
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType()
    {
        return deviceType;
    }
    public void setDeviceModel(String deviceModel)
    {
        this.deviceModel = deviceModel;
    }

    public String getDeviceModel()
    {
        return deviceModel;
    }
    public void setCommunicationProtocol(Long communicationProtocol)
    {
        this.communicationProtocol = communicationProtocol;
    }

    public Long getCommunicationProtocol()
    {
        return communicationProtocol;
    }
    public void setNetworkMode(Long networkMode)
    {
        this.networkMode = networkMode;
    }

    public Long getNetworkMode()
    {
        return networkMode;
    }
    public void setPowerSupplyMode(Long powerSupplyMode)
    {
        this.powerSupplyMode = powerSupplyMode;
    }

    public Long getPowerSupplyMode()
    {
        return powerSupplyMode;
    }
    public void setInstallationMode(Long installationMode)
    {
        this.installationMode = installationMode;
    }

    public Long getInstallationMode()
    {
        return installationMode;
    }
    public void setDevicePrice(BigDecimal devicePrice)
    {
        this.devicePrice = devicePrice;
    }

    public BigDecimal getDevicePrice()
    {
        return devicePrice;
    }
    public void setWarrantyPeriod(Long warrantyPeriod)
    {
        this.warrantyPeriod = warrantyPeriod;
    }

    public Long getWarrantyPeriod()
    {
        return warrantyPeriod;
    }
    public void setDeviceFunction(String deviceFunction)
    {
        this.deviceFunction = deviceFunction;
    }

    public String getDeviceFunction()
    {
        return deviceFunction;
    }
    public void setDeviceParameters(String deviceParameters)
    {
        this.deviceParameters = deviceParameters;
    }

    public String getDeviceParameters()
    {
        return deviceParameters;
    }
    public void setDeviceImageUrl(String deviceImageUrl)
    {
        this.deviceImageUrl = deviceImageUrl;
    }

    public String getDeviceImageUrl()
    {
        return deviceImageUrl;
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
                .append("deviceId", getDeviceId())
                .append("brandId", getBrandId())
                .append("deviceType", getDeviceType())
                .append("deviceModel", getDeviceModel())
                .append("communicationProtocol", getCommunicationProtocol())
                .append("networkMode", getNetworkMode())
                .append("powerSupplyMode", getPowerSupplyMode())
                .append("installationMode", getInstallationMode())
                .append("devicePrice", getDevicePrice())
                .append("warrantyPeriod", getWarrantyPeriod())
                .append("deviceFunction", getDeviceFunction())
                .append("deviceParameters", getDeviceParameters())
                .append("deviceImageUrl", getDeviceImageUrl())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
