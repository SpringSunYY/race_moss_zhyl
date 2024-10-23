package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 设备品牌对象 yl_device_brand
 * 
 * @author YY
 * @date 2024-10-23
 */
public class DeviceBrand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long brandId;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String brandName;

    /** 品牌描述 */
    @Excel(name = "品牌描述")
    private String description;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInfo;

    /** 内容 */
    @Excel(name = "内容")
    private String contractContent;

    /** 签订时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签订时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractSignTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setBrandName(String brandName) 
    {
        this.brandName = brandName;
    }

    public String getBrandName() 
    {
        return brandName;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setContactInfo(String contactInfo) 
    {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() 
    {
        return contactInfo;
    }
    public void setContractContent(String contractContent) 
    {
        this.contractContent = contractContent;
    }

    public String getContractContent() 
    {
        return contractContent;
    }
    public void setContractSignTime(Date contractSignTime) 
    {
        this.contractSignTime = contractSignTime;
    }

    public Date getContractSignTime() 
    {
        return contractSignTime;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("brandId", getBrandId())
            .append("brandName", getBrandName())
            .append("description", getDescription())
            .append("contactInfo", getContactInfo())
            .append("contractContent", getContractContent())
            .append("contractSignTime", getContractSignTime())
            .append("createBy", getCreateBy())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
