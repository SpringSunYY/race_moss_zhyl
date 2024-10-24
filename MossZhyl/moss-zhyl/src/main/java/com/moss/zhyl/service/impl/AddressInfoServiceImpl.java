package com.moss.zhyl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.moss.common.core.domain.TreeSelect;
import com.moss.common.exception.ServiceException;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.AddressInfoMapper;
import com.moss.common.core.domain.entity.AddressInfo;
import com.moss.zhyl.service.IAddressInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 地址信息Service业务层处理
 *
 * @author YY
 * @date 2024-10-23
 */
@Service
public class AddressInfoServiceImpl implements IAddressInfoService {
    @Autowired
    private AddressInfoMapper addressInfoMapper;
    @Autowired
    private TransactionAutoConfiguration.EnableTransactionManagementConfiguration enableTransactionManagementConfiguration;

    /**
     * 查询地址信息
     *
     * @param addressId 地址信息主键
     * @return 地址信息
     */
    @Override
    public AddressInfo selectAddressInfoByAddressId(Long addressId) {
        return addressInfoMapper.selectAddressInfoByAddressId(addressId);
    }

    /**
     * 查询地址信息列表
     *
     * @param addressInfo 地址信息
     * @return 地址信息
     */
    @Override
    public List<AddressInfo> selectAddressInfoList(AddressInfo addressInfo) {
        return addressInfoMapper.selectAddressInfoList(addressInfo);
    }

    /**
     * 新增地址信息
     *
     * @param addressInfo 地址信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAddressInfo(AddressInfo addressInfo) {
        addressInfo.setAddressId(IdUtils.snowflakeId());
        //先插入
        int i = addressInfoMapper.insertAddressInfo(addressInfo);
        //在更新，得到祖级列表
        updateAddressInfo(addressInfo);
        return i;
    }

    /**
     * 修改地址信息
     *
     * @param addressInfo 地址信息
     * @return 结果
     */
    @Override
    public int updateAddressInfo(AddressInfo addressInfo) {
        if (addressInfo.getParentId().equals(addressInfo.getAddressId())) {
            throw new ServiceException("请检查父级是否正确！！！");
        }
        AddressInfo parentInfo = addressInfoMapper.selectAddressInfoByAddressId(addressInfo.getParentId());
        addressInfo.setAncestors(getParentIds(parentInfo, new StringBuilder(parentInfo.getAddressId().toString())));
        return addressInfoMapper.updateAddressInfo(addressInfo);
    }


    /**
     * 获取类型父类ID，返回以逗号分隔的字符串
     */
    private String getParentIds(AddressInfo addressInfo, StringBuilder ids) {
        if (addressInfo == null || addressInfo.getParentId() == null) {
            // 如果没有父类或者parentId为0，表示已到顶级节点，返回当前ids（可能为空）
            return ids.toString();
        }

        // 获取当前父类信息
        AddressInfo parentInfo = addressInfoMapper.selectAddressInfoByAddressId(addressInfo.getParentId());
        if (parentInfo != null) {
            // 在递归之前添加父类ID，这样顶级父类会排在最前面
            if (ids.length() > 0) {
                ids.insert(0, ",");
            }
            ids.insert(0, parentInfo.getAddressId());

            // 递归获取更上层的父类ID
            getParentIds(parentInfo, ids);
        }

        // 如果当前节点的父类ID为0，说明它是顶级父类，确保顶级ID（0）在最前面
        if (addressInfo.getParentId().equals(0L)) {
            if (ids.length() > 0) {
                ids.insert(0, ",");
            }
            ids.insert(0, "0"); // 在最前面插入顶级父类ID
        }

        // 返回包含所有父类ID的字符串
        return ids.toString();
    }


    /**
     * 批量删除地址信息
     *
     * @param addressIds 需要删除的地址信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressInfoByAddressIds(Long[] addressIds) {
        return addressInfoMapper.deleteAddressInfoByAddressIds(addressIds);
    }

    /**
     * 删除地址信息信息
     *
     * @param addressId 地址信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressInfoByAddressId(Long addressId) {
        return addressInfoMapper.deleteAddressInfoByAddressId(addressId);
    }

    @Override
    public List<TreeSelect> addressTree(AddressInfo addressInfo) {
        List<AddressInfo> list = selectAddressInfoList(addressInfo);
        return buildTypeTreeSelect(list);
    }

    /**
     * @description: 创建树形结构
     * @author: YY
     * @method: buildTypeTreeSelect
     * @date: 2024/10/24 11:57
     * @param:
     * @param: list
     * @return: java.util.List<com.moss.common.core.domain.TreeSelect>
     **/
    private List<TreeSelect> buildTypeTreeSelect(List<AddressInfo> list) {
        List<AddressInfo> propertyTypeInfos = buildAddressTree(list);
        return propertyTypeInfos.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * @description: 构建树
     * @author: YY
     * @method: buildAddressTree
     * @date: 2024/10/24 11:59
     * @param:
     * @param: addressInfoList
     * @return: java.util.List<com.moss.common.core.domain.entity.AddressInfo>
     **/
    private List<AddressInfo> buildAddressTree(List<AddressInfo> addressInfoList) {
        List<AddressInfo> addressInfos = new ArrayList<>();
        List<Long> addressIds = addressInfoList.stream()
                .map(AddressInfo::getAddressId)
                .collect(Collectors.toList());
        for (AddressInfo addressInfo : addressInfoList) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!addressIds.contains(addressInfo.getParentId())) {
                recursionFn(addressInfoList, addressInfo);
                addressInfos.add(addressInfo);
            }
        }
        if (addressInfos.isEmpty()) {
            addressInfos = addressInfoList;
        }
        return addressInfos;
    }

    /**
     * @description: 递归列表
     * @author: YY
     * @method: recursionFn
     * @date: 2024/10/24 12:01
     * @param:
     * @param: addressInfoList
     * @param: addressInfo
     * @return: void
     **/
    private void recursionFn(List<AddressInfo> list, AddressInfo t) {
        // 得到子节点列表
        List<AddressInfo> childList = getChildList(list, t);
        t.setChildren(childList);
        for (AddressInfo tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<AddressInfo> list, AddressInfo t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 得到子节点列表
     */
    private List<AddressInfo> getChildList(List<AddressInfo> list, AddressInfo t) {
        List<AddressInfo> tlist = new ArrayList<AddressInfo>();
        Iterator<AddressInfo> it = list.iterator();
        while (it.hasNext()) {
            AddressInfo n = (AddressInfo) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getAddressId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }
}
