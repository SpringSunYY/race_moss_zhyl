package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.moss.common.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

import javax.validation.constraints.Size;

/**
 * 设备品牌对象 yl_device_brand
 *
 * @author YY
 * @date 2024-10-25
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "签订时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date contractSignTime;

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

    @Xss(message = "品牌名称不能包含脚本字符")
    @Size(min = 0, max = 64, message = "品牌名称长度不能超过30个字符")
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
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
