package com.moss.zhyl.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 咨询对象 yl_consult
 *
 * @author YY
 * @date 2024-11-13
 */
public class Consult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long consultId;

    /** 标题 */
    @Excel(name = "标题")
    private String consultTitle;

    /** 咨询类型 */
    @Excel(name = "咨询类型")
    private String consultType;

    /** 封面 */
//    @Excel(name = "封面")
    private String consultImage;

    /** 内容 */
    @Excel(name = "内容")
    private String consultContent;

    /** 排序 */
    @Excel(name = "排序")
    private Long sorted;

    /** 删除 */
    @Excel(name = "删除")
    private String delFlag;

    public void setConsultId(Long consultId)
    {
        this.consultId = consultId;
    }

    public Long getConsultId()
    {
        return consultId;
    }
    public void setConsultTitle(String consultTitle)
    {
        this.consultTitle = consultTitle;
    }

    public String getConsultTitle()
    {
        return consultTitle;
    }
    public void setConsultType(String consultType)
    {
        this.consultType = consultType;
    }

    public String getConsultType()
    {
        return consultType;
    }
    public void setConsultImage(String consultImage)
    {
        this.consultImage = consultImage;
    }

    public String getConsultImage()
    {
        return consultImage;
    }
    public void setConsultContent(String consultContent)
    {
        this.consultContent = consultContent;
    }

    public String getConsultContent()
    {
        return consultContent;
    }
    public void setSorted(Long sorted)
    {
        this.sorted = sorted;
    }

    public Long getSorted()
    {
        return sorted;
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
                .append("consultId", getConsultId())
                .append("consultTitle", getConsultTitle())
                .append("consultType", getConsultType())
                .append("consultImage", getConsultImage())
                .append("consultContent", getConsultContent())
                .append("sorted", getSorted())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
