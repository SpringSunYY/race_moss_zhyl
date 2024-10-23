package com.moss.zhyl.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 家属信息对象 yl_elderly_family
 * 
 * @author YY
 * @date 2024-10-23
 */
public class ElderlyFamily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long elderlyFamilyId;

    /** 家属 */
    @Excel(name = "家属")
    private Long userInfoId;

    /** 长者 */
    @Excel(name = "长者")
    private Long userInfoElderlyId;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("elderlyFamilyId", getElderlyFamilyId())
            .append("userInfoId", getUserInfoId())
            .append("userInfoElderlyId", getUserInfoElderlyId())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
