package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.UserInfoMapper;
import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.service.IUserInfoService;

/**
 * 用户信息Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService 
{
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询用户信息
     * 
     * @param userInfoId 用户信息主键
     * @return 用户信息
     */
    @Override
    public UserInfo selectUserInfoByUserInfoId(Long userInfoId)
    {
        return userInfoMapper.selectUserInfoByUserInfoId(userInfoId);
    }

    /**
     * 查询用户信息列表
     * 
     * @param userInfo 用户信息
     * @return 用户信息
     */
    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo)
    {
        return userInfoMapper.selectUserInfoList(userInfo);
    }

    /**
     * 新增用户信息
     * 
     * @param userInfo 用户信息
     * @return 结果
     */
    @Override
    public int insertUserInfo(UserInfo userInfo)
    {
        userInfo.setCreateTime(DateUtils.getNowDate());
        return userInfoMapper.insertUserInfo(userInfo);
    }

    /**
     * 修改用户信息
     * 
     * @param userInfo 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(UserInfo userInfo)
    {
        userInfo.setUpdateTime(DateUtils.getNowDate());
        return userInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 批量删除用户信息
     * 
     * @param userInfoIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoByUserInfoIds(Long[] userInfoIds)
    {
        return userInfoMapper.deleteUserInfoByUserInfoIds(userInfoIds);
    }

    /**
     * 删除用户信息信息
     * 
     * @param userInfoId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoByUserInfoId(Long userInfoId)
    {
        return userInfoMapper.deleteUserInfoByUserInfoId(userInfoId);
    }
}
