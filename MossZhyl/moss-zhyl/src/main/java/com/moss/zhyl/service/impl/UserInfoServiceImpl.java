package com.moss.zhyl.service.impl;

import com.moss.common.exception.ServiceException;
import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.system.service.ISysConfigService;
import com.moss.zhyl.domain.Elderly;
import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.domain.dto.UserInfoElderlyDto;
import com.moss.zhyl.mapper.UserInfoMapper;
import com.moss.zhyl.service.IElderlyService;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.moss.common.constant.ConfigConstants.YL_USER_INFO_INIT_PASSWORD;
import static com.moss.common.constant.PermissionsConstants.ZHYL_USERINFO_UPDATE_PASSWORD;
import static com.moss.zhyl.domain.enums.DelFlagEnum.DEL_FLAG_0;
import static com.moss.zhyl.domain.enums.UserInfoRoleEnum.ELDERLY_FAMILY;

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

    @Autowired
    private IElderlyService elderlyService;

    /**
     * 查询用户信息
     *
     * @param userInfoId 用户信息主键
     * @return 用户信息
     */
    @Override
    public UserInfoElderlyDto selectUserInfoByUserInfoId(Long userInfoId) {
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserInfoId(userInfoId);
        //清除密码信息
        userInfo.setPassword(null);
        UserInfoElderlyDto userInfoElderlyDto = new UserInfoElderlyDto();
        BeanUtils.copyProperties(userInfo, userInfoElderlyDto);

        //如果是家属
        if (userInfoElderlyDto.getUserInfoRole().equals(ELDERLY_FAMILY.getValue())) {
            return userInfoElderlyDto;
        }
        Elderly elderly = elderlyService.selectElderlyByElderlyUserInfoId(userInfoId);
        //如果没有长者信息也直接返回
        if (StringUtils.isNull(elderly)) {
            return userInfoElderlyDto;
        }
        BeanUtils.copyProperties(elderly, userInfoElderlyDto);
        return userInfoElderlyDto;
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
     * @param userInfoElderlyDto 用户信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertUserInfo(UserInfoElderlyDto userInfoElderlyDto) {
        //加密密码
        if (StringUtils.isEmpty(userInfoElderlyDto.getPassword())) {
            String password = sysConfigService.selectConfigByKey(YL_USER_INFO_INIT_PASSWORD);
            userInfoElderlyDto.setPassword(SecurityUtils.encryptPassword(password));
        } else {
            userInfoElderlyDto.setPassword(SecurityUtils.encryptPassword(userInfoElderlyDto.getPassword()));
        }
        //根据手机号判断是否有这个用户
        if (StringUtils.isNotNull(this.selectUserInfoByPhone(userInfoElderlyDto.getContactPhone()))) {
            throw new ServiceException("此电话号码已存在！！！");
        }
        //根据身份证号查询
        if (StringUtils.isNotNull(this.selectUserInfoByIdCard(userInfoElderlyDto.getIdCard()))) {
            throw new ServiceException("此身份证已存在！！！");
        }
        Long userInfoId = IdUtils.snowflakeId();
        userInfoElderlyDto.setUserInfoId(userInfoId);
        userInfoElderlyDto.setDelFlag(DEL_FLAG_0.getValue());
        userInfoElderlyDto.setCreateBy(SecurityUtils.getUsername());
        userInfoElderlyDto.setCreateTime(DateUtils.getNowDate());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoElderlyDto, userInfo);
        //如果是家属直接添加
        if (userInfo.getUserInfoRole().equals(ELDERLY_FAMILY.getValue())) {
            return userInfoMapper.insertUserInfo(userInfo);
        }
        //是老人，先插入
        userInfoMapper.insertUserInfo(userInfo);
        Elderly elderly = new Elderly();
        BeanUtils.copyProperties(userInfoElderlyDto, elderly);
        return elderlyService.insertElderly(elderly);
    }

    /**
     * 修改用户信息
     *
     * @param userInfoElderlyDto 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(UserInfoElderlyDto userInfoElderlyDto) {
        userInfoElderlyDto.setPassword(null);
        userInfoElderlyDto.setUpdateBy(SecurityUtils.getUsername());
        userInfoElderlyDto.setUpdateTime(DateUtils.getNowDate());
        //根据手机号判断是否有这个用户
        UserInfo userInfoByPhone = this.selectUserInfoByPhone(userInfoElderlyDto.getContactPhone());
        //如果不是自己
        if (StringUtils.isNotNull(userInfoByPhone) && !userInfoByPhone.getUserInfoId().equals(userInfoElderlyDto.getUserInfoId())) {
            throw new ServiceException("此电话号码已存在！！！");
        }
        //根据身份证号查询
        UserInfo userInfoByIdCard = this.selectUserInfoByIdCard(userInfoElderlyDto.getIdCard());
        //如果不是自己
        if (StringUtils.isNotNull(userInfoByIdCard) && !userInfoByIdCard.getUserInfoId().equals(userInfoElderlyDto.getUserInfoId())) {
            throw new ServiceException("此身份证已存在！！！");
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoElderlyDto, userInfo);
        //如果是家属直接更新
        if (userInfo.getUserInfoRole().equals(ELDERLY_FAMILY.getValue())) {
            return userInfoMapper.updateUserInfo(userInfo);
        }
        //是老人，先更新
        userInfoMapper.updateUserInfo(userInfo);
        Elderly elderly = new Elderly();
        BeanUtils.copyProperties(userInfoElderlyDto, elderly);
        //先根据用户信息查询到是否有长者信息
        Elderly isElderly = elderlyService.selectElderlyByElderlyUserInfoId(userInfo.getUserInfoId());
        //如果不存在长者信息直接插入，如果存在则更新
        if (StringUtils.isNull(isElderly)) {
            return elderlyService.insertElderly(elderly);
        }
        return elderlyService.updateElderlyByUserInfoId(elderly);
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
        //先初始化为0
        return userInfoMapper.selectUserInfoByPhone(contactPhone, DEL_FLAG_0.getValue());
    }

    @Override
    public UserInfo selectUserInfoByIdCard(String idCard) {
        //先初始化为0
        return userInfoMapper.selectUserInfoByIdCard(idCard, DEL_FLAG_0.getValue());
    }
}
