package com.moss.zhyl.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 设备预警处理对象 yl_device_sos_alarm_dispose
 *
 * @author YY
 * @date 2024-10-23
 */
public class DeviceSosAlarmDispose extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long disposeId;

    /** 长者 */
    @Excel(name = "长者")
    private Long userInfoId;

    /** 预警 */
    @Excel(name = "预警")
    private Long dataId;

    /** 处理凭证 */
    @Excel(name = "处理凭证")
    private String disposeVoucher;

    /** 处理描述 */
    @Excel(name = "处理描述")
    private String disposeDescribe;

    /** 处理地址 */
    @Excel(name = "处理地址")
    private Long addressId;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressDetail;

    /** 处理人 */
    @Excel(name = "处理人")
    private Long userId;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date disposeTime;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private String disposeStatus;

    /** 删除 */
    @Excel(name = "删除")
    private String delFlag;

    public void setDisposeId(Long disposeId) {
        this.disposeId = disposeId;
    }

    public Long getDisposeId() {
        return disposeId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public void setDisposeVoucher(String disposeVoucher) {
        this.disposeVoucher = disposeVoucher;
    }

    public String getDisposeVoucher() {
        return disposeVoucher;
    }

    public void setDisposeDescribe(String disposeDescribe) {
        this.disposeDescribe = disposeDescribe;
    }

    @NotBlank(message = "请输入处理描述！！！")
    public String getDisposeDescribe() {
        return disposeDescribe;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @NotNull(message = "地理位置不能为空！！！")
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    @NotBlank(message = "请输入详细的地理位置！！！")
    public String getAddressDetail() {
        return addressDetail;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @NotNull(message = "请选择处理人！！！")
    public Long getUserId() {
        return userId;
    }

    public void setDisposeTime(Date disposeTime) {
        this.disposeTime = disposeTime;
    }

    @NotNull(message = "请选择处理时间！！！")
    public Date getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeStatus(String disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    @NotBlank(message = "请选择处理情况！！！")
    public String getDisposeStatus() {
        return disposeStatus;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return "DeviceSosAlarmDispose{" +
                "disposeId=" + disposeId +
                ", userInfoId=" + userInfoId +
                ", dataId=" + dataId +
                ", disposeVoucher='" + disposeVoucher + '\'' +
                ", disposeDescribe='" + disposeDescribe + '\'' +
                ", addressId=" + addressId +
                ", addressDetail='" + addressDetail + '\'' +
                ", userId=" + userId +
                ", disposeTime=" + disposeTime +
                ", disposeStatus='" + disposeStatus + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
