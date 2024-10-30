package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 健康报告对象 yl_health_report
 *
 * @author YY
 * @date 2024-10-30
 */
public class HealthReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long healthReportId;

    /** 标题 */
    @Excel(name = "标题")
    private String reportTitle;

    /** 报告类型 */
    @Excel(name = "报告类型")
    private String reportType;

    /** 使用模型 */
    @Excel(name = "使用模型")
    private String useModel;

    /** 封面 */
    @Excel(name = "封面")
    private String reportImage;

    /** 请求内容 */
    @Excel(name = "请求内容")
    private String reportContent;

    /** 报告返回 */
    @Excel(name = "报告返回")
    private String reportReturn;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskId;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String taskStatus;

    /** 长者 */
    @Excel(name = "长者")
    private Long userInfoId;

    /** tokens */
    @Excel(name = "tokens")
    private Long useTokens;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accomplishTime;

    /** 删除 */
    @Excel(name = "删除")
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
    public void setReportType(String reportType)
    {
        this.reportType = reportType;
    }

    public String getReportType()
    {
        return reportType;
    }
    public void setUseModel(String useModel)
    {
        this.useModel = useModel;
    }

    public String getUseModel()
    {
        return useModel;
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
    public void setReportReturn(String reportReturn)
    {
        this.reportReturn = reportReturn;
    }

    public String getReportReturn()
    {
        return reportReturn;
    }
    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public String getTaskId()
    {
        return taskId;
    }
    public void setTaskStatus(String taskStatus)
    {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus()
    {
        return taskStatus;
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
    public void setAccomplishTime(Date accomplishTime)
    {
        this.accomplishTime = accomplishTime;
    }

    public Date getAccomplishTime()
    {
        return accomplishTime;
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
                .append("reportType", getReportType())
                .append("useModel", getUseModel())
                .append("reportImage", getReportImage())
                .append("reportContent", getReportContent())
                .append("reportReturn", getReportReturn())
                .append("taskId", getTaskId())
                .append("taskStatus", getTaskStatus())
                .append("userInfoId", getUserInfoId())
                .append("useTokens", getUseTokens())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("accomplishTime", getAccomplishTime())
                .append("updateTime", getUpdateTime())
                .append("createTime", getCreateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
