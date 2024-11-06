package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.exception.ServiceException;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.zhyl.domain.dto.UserInfoElderlyDto;
import com.moss.zhyl.domain.enums.UserInfoRoleEnum;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.ElderlyFamilyMapper;
import com.moss.zhyl.domain.ElderlyFamily;
import com.moss.zhyl.service.IElderlyFamilyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 家属信息Service业务层处理
 *
 * @author YY
 * @date 2024-10-23
 */
@Service
public class ElderlyFamilyServiceImpl implements IElderlyFamilyService {
    @Autowired
    private ElderlyFamilyMapper elderlyFamilyMapper;

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 查询家属信息
     *
     * @param elderlyFamilyId 家属信息主键
     * @return 家属信息
     */
    @Override
    public ElderlyFamily selectElderlyFamilyByElderlyFamilyId(Long elderlyFamilyId) {
        return elderlyFamilyMapper.selectElderlyFamilyByElderlyFamilyId(elderlyFamilyId);
    }

    /**
     * 查询家属信息列表
     *
     * @param elderlyFamily 家属信息
     * @return 家属信息
     */
    @Override
    public List<ElderlyFamily> selectElderlyFamilyList(ElderlyFamily elderlyFamily) {
        List<ElderlyFamily> elderlyFamilies = elderlyFamilyMapper.selectElderlyFamilyList(elderlyFamily);
        for (ElderlyFamily info : elderlyFamilies) {
            UserInfo userInfo = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(info.getUserInfoId());
            if (StringUtils.isNotNull(userInfo)) {
                info.setUserInfoName(userInfo.getUserInfoName());
            }
            UserInfo userInfoElderly = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(info.getUserInfoElderlyId());
            if (StringUtils.isNotNull(userInfoElderly)) {
                info.setUserInfoElderlyName(userInfoElderly.getUserInfoName());
            }
        }
        return elderlyFamilies;
    }

    /**
     * 新增家属信息
     *
     * @param elderlyFamily 家属信息
     * @return 结果
     */
    @Override
    public int insertElderlyFamily(ElderlyFamily elderlyFamily) {
        //根据用户id和长者id判断是否已经存在，存在直接更新
        ElderlyFamily familyQuery = new ElderlyFamily();
        familyQuery.setUserInfoId(elderlyFamily.getUserInfoId());
        familyQuery.setUserInfoElderlyId(elderlyFamily.getUserInfoElderlyId());
        List<ElderlyFamily> elderlyFamilies = elderlyFamilyMapper.selectElderlyFamilyList(familyQuery);
        //如果存在就更新
        if (StringUtils.isNotEmpty(elderlyFamilies)) {
            elderlyFamily.setElderlyFamilyId(elderlyFamilies.get(0).getElderlyFamilyId());
            return this.updateElderlyFamily(elderlyFamily);
        }

        elderlyFamily.setElderlyFamilyId(IdUtils.snowflakeId());
        elderlyFamily.setCreateBy(SecurityUtils.getUsername());
        return elderlyFamilyMapper.insertElderlyFamily(elderlyFamily);
    }

    /**
     * 修改家属信息
     *
     * @param elderlyFamily 家属信息
     * @return 结果
     */
    @Override
    public int updateElderlyFamily(ElderlyFamily elderlyFamily) {
        return elderlyFamilyMapper.updateElderlyFamily(elderlyFamily);
    }

    /**
     * 批量删除家属信息
     *
     * @param elderlyFamilyIds 需要删除的家属信息主键
     * @return 结果
     */
    @Override
    public int deleteElderlyFamilyByElderlyFamilyIds(Long[] elderlyFamilyIds) {
        return elderlyFamilyMapper.deleteElderlyFamilyByElderlyFamilyIds(elderlyFamilyIds);
    }

    /**
     * 删除家属信息信息
     *
     * @param elderlyFamilyId 家属信息主键
     * @return 结果
     */
    @Override
    public int deleteElderlyFamilyByElderlyFamilyId(Long elderlyFamilyId) {
        return elderlyFamilyMapper.deleteElderlyFamilyByElderlyFamilyId(elderlyFamilyId);
    }

    @Override
    @Transactional
    public int insertElderlyFamilyByUserInfoElderlyDto(UserInfoElderlyDto userInfoElderlyDto) {
        List<ElderlyFamily> elderlyFamilyList = userInfoElderlyDto.getElderlyFamilyList();
        //如果是家属
        if (userInfoElderlyDto.getUserInfoRole().equals(UserInfoRoleEnum.ELDERLY_FAMILY.getValue())) {
            //根据家属信息删除所有的
            elderlyFamilyMapper.deleteElderlyFamilyByUserInfoId(userInfoElderlyDto.getUserInfoId());
        } else if (userInfoElderlyDto.getUserInfoRole().equals(UserInfoRoleEnum.ELDERLY.getValue())) {
            //如果是长者根据长者删除所有的信息
            elderlyFamilyMapper.deleteElderlyFamilyByUserInfoElderlyId(userInfoElderlyDto.getUserInfoId());
        } else {
            throw new ServiceException("角色不存在");
        }
        for (ElderlyFamily family : elderlyFamilyList) {
            ElderlyFamily elderlyFamily = new ElderlyFamily();
            //如果是家属 传来的用户id是用户id，长者id是遍历id
            if (userInfoElderlyDto.getUserInfoRole().equals(UserInfoRoleEnum.ELDERLY_FAMILY.getValue())) {
                elderlyFamily.setUserInfoId(userInfoElderlyDto.getUserInfoId());
                //这个传过来的id有可能是长者有可能使用户
                elderlyFamily.setUserInfoElderlyId(family.getUserInfoElderlyId());
            } else if (userInfoElderlyDto.getUserInfoRole().equals(UserInfoRoleEnum.ELDERLY.getValue())) {
               //如果是长者 传来的用户id长者id，用户id是遍历id
                //这个传过来的id有可能是长者有可能使用户
                elderlyFamily.setUserInfoId(family.getUserInfoElderlyId());
                elderlyFamily.setUserInfoElderlyId(userInfoElderlyDto.getUserInfoId());
            } else {
                throw new ServiceException("角色不存在!!!");
            }
            //获取两者的用户角色，如果一样直接报错
            Long elderlyFamilyId = family.getUserInfoElderlyId();
            System.out.println("elderlyFamilyId = " + elderlyFamilyId);
            UserInfo userInfo = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(elderlyFamilyId);
            if (userInfo.getUserInfoRole().equals(userInfoElderlyDto.getUserInfoRole())) {
                throw new ServiceException("角色相同！！！");
            }

            elderlyFamily.setRemark(family.getRemark());
            elderlyFamily.setRelationshipType(family.getRelationshipType());
            //直接插入
            this.insertElderlyFamily(elderlyFamily);
        }
        return 1;
    }
}
