package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.exception.ServiceException;
import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.domain.dto.UserInfoElderlyDto;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.ElderlyMapper;
import com.moss.zhyl.domain.Elderly;
import com.moss.zhyl.service.IElderlyService;

import static com.moss.zhyl.domain.enums.UserInfoRoleEnum.ELDERLY;

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

    @Autowired
    private IUserInfoService userInfoService;

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
        List<Elderly> elderlies = elderlyMapper.selectElderlyList(elderly);
        for (Elderly info : elderlies) {
            UserInfo userInfo = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(info.getUserInfoId());
            if (StringUtils.isNotNull(userInfo)) {
                info.setUserInfoName(userInfo.getUserInfoName());
            }
        }
        return elderlies;
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
        //获取用户判断来的是不长者
        Long userInfoId = elderly.getUserInfoId();
        UserInfo userInfo = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(userInfoId);
        if (!userInfo.getUserInfoRole().equals(ELDERLY.getValue())) {
            throw new ServiceException("此用户并不是长者！！！");
        }
        //判断是否已存在长者信息
        Elderly elderlyByUserInfo = this.selectElderlyByElderlyUserInfoId(userInfoId);
        //如果已经存在则直接更新
        if (StringUtils.isNotNull(elderlyByUserInfo)) {
           return this.updateElderlyByUserInfoId(elderly);
        }
        elderly.setCreateBy(SecurityUtils.getUsername());
        elderly.setElderlyId(IdUtils.snowflakeId());
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

    @Override
    public int updateElderlyByUserInfoId(Elderly elderly) {
        elderly.setUpdateTime(DateUtils.getNowDate());
        return elderlyMapper.updateElderlyByUserInfoId(elderly);
    }

    @Override
    public Elderly selectElderlyByElderlyUserInfoId(Long userInfoId) {
        return elderlyMapper.selectElderlyByElderlyUserInfoId(userInfoId);
    }
}
