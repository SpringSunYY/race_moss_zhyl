package com.moss.common.core.domain.entity;

import com.moss.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.moss.common.annotation.Excel;
import com.moss.common.core.domain.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址信息对象 yl_address_info
 * 
 * @author YY
 * @date 2024-10-23
 */
public class AddressInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long addressId;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 级别 */
    @Excel(name = "级别")
    private String level;

    /** 经纬度 */
    @Excel(name = "经纬度")
    private String center;

    /** 父ID */
    private Long parentId;

    /** 显示顺序 */
    private Integer orderNum;

    /** 祖级列表 */
    private String ancestors;

    /** 子集 */
    private List<AddressInfo> children = new ArrayList<AddressInfo>();

    public void setAddressId(Long addressId) 
    {
        this.addressId = addressId;
    }

    public Long getAddressId() 
    {
        return addressId;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setCenter(String center) 
    {
        this.center = center;
    }

    public String getCenter() 
    {
        return center;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public List<AddressInfo> getChildren() {
        return children;
    }

    public void setChildren(List<AddressInfo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("addressId", getAddressId())
            .append("parentId", getParentId())
            .append("code", getCode())
            .append("name", getName())
            .append("level", getLevel())
            .append("center", getCenter())
            .append("remark", getRemark())
            .append("orderNum", getOrderNum())
            .append("ancestors", getAncestors())
            .toString();
    }
}
