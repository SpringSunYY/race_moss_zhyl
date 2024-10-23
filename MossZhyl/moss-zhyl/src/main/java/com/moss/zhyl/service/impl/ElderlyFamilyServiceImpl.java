package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.ElderlyFamilyMapper;
import com.moss.zhyl.domain.ElderlyFamily;
import com.moss.zhyl.service.IElderlyFamilyService;

/**
 * 家属信息Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class ElderlyFamilyServiceImpl implements IElderlyFamilyService 
{
    @Autowired
    private ElderlyFamilyMapper elderlyFamilyMapper;

    /**
     * 查询家属信息
     * 
     * @param elderlyFamilyId 家属信息主键
     * @return 家属信息
     */
    @Override
    public ElderlyFamily selectElderlyFamilyByElderlyFamilyId(Long elderlyFamilyId)
    {
        return elderlyFamilyMapper.selectElderlyFamilyByElderlyFamilyId(elderlyFamilyId);
    }

    /**
     * 查询家属信息列表
     * 
     * @param elderlyFamily 家属信息
     * @return 家属信息
     */
    @Override
    public List<ElderlyFamily> selectElderlyFamilyList(ElderlyFamily elderlyFamily)
    {
        return elderlyFamilyMapper.selectElderlyFamilyList(elderlyFamily);
    }

    /**
     * 新增家属信息
     * 
     * @param elderlyFamily 家属信息
     * @return 结果
     */
    @Override
    public int insertElderlyFamily(ElderlyFamily elderlyFamily)
    {
        elderlyFamily.setCreateTime(DateUtils.getNowDate());
        return elderlyFamilyMapper.insertElderlyFamily(elderlyFamily);
    }

    /**
     * 修改家属信息
     * 
     * @param elderlyFamily 家属信息
     * @return 结果
     */
    @Override
    public int updateElderlyFamily(ElderlyFamily elderlyFamily)
    {
        elderlyFamily.setUpdateTime(DateUtils.getNowDate());
        return elderlyFamilyMapper.updateElderlyFamily(elderlyFamily);
    }

    /**
     * 批量删除家属信息
     * 
     * @param elderlyFamilyIds 需要删除的家属信息主键
     * @return 结果
     */
    @Override
    public int deleteElderlyFamilyByElderlyFamilyIds(Long[] elderlyFamilyIds)
    {
        return elderlyFamilyMapper.deleteElderlyFamilyByElderlyFamilyIds(elderlyFamilyIds);
    }

    /**
     * 删除家属信息信息
     * 
     * @param elderlyFamilyId 家属信息主键
     * @return 结果
     */
    @Override
    public int deleteElderlyFamilyByElderlyFamilyId(Long elderlyFamilyId)
    {
        return elderlyFamilyMapper.deleteElderlyFamilyByElderlyFamilyId(elderlyFamilyId);
    }
}
