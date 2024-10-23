package com.moss.zhyl.mapper;

import java.util.List;
import com.moss.zhyl.domain.TagManagement;

/**
 * 标签Mapper接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface TagManagementMapper 
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
     * 删除标签
     * 
     * @param tagId 标签主键
     * @return 结果
     */
    public int deleteTagManagementByTagId(Long tagId);

    /**
     * 批量删除标签
     * 
     * @param tagIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTagManagementByTagIds(Long[] tagIds);
}
