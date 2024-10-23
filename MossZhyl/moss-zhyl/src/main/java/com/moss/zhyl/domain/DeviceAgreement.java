package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 设备协议对象 yl_device_agreement
 * 
 * @author YY
 * @date 2024-10-23
 */
public class DeviceAgreement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long agreementId;

    /** 协议名称 */
    @Excel(name = "协议名称")
    private String reportName;

    /** 协议内容 */
    @Excel(name = "协议内容")
    private String reportContent;

    /** 协议状态 */
    @Excel(name = "协议状态")
    private String agreementStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setAgreementId(Long agreementId) 
    {
        this.agreementId = agreementId;
    }

    public Long getAgreementId() 
    {
        return agreementId;
    }
    public void setReportName(String reportName) 
    {
        this.reportName = reportName;
    }

    public String getReportName() 
    {
        return reportName;
    }
    public void setReportContent(String reportContent) 
    {
        this.reportContent = reportContent;
    }

    public String getReportContent() 
    {
        return reportContent;
    }
    public void setAgreementStatus(String agreementStatus) 
    {
        this.agreementStatus = agreementStatus;
    }

    public String getAgreementStatus() 
    {
        return agreementStatus;
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
            .append("agreementId", getAgreementId())
            .append("reportName", getReportName())
            .append("reportContent", getReportContent())
            .append("agreementStatus", getAgreementStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
