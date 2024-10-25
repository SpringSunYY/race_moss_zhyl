package com.moss.zhyl.domain;

import com.moss.common.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

import javax.validation.constraints.Size;

/**
 * 设备协议对象 yl_device_agreement
 *
 * @author YY
 * @date 2024-10-25
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

    @Xss(message = "协议名称不能包含脚本字符")
    @Size(min = 0, max = 64, message = "协议名称长度不能超过30个字符")
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("agreementId", getAgreementId())
                .append("reportName", getReportName())
                .append("reportContent", getReportContent())
                .append("agreementStatus", getAgreementStatus())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
