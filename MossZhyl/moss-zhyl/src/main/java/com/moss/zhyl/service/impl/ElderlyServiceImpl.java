package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.ElderlyMapper;
import com.moss.zhyl.domain.Elderly;
import com.moss.zhyl.service.IElderlyService;

/**
 * 长者信息Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class ElderlyServiceImpl implements IElderlyService 
{
    @Autowired
    private ElderlyMapper elderlyMapper;

    /**
     * 查询长者信息
     * 
     * @param elderlyId 长者信息主键
     * @return 长者信息
     */
    @Override
    public Elderly selectElderlyByElderlyId(Long elderlyId)
    {
        return elderlyMapper.selectElderlyByElderlyId(elderlyId);
    }

    /**
     * 查询长者信息列表
     * 
     * @param elderly 长者信息
     * @return 长者信息
     */
    @Override
    public List<Elderly> selectElderlyList(Elderly elderly)
    {
        return elderlyMapper.selectElderlyList(elderly);
    }

    /**
     * 新增长者信息
     * 
     * @param elderly 长者信息
     * @return 结果
     */
    @Override
    public int insertElderly(Elderly elderly)
    {
        elderly.setCreateTime(DateUtils.getNowDate());
        return elderlyMapper.insertElderly(elderly);
    }

    /**
     * 修改长者信息
     * 
     * @param elderly 长者信息
     * @return 结果
     */
    @Override
    public int updateElderly(Elderly elderly)
    {
        elderly.setUpdateTime(DateUtils.getNowDate());
        return elderlyMapper.updateElderly(elderly);
    }

    /**
     * 批量删除长者信息
     * 
     * @param elderlyIds 需要删除的长者信息主键
     * @return 结果
     */
    @Override
    public int deleteElderlyByElderlyIds(Long[] elderlyIds)
    {
        return elderlyMapper.deleteElderlyByElderlyIds(elderlyIds);
    }

    /**
     * 删除长者信息信息
     * 
     * @param elderlyId 长者信息主键
     * @return 结果
     */
    @Override
    public int deleteElderlyByElderlyId(Long elderlyId)
    {
        return elderlyMapper.deleteElderlyByElderlyId(elderlyId);
    }
}
