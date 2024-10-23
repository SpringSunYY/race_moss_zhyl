package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 长者信息对象 yl_elderly
 * 
 * @author YY
 * @date 2024-10-23
 */
public class Elderly extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long elderlyId;

    /** 长者 */
    @Excel(name = "长者")
    private Long userInfoId;

    /** 失能情况 */
    @Excel(name = "失能情况")
    private String disabilityStatus;

    /** 居住情况 */
    @Excel(name = "居住情况")
    private String livingCondition;

    /** 登记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationTime;

    /** 残疾情况 */
    @Excel(name = "残疾情况")
    private String disabilityCondition;

    public void setElderlyId(Long elderlyId) 
    {
        this.elderlyId = elderlyId;
    }

    public Long getElderlyId() 
    {
        return elderlyId;
    }
    public void setUserInfoId(Long userInfoId) 
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() 
    {
        return userInfoId;
    }
    public void setDisabilityStatus(String disabilityStatus) 
    {
        this.disabilityStatus = disabilityStatus;
    }

    public String getDisabilityStatus() 
    {
        return disabilityStatus;
    }
    public void setLivingCondition(String livingCondition) 
    {
        this.livingCondition = livingCondition;
    }

    public String getLivingCondition() 
    {
        return livingCondition;
    }
    public void setRegistrationTime(Date registrationTime) 
    {
        this.registrationTime = registrationTime;
    }

    public Date getRegistrationTime() 
    {
        return registrationTime;
    }
    public void setDisabilityCondition(String disabilityCondition) 
    {
        this.disabilityCondition = disabilityCondition;
    }

    public String getDisabilityCondition() 
    {
        return disabilityCondition;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("elderlyId", getElderlyId())
            .append("userInfoId", getUserInfoId())
            .append("disabilityStatus", getDisabilityStatus())
            .append("livingCondition", getLivingCondition())
            .append("registrationTime", getRegistrationTime())
            .append("disabilityCondition", getDisabilityCondition())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
