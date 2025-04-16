package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.TagManagementMapper;
import com.moss.zhyl.domain.TagManagement;
import com.moss.zhyl.service.ITagManagementService;

/**
 * 标签Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class TagManagementServiceImpl implements ITagManagementService 
{
    @Autowired
    private TagManagementMapper tagManagementMapper;

    /**
     * 查询标签
     * 
     * @param tagId 标签主键
     * @return 标签
     */
    @Override
    public TagManagement selectTagManagementByTagId(Long tagId)
    {
        return tagManagementMapper.selectTagManagementByTagId(tagId);
    }

    /**
     * 查询标签列表
     * 
     * @param tagManagement 标签
     * @return 标签
     */
    @Override
    public List<TagManagement> selectTagManagementList(TagManagement tagManagement)
    {
        return tagManagementMapper.selectTagManagementList(tagManagement);
    }

    /**
     * 新增标签
     * 
     * @param tagManagement 标签
     * @return 结果
     */
    @Override
    public int insertTagManagement(TagManagement tagManagement)
    {
        tagManagement.setCreateBy(SecurityUtils.getUsername());
        tagManagement.setUpdateBy(SecurityUtils.getUsername());
        tagManagement.setCreateTime(DateUtils.getNowDate());
        return tagManagementMapper.insertTagManagement(tagManagement);
    }

    /**
     * 修改标签
     * 
     * @param tagManagement 标签
     * @return 结果
     */
    @Override
    public int updateTagManagement(TagManagement tagManagement)
    {
        tagManagement.setUpdateTime(DateUtils.getNowDate());
        tagManagement.setUpdateBy(SecurityUtils.getUsername());
        return tagManagementMapper.updateTagManagement(tagManagement);
    }

    /**
     * 批量删除标签
     * 
     * @param tagIds 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteTagManagementByTagIds(Long[] tagIds)
    {
        return tagManagementMapper.deleteTagManagementByTagIds(tagIds);
    }

    /**
     * 删除标签信息
     * 
     * @param tagId 标签主键
     * @return 结果
     */
    @Override
    public int deleteTagManagementByTagId(Long tagId)
    {
        return tagManagementMapper.deleteTagManagementByTagId(tagId);
    }
}
