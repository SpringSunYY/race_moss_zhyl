package com.moss.zhyl.service;

import java.util.List;

import com.moss.common.core.domain.AjaxResult;
import com.moss.common.core.domain.entity.UserInfo;
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
     * @return: com.moss.common.core.domain.entity.UserInfo
     **/
    public UserInfo selectUserInfoByPhone(String contactPhone);

    /**
     * @description: 根据身份证查询用户
     * @author: YY
     * @method: selectUserInfoByIdCard
     * @date: 2024/10/24 23:03
     * @param:
     * @param: idCard
     * @return: com.moss.common.core.domain.entity.UserInfo
     **/
    public UserInfo selectUserInfoByIdCard(String idCard);

    /**
     * @description: 返回用户信息
     * @author: YY
     * @method: selectUserInfoByUserInfoIdResultUserInfo
     * @date: 2024/10/25 11:15
     * @param:
     * @param: userInfoId
     * @return: com.moss.common.core.domain.entity.UserInfo
     **/
    UserInfo selectUserInfoByUserInfoIdResultUserInfo(Long userInfoId);

    /**
     * description: 根据unionId和openId查询用户
     * author: YY
     * method: selectUserInfoByUnionIdOrOpenId
     * date: 2024/11/6 20:38
     * param:
     * return: com.moss.common.core.domain.entity.UserInfo
     **/
    UserInfo selectUserInfoByUnionIdOrOpenId(String unionId,String openId);

    /**
     * description: 绑定用户信息，相关微信
     * author: YY
     * method: bindingByUnionid
     * date: 2024/11/7 17:09
     * param:
     * @param: phoneNumber
     * @param: captcha
     * @param: idCard
     * return: int
     **/
    int bindingByUnionid(String phoneNumber, String captcha, String idCard);
}
