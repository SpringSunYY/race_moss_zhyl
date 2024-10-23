package com.moss.zhyl.service;

import java.util.List;
import com.moss.zhyl.domain.TagManagement;

/**
 * 标签Service接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface ITagManagementService 
{
    /**
     * 查询标签
     * 
     * @param tagId 标签主键
     * @return 标签
     */
    public TagManagement selectTagManagementByTagId(Long tagId);

    /**
     * 查询标签列表
     * 
     * @param tagManagement 标签
     * @return 标签集合
     */
    public List<TagManagement> selectTagManagementList(TagManagement tagManagement);

    /**
     * 新增标签
     * 
     * @param tagManagement 标签
     * @return 结果
     */
    public int insertTagManagement(TagManagement tagManagement);

    /**
     * 修改标签
     * 
     * @param tagManagement 标签
     * @return 结果
     */
    public int updateTagManagement(TagManagement tagManagement);

    /**
     * 批量删除标签
     * 
     * @param tagIds 需要删除的标签主键集合
     * @return 结果
     */
    public int deleteTagManagementByTagIds(Long[] tagIds);

    /**
     * 删除标签信息
     * 
     * @param tagId 标签主键
     * @return 结果
     */
    public int deleteTagManagementByTagId(Long tagId);
}
