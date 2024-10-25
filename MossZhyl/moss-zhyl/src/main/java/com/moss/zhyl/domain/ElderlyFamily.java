package com.moss.zhyl.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 家属信息对象 yl_elderly_family
 *
 * @author YY
 * @date 2024-10-25
 */
public class ElderlyFamily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long elderlyFamilyId;

    /** 家属 */
    @Excel(name = "家属")
    private String userInfoName;
    private Long userInfoId;

    /** 长者 */
    @Excel(name = "长者")
    private String userInfoElderlyName;
    private Long userInfoElderlyId;

    /** 关系 */
    @Excel(name = "关系")
    private String relationshipType;

    public String getUserInfoName() {
        return userInfoName;
    }

    public void setUserInfoName(String userInfoName) {
        this.userInfoName = userInfoName;
    }

    public String getUserInfoElderlyName() {
        return userInfoElderlyName;
    }

    public void setUserInfoElderlyName(String userInfoElderlyName) {
        this.userInfoElderlyName = userInfoElderlyName;
    }

    public void setElderlyFamilyId(Long elderlyFamilyId)
    {
        this.elderlyFamilyId = elderlyFamilyId;
    }

    public Long getElderlyFamilyId()
    {
        return elderlyFamilyId;
    }
    public void setUserInfoId(Long userInfoId)
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId()
    {
        return userInfoId;
    }
    public void setUserInfoElderlyId(Long userInfoElderlyId)
    {
        this.userInfoElderlyId = userInfoElderlyId;
    }

    public Long getUserInfoElderlyId()
    {
        return userInfoElderlyId;
    }
    public void setRelationshipType(String relationshipType)
    {
        this.relationshipType = relationshipType;
    }

    public String getRelationshipType()
    {
        return relationshipType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("elderlyFamilyId", getElderlyFamilyId())
                .append("userInfoId", getUserInfoId())
                .append("userInfoElderlyId", getUserInfoElderlyId())
                .append("relationshipType", getRelationshipType())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
