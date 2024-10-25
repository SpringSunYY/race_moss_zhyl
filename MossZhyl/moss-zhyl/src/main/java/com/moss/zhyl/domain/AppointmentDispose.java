package com.moss.zhyl.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 预约处理对象 yl_appointment_dispose
 * 
 * @author YY
 * @date 2024-10-23
 */
public class AppointmentDispose extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long disposeId;

    /** 长者 */
    @Excel(name = "长者")
    private Long userInfoId;

    /** 预约 */
    @Excel(name = "预约")
    private Long appointmentId;

    /** 处理凭证 */
    @Excel(name = "处理凭证")
    private String disposeVoucher;

    /** 处理描述 */
    private String disposeDescribe;

    /** 处理地址 */
    @Excel(name = "处理地址")
    private Long disposeAddress;

    /** 详细地址 */
    private String addressDetail;

    /** 处理人 */
    @Excel(name = "处理人")
    private Long userId;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date disposeTime;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private String disposeStatus;

    /** 删除 */
    private String delFlag;

    public void setDisposeId(Long disposeId) 
    {
        this.disposeId = disposeId;
    }

    public Long getDisposeId() 
    {
        return disposeId;
    }
    public void setUserInfoId(Long userInfoId) 
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() 
    {
        return userInfoId;
    }
    public void setAppointmentId(Long appointmentId) 
    {
        this.appointmentId = appointmentId;
    }

    public Long getAppointmentId() 
    {
        return appointmentId;
    }
    public void setDisposeVoucher(String disposeVoucher) 
    {
        this.disposeVoucher = disposeVoucher;
    }

    public String getDisposeVoucher() 
    {
        return disposeVoucher;
    }
    public void setDisposeDescribe(String disposeDescribe) 
    {
        this.disposeDescribe = disposeDescribe;
    }

    public String getDisposeDescribe() 
    {
        return disposeDescribe;
    }
    public void setDisposeAddress(Long disposeAddress) 
    {
        this.disposeAddress = disposeAddress;
    }

    public Long getDisposeAddress() 
    {
        return disposeAddress;
    }
    public void setAddressDetail(String addressDetail) 
    {
        this.addressDetail = addressDetail;
    }

    public String getAddressDetail() 
    {
        return addressDetail;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDisposeTime(Date disposeTime) 
    {
        this.disposeTime = disposeTime;
    }

    public Date getDisposeTime() 
    {
        return disposeTime;
    }
    public void setDisposeStatus(String disposeStatus) 
    {
        this.disposeStatus = disposeStatus;
    }

    public String getDisposeStatus() 
    {
        return disposeStatus;
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
            .append("disposeId", getDisposeId())
            .append("userInfoId", getUserInfoId())
            .append("appointmentId", getAppointmentId())
            .append("disposeVoucher", getDisposeVoucher())
            .append("disposeDescribe", getDisposeDescribe())
            .append("disposeAddress", getDisposeAddress())
            .append("addressDetail", getAddressDetail())
            .append("userId", getUserId())
            .append("disposeTime", getDisposeTime())
            .append("disposeStatus", getDisposeStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
