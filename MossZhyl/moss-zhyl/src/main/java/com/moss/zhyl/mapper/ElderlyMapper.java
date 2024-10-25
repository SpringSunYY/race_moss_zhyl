package com.moss.zhyl.mapper;

import java.util.List;
import com.moss.zhyl.domain.Elderly;
import org.apache.ibatis.annotations.Param;

/**
 * 长者信息Mapper接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface ElderlyMapper 
{
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
     * 删除长者信息
     * 
     * @param elderlyId 长者信息主键
     * @return 结果
     */
    public int deleteElderlyByElderlyId(Long elderlyId);

    /**
     * 批量删除长者信息
     * 
     * @param elderlyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteElderlyByElderlyIds(Long[] elderlyIds);

    /**
     * @description: 根据用户id更新长者信息
     * @author: YY
     * @method: updateElderlyByUserInfoId
     * @date: 2024/10/25 9:40
     * @param:
     * @param: elderly
     * @return: int
     **/
    int updateElderlyByUserInfoId(Elderly elderly);

    /**
     * @description: 根据用户id查询长者信息
     * @author: YY
     * @method: selectElderlyByElderlyUserInfoId
     * @date: 2024/10/25 9:45
     * @param:
     * @param: userInfoId
     * @return: com.moss.zhyl.domain.Elderly
     **/
    Elderly selectElderlyByElderlyUserInfoId(@Param("userInfoId") Long userInfoId);
}
