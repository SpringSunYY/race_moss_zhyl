package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.exception.ServiceException;
import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.system.service.ISysConfigService;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.UserInfoMapper;
import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.service.IUserInfoService;

import static com.moss.common.constant.ConfigConstants.YL_USER_INFO_INIT_PASSWORD;
import static com.moss.common.constant.PermissionsConstants.ZHYL_USERINFO_UPDATE_PASSWORD;

/**
 * 用户信息Service业务层处理
 *
 * @author YY
 * @date 2024-10-23
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 查询用户信息
     *
     * @param userInfoId 用户信息主键
     * @return 用户信息
     */
    @Override
    public UserInfo selectUserInfoByUserInfoId(Long userInfoId) {
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserInfoId(userInfoId);
        //清除密码信息
        userInfo.setPassword(null);
        return userInfo;
    }

    /**
     * 查询用户信息列表
     *
     * @param userInfo 用户信息
     * @return 用户信息
     */
    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo) {
        List<UserInfo> userInfos = userInfoMapper.selectUserInfoList(userInfo);
        for (UserInfo info : userInfos) {
            info.setPassword(null);
        }
        return userInfos;
    }

    /**
     * 新增用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    @Override
    public int insertUserInfo(UserInfo userInfo) {
        //加密密码
        if (StringUtils.isEmpty(userInfo.getPassword())) {
            String password = sysConfigService.selectConfigByKey(YL_USER_INFO_INIT_PASSWORD);
            userInfo.setPassword(SecurityUtils.encryptPassword(password));
        } else {
            userInfo.setPassword(SecurityUtils.encryptPassword(userInfo.getPassword()));
        }
        //根据手机号判断是否有这个用户
        if (StringUtils.isNotNull(this.selectUserInfoByPhone(userInfo.getContactPhone()))) {
            throw new ServiceException("此电话号码已存在！！！");
        }
        //根据身份证号查询
        if (StringUtils.isNotNull(this.selectUserInfoByIdCard(userInfo.getIdCard()))) {
            throw new ServiceException("此身份证已存在！！！");
        }
        userInfo.setUserInfoId(IdUtils.snowflakeId());
        userInfo.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        userInfo.setCreateBy(SecurityUtils.getUsername());
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
    public int updateUserInfo(UserInfo userInfo) {
        userInfo.setPassword(null);
        userInfo.setUpdateBy(SecurityUtils.getUsername());
        userInfo.setUpdateTime(DateUtils.getNowDate());
        //根据手机号判断是否有这个用户
        UserInfo userInfoByPhone = this.selectUserInfoByPhone(userInfo.getContactPhone());
        //如果不是自己
        if (StringUtils.isNotNull(userInfoByPhone) && !userInfoByPhone.getUserInfoId().equals(userInfo.getUserInfoId())) {
            throw new ServiceException("此电话号码已存在！！！");
        }
        //根据身份证号查询
        UserInfo userInfoByIdCard = this.selectUserInfoByIdCard(userInfo.getIdCard());
        //如果不是自己
        if (StringUtils.isNotNull(userInfoByIdCard) && !userInfoByIdCard.getUserInfoId().equals(userInfo.getUserInfoId())) {
            throw new ServiceException("此身份证已存在！！！");
        }
        return userInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 批量删除用户信息
     *
     * @param userInfoIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoByUserInfoIds(Long[] userInfoIds) {
        return userInfoMapper.deleteUserInfoByUserInfoIds(userInfoIds);
    }

    /**
     * 删除用户信息信息
     *
     * @param userInfoId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoByUserInfoId(Long userInfoId) {
        return userInfoMapper.deleteUserInfoByUserInfoId(userInfoId);
    }

    @Override
    public int resetPwd(UserInfo userInfo) {
        //如果有更新用户密码权限,直接重置
        if (SecurityUtils.hasPermi(ZHYL_USERINFO_UPDATE_PASSWORD)) {
            //更新密码
            String password = sysConfigService.selectConfigByKey(YL_USER_INFO_INIT_PASSWORD);
            userInfo.setPassword(SecurityUtils.encryptPassword(password));
            return userInfoMapper.updateUserInfo(userInfo);
        }
        //如果不是是自己
        if (!SecurityUtils.getUserId().equals(userInfo.getUserInfoId())) {
            throw new ServiceException("用户校验失败！！！");
        }
        //如果是自己
        //更新密码 TODO 后续根据实际情况优化
        String password = sysConfigService.selectConfigByKey(YL_USER_INFO_INIT_PASSWORD);
        userInfo.setPassword(SecurityUtils.encryptPassword(password));
        return userInfoMapper.updateUserInfo(userInfo);
    }

    @Override
    public UserInfo selectUserInfoByPhone(String contactPhone) {
        return userInfoMapper.selectUserInfoByPhone(contactPhone);
    }

    @Override
    public UserInfo selectUserInfoByIdCard(String idCard) {
        return userInfoMapper.selectUserInfoByIdCard(idCard);
    }
}
