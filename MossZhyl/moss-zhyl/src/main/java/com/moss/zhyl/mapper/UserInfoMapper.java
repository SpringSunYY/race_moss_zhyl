package com.moss.zhyl.mapper;

import java.util.List;

import com.moss.zhyl.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息Mapper接口
 *
 * @author YY
 * @date 2024-10-23
 */
public interface UserInfoMapper {
    /**
     * 查询用户信息
     *
     * @param userInfoId 用户信息主键
     * @return 用户信息
     */
    public UserInfo selectUserInfoByUserInfoId(Long userInfoId);

    /**
     * 查询用户信息列表
     *
     * @param userInfo 用户信息
     * @return 用户信息集合
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 新增用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    public int insertUserInfo(UserInfo userInfo);

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    public int updateUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     *
     * @param userInfoId 用户信息主键
     * @return 结果
     */
    public int deleteUserInfoByUserInfoId(Long userInfoId);

    /**
     * 批量删除用户信息
     *
     * @param userInfoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserInfoByUserInfoIds(Long[] userInfoIds);

    UserInfo selectUserInfoByPhone(@Param("contactPhone") String contactPhone, @Param("delFlag") String delFlag);

    UserInfo selectUserInfoByIdCard(@Param("idCard") String idCard, @Param("delFlag") String delFlag);
}
