package com.moss.zhyl.domain;

import com.moss.common.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

import javax.validation.constraints.Size;

/**
 * 用户信息对象 yl_user_info
 *
 * @author YY
 * @date 2024-10-23
 */
public class UserInfo extends BaseEntity {
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

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoName(String userInfoName) {
        this.userInfoName = userInfoName;
    }

    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    public String getUserInfoName() {
        return userInfoName;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setUserInfoProfile(String userInfoProfile) {
        this.userInfoProfile = userInfoProfile;
    }

    public String getUserInfoProfile() {
        return userInfoProfile;
    }

    public void setUserInfoRole(String userInfoRole) {
        this.userInfoRole = userInfoRole;
    }

    public String getUserInfoRole() {
        return userInfoRole;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setEducation(Long education) {
        this.education = education;
    }

    public Long getEducation() {
        return education;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }

    public String getAreaDetail() {
        return areaDetail;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setMiniOpenid(String miniOpenid) {
        this.miniOpenid = miniOpenid;
    }

    public String getMiniOpenid() {
        return miniOpenid;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userInfoId", getUserInfoId())
                .append("userInfoName", getUserInfoName())
                .append("contactPhone", getContactPhone())
                .append("userInfoProfile", getUserInfoProfile())
                .append("userInfoRole", getUserInfoRole())
                .append("idCard", getIdCard())
                .append("education", getEducation())
                .append("occupation", getOccupation())
                .append("addressId", getAddressId())
                .append("areaDetail", getAreaDetail())
                .append("wxOpenid", getWxOpenid())
                .append("miniOpenid", getMiniOpenid())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
