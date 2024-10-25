package com.moss.zhyl.service;

import java.util.List;

import com.moss.zhyl.domain.Elderly;

/**
 * 长者信息Service接口
 *
 * @author YY
 * @date 2024-10-23
 */
public interface IElderlyService {
    /**
     * 查询长者信息
     *
     * @param elderlyId 长者信息主键
     * @return 长者信息
     */
    public Elderly selectElderlyByElderlyId(Long elderlyId);

    /**
     * 查询长者信息列表
     *
     * @param elderly 长者信息
     * @return 长者信息集合
     */
    public List<Elderly> selectElderlyList(Elderly elderly);

    /**
     * 新增长者信息
     *
     * @param elderly 长者信息
     * @return 结果
     */
    public int insertElderly(Elderly elderly);

    /**
     * 修改长者信息
     *
     * @param elderly 长者信息
     * @return 结果
     */
    public int updateElderly(Elderly elderly);

    /**
     * 批量删除长者信息
     *
     * @param elderlyIds 需要删除的长者信息主键集合
     * @return 结果
     */
    public int deleteElderlyByElderlyIds(Long[] elderlyIds);

    /**
     * 删除长者信息信息
     *
     * @param elderlyId 长者信息主键
     * @return 结果
     */
    public int deleteElderlyByElderlyId(Long elderlyId);

    /**
     * @description: 根据用户id更新长者信息
     * @author: YY
     * @method: updateElderlyByUserInfoId
     * @date: 2024/10/25 9:39
     * @param:
     * @param: elderly
     * @return: int
     **/
    int updateElderlyByUserInfoId(Elderly elderly);

    /**
     * @description: 根据用户id查询长者信息
     * @author: YY
     * @method: selectElderlyByElderlyUserInfoId
     * @date: 2024/10/25 9:44
     * @param:
     * @param: userInfoId
     * @return: com.moss.zhyl.domain.Elderly
     **/
    Elderly selectElderlyByElderlyUserInfoId(Long userInfoId);
}
