package com.moss.zhyl.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 服务站信息对象 yl_service_station
 * 
 * @author YY
 * @date 2024-10-23
 */
public class ServiceStation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long serviceStationId;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String stationName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 服务时间 */
    @Excel(name = "服务时间")
    private String serviceTime;

    /** 地区 */
    @Excel(name = "地区")
    private Long addressId;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String areaDetail;

    /** 站点相册 */
    @Excel(name = "站点相册")
    private String albumUrl;

    /** 删除 */
    private String delFlag;

    /** 管理员 */
    @Excel(name = "管理员")
    private Long userId;

    public void setServiceStationId(Long serviceStationId) 
    {
        this.serviceStationId = serviceStationId;
    }

    public Long getServiceStationId() 
    {
        return serviceStationId;
    }
    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    public String getStationName() 
    {
        return stationName;
    }
    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setServiceTime(String serviceTime) 
    {
        this.serviceTime = serviceTime;
    }

    public String getServiceTime() 
    {
        return serviceTime;
    }
    public void setAddressId(Long addressId) 
    {
        this.addressId = addressId;
    }

    public Long getAddressId() 
    {
        return addressId;
    }
    public void setAreaDetail(String areaDetail) 
    {
        this.areaDetail = areaDetail;
    }

    public String getAreaDetail() 
    {
        return areaDetail;
    }
    public void setAlbumUrl(String albumUrl) 
    {
        this.albumUrl = albumUrl;
    }

    public String getAlbumUrl() 
    {
        return albumUrl;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serviceStationId", getServiceStationId())
            .append("stationName", getStationName())
            .append("contactPerson", getContactPerson())
            .append("contactPhone", getContactPhone())
            .append("serviceTime", getServiceTime())
            .append("addressId", getAddressId())
            .append("areaDetail", getAreaDetail())
            .append("albumUrl", getAlbumUrl())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("userId", getUserId())
            .toString();
    }
}
