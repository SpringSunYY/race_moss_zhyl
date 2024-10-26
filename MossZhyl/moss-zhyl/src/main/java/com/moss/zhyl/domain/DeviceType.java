package com.moss.zhyl.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.BaseEntity;

/**
 * 设备类型对象 yl_device_type
 * 
 * @author YY
 * @date 2024-10-26
 */
public class DeviceType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long typeId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceType;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDeviceType(String deviceType) 
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType() 
    {
        return deviceType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("name", getName())
            .append("deviceType", getDeviceType())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
