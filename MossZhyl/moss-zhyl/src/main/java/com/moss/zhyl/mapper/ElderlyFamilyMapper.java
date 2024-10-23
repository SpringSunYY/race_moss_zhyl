package com.moss.zhyl.mapper;

import java.util.List;
import com.moss.zhyl.domain.ElderlyFamily;

/**
 * 家属信息Mapper接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface ElderlyFamilyMapper 
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
     * 删除家属信息
     * 
     * @param elderlyFamilyId 家属信息主键
     * @return 结果
     */
    public int deleteElderlyFamilyByElderlyFamilyId(Long elderlyFamilyId);

    /**
     * 批量删除家属信息
     * 
     * @param elderlyFamilyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteElderlyFamilyByElderlyFamilyIds(Long[] elderlyFamilyIds);
}
