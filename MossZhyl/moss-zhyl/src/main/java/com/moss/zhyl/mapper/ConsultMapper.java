package com.moss.zhyl.mapper;

import java.util.List;
import com.moss.zhyl.domain.Consult;

/**
 * 咨询Mapper接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface ConsultMapper 
{
    /**
     * 查询咨询
     * 
     * @param consultId 咨询主键
     * @return 咨询
     */
    public Consult selectConsultByConsultId(Long consultId);

    /**
     * 查询咨询列表
     * 
     * @param consult 咨询
     * @return 咨询集合
     */
    public List<Consult> selectConsultList(Consult consult);

    /**
     * 新增咨询
     * 
     * @param consult 咨询
     * @return 结果
     */
    public int insertConsult(Consult consult);

    /**
     * 修改咨询
     * 
     * @param consult 咨询
     * @return 结果
     */
    public int updateConsult(Consult consult);

    /**
     * 删除咨询
     * 
     * @param consultId 咨询主键
     * @return 结果
     */
    public int deleteConsultByConsultId(Long consultId);

    /**
     * 批量删除咨询
     * 
     * @param consultIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteConsultByConsultIds(Long[] consultIds);
}
