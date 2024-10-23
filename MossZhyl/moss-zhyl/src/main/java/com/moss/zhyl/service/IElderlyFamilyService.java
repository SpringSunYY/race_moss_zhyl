package com.moss.zhyl.service;

import java.util.List;
import com.moss.zhyl.domain.ElderlyFamily;

/**
 * 家属信息Service接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface IElderlyFamilyService 
{
    /**
     * 查询家属信息
     * 
     * @param elderlyFamilyId 家属信息主键
     * @return 家属信息
     */
    public ElderlyFamily selectElderlyFamilyByElderlyFamilyId(Long elderlyFamilyId);

    /**
     * 查询家属信息列表
     * 
     * @param elderlyFamily 家属信息
     * @return 家属信息集合
     */
    public List<ElderlyFamily> selectElderlyFamilyList(ElderlyFamily elderlyFamily);

    /**
     * 新增家属信息
     * 
     * @param elderlyFamily 家属信息
     * @return 结果
     */
    public int insertElderlyFamily(ElderlyFamily elderlyFamily);

    /**
     * 修改家属信息
     * 
     * @param elderlyFamily 家属信息
     * @return 结果
     */
    public int updateElderlyFamily(ElderlyFamily elderlyFamily);

    /**
     * 批量删除家属信息
     * 
     * @param elderlyFamilyIds 需要删除的家属信息主键集合
     * @return 结果
     */
    public int deleteElderlyFamilyByElderlyFamilyIds(Long[] elderlyFamilyIds);

    /**
     * 删除家属信息信息
     * 
     * @param elderlyFamilyId 家属信息主键
     * @return 结果
     */
    public int deleteElderlyFamilyByElderlyFamilyId(Long elderlyFamilyId);
}
