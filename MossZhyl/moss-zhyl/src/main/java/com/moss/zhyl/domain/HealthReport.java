package com.moss.zhyl.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 健康报告对象 yl_health_report
 * 
 * @author YY
 * @date 2024-10-23
 */
public class HealthReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long healthReportId;

    /** 标题 */
    @Excel(name = "标题")
    private String reportTitle;

    /** 封面 */
    @Excel(name = "封面")
    private String reportImage;

    /** 报告内容 */
    @Excel(name = "报告内容")
    private String reportContent;

    /** 长者 */
    @Excel(name = "长者")
    private Long userInfoId;

    /** tokens */
    @Excel(name = "tokens")
    private Long useTokens;

    /** 删除 */
    private String delFlag;

    public void setHealthReportId(Long healthReportId) 
    {
        this.healthReportId = healthReportId;
    }

    public Long getHealthReportId() 
    {
        return healthReportId;
    }
    public void setReportTitle(String reportTitle) 
    {
        this.reportTitle = reportTitle;
    }

    public String getReportTitle() 
    {
        return reportTitle;
    }
    public void setReportImage(String reportImage) 
    {
        this.reportImage = reportImage;
    }

    public String getReportImage() 
    {
        return reportImage;
    }
    public void setReportContent(String reportContent) 
    {
        this.reportContent = reportContent;
    }

    public String getReportContent() 
    {
        return reportContent;
    }
    public void setUserInfoId(Long userInfoId) 
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() 
    {
        return userInfoId;
    }
    public void setUseTokens(Long useTokens) 
    {
        this.useTokens = useTokens;
    }

    public Long getUseTokens() 
    {
        return useTokens;
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
            .append("healthReportId", getHealthReportId())
            .append("reportTitle", getReportTitle())
            .append("reportImage", getReportImage())
            .append("reportContent", getReportContent())
            .append("userInfoId", getUserInfoId())
            .append("useTokens", getUseTokens())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
