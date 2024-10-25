package com.moss.zhyl.service;

import java.util.List;

import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.domain.dto.UserInfoElderlyDto;

/**
 * 用户信息Service接口
 *
 * @author YY
 * @date 2024-10-23
 */
public interface IUserInfoService {
    /**
     * 查询用户信息
     *
     * @param userInfoId 用户信息主键
     * @return 用户信息
     */
    public UserInfoElderlyDto selectUserInfoByUserInfoIdResultDto(Long userInfoId);

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
    public int insertUserInfo(UserInfoElderlyDto userInfo);

    /**
     * 修改用户信息
     *
     * @param userInfoElderlyDto 用户信息
     * @return 结果
     */
    public int updateUserInfo(UserInfoElderlyDto userInfoElderlyDto);

    /**
     * 批量删除用户信息
     *
     * @param userInfoIds 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteUserInfoByUserInfoIds(Long[] userInfoIds);

    /**
     * 删除用户信息信息
     *
     * @param userInfoId 用户信息主键
     * @return 结果
     */
    public int deleteUserInfoByUserInfoId(Long userInfoId);

    /**
     * @description: 重置密码
     * @author: YY
     * @method: resetPwd
     * @date: 2024/10/24 22:39
     * @param:
     * @param: user
     * @return: int
     **/
    int resetPwd(UserInfo user);

    /**
     * @description: 根据手机号查询用户
     * @author: YY
     * @method: selectUserInfoByPhone
     * @date: 2024/10/24 23:02
     * @param:
     * @param: contactPhone
     * @return: com.moss.zhyl.domain.UserInfo
     **/
    public UserInfo selectUserInfoByPhone(String contactPhone);

    /**
     * @description: 根据身份证查询用户
     * @author: YY
     * @method: selectUserInfoByIdCard
     * @date: 2024/10/24 23:03
     * @param:
     * @param: idCard
     * @return: com.moss.zhyl.domain.UserInfo
     **/
    public UserInfo selectUserInfoByIdCard(String idCard);

    /**
     * @description: 返回用户信息
     * @author: YY
     * @method: selectUserInfoByUserInfoIdResultUserInfo
     * @date: 2024/10/25 11:15
     * @param:
     * @param: userInfoId
     * @return: com.moss.zhyl.domain.UserInfo
     **/
    UserInfo selectUserInfoByUserInfoIdResultUserInfo(Long userInfoId);
}
