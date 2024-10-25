package com.moss.zhyl.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 标签对象 yl_tag_management
 *
 * @author YY
 * @date 2024-10-25
 */
public class TagManagement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long tagId;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;

    public void setTagId(Long tagId)
    {
        this.tagId = tagId;
    }

    public Long getTagId()
    {
        return tagId;
    }
    public void setTagName(String tagName)
    {
        this.tagName = tagName;
    }

    public String getTagName()
    {
        return tagName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("tagId", getTagId())
                .append("tagName", getTagName())
                .append("remark", getRemark())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
