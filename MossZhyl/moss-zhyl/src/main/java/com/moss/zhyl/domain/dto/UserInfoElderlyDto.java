package com.moss.zhyl.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain
 * @Author: YY
 * @CreateTime: 2024-10-25  08:53
 * @Description: UserInfoElderlyDto
 * 长者信息dto
 * @Version: 1.0
 */
public class UserInfoElderlyDto extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long userInfoId;

    /**
     * 家属姓名
     */
    @Excel(name = "家属姓名")
    private String userInfoName;

    /**
     * 联系手机
     */
    @Excel(name = "联系手机")
    private String contactPhone;

    /**
     * 用户头像
     */
    @Excel(name = "用户头像")
    private String userInfoProfile;

    /**
     * 角色
     */
    @Excel(name = "角色")
    private String userInfoRole;

    /**
     * 身份证
     */
    @Excel(name = "身份证")
    private String idCard;

    /**
     * 学历
     */
    @Excel(name = "学历")
    private Long education;

    /**
     * 职业
     */
    @Excel(name = "职业")
    private String occupation;

    /**
     * 性别
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /**
     * 密码
     */
    private String password;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private Long addressId;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址")
    private String areaDetail;

    /**
     * 微信openid
     */
    @Excel(name = "微信openid")
    private String wxOpenid;

    /**
     * 小程序openid
     */
    @Excel(name = "小程序openid")
    private String miniOpenid;

    /**
     * 删除
     */
    private String delFlag;

    /**
     * 编号
     */
    private Long elderlyId;

    /**
     * 失能情况
     */
    @Excel(name = "失能情况")
    private String disabilityStatus;

    /**
     * 居住情况
     */
    @Excel(name = "居住情况")
    private String livingCondition;

    /**
     * 登记时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationTime;

    /**
     * 残疾情况
     */
    @Excel(name = "残疾情况")
    private String disabilityCondition;

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserInfoName() {
        return userInfoName;
    }

    public void setUserInfoName(String userInfoName) {
        this.userInfoName = userInfoName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getUserInfoProfile() {
        return userInfoProfile;
    }

    public void setUserInfoProfile(String userInfoProfile) {
        this.userInfoProfile = userInfoProfile;
    }

    public String getUserInfoRole() {
        return userInfoRole;
    }

    public void setUserInfoRole(String userInfoRole) {
        this.userInfoRole = userInfoRole;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getEducation() {
        return education;
    }

    public void setEducation(Long education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAreaDetail() {
        return areaDetail;
    }

    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getMiniOpenid() {
        return miniOpenid;
    }

    public void setMiniOpenid(String miniOpenid) {
        this.miniOpenid = miniOpenid;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getElderlyId() {
        return elderlyId;
    }

    public void setElderlyId(Long elderlyId) {
        this.elderlyId = elderlyId;
    }

    public String getDisabilityStatus() {
        return disabilityStatus;
    }

    public void setDisabilityStatus(String disabilityStatus) {
        this.disabilityStatus = disabilityStatus;
    }

    public String getLivingCondition() {
        return livingCondition;
    }

    public void setLivingCondition(String livingCondition) {
        this.livingCondition = livingCondition;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getDisabilityCondition() {
        return disabilityCondition;
    }

    public void setDisabilityCondition(String disabilityCondition) {
        this.disabilityCondition = disabilityCondition;
    }

    @Override
    public String toString() {
        return "UserInfoElderlyDto{" +
                "userInfoId=" + userInfoId +
                ", userInfoName='" + userInfoName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", userInfoProfile='" + userInfoProfile + '\'' +
                ", userInfoRole='" + userInfoRole + '\'' +
                ", idCard='" + idCard + '\'' +
                ", education=" + education +
                ", occupation='" + occupation + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", addressId=" + addressId +
                ", areaDetail='" + areaDetail + '\'' +
                ", wxOpenid='" + wxOpenid + '\'' +
                ", miniOpenid='" + miniOpenid + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", elderlyId=" + elderlyId +
                ", disabilityStatus='" + disabilityStatus + '\'' +
                ", livingCondition='" + livingCondition + '\'' +
                ", registrationTime=" + registrationTime +
                ", disabilityCondition='" + disabilityCondition + '\'' +
                '}';
    }
}
