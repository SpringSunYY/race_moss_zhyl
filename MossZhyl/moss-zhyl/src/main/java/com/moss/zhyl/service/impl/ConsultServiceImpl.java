package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.uuid.IdUtils;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.ConsultMapper;
import com.moss.zhyl.domain.Consult;
import com.moss.zhyl.service.IConsultService;

/**
 * 咨询Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class ConsultServiceImpl implements IConsultService 
{
    @Autowired
    private ConsultMapper consultMapper;

    /**
     * 查询咨询
     * 
     * @param consultId 咨询主键
     * @return 咨询
     */
    @Override
    public Consult selectConsultByConsultId(Long consultId)
    {
        return consultMapper.selectConsultByConsultId(consultId);
    }

    /**
     * 查询咨询列表
     * 
     * @param consult 咨询
     * @return 咨询
     */
    @Override
    public List<Consult> selectConsultList(Consult consult)
    {
        return consultMapper.selectConsultList(consult);
    }

    /**
     * 新增咨询
     * 
     * @param consult 咨询
     * @return 结果
     */
    @Override
    public int insertConsult(Consult consult)
    {
        consult.setConsultId(IdUtils.snowflakeId());
        consult.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        consult.setCreateBy(SecurityUtils.getUsername());
        consult.setCreateTime(DateUtils.getNowDate());
        return consultMapper.insertConsult(consult);
    }

    /**
     * 修改咨询
     * 
     * @param consult 咨询
     * @return 结果
     */
    @Override
    public int updateConsult(Consult consult)
    {
        consult.setUpdateBy(SecurityUtils.getUsername());
        return consultMapper.updateConsult(consult);
    }

    /**
     * 批量删除咨询
     * 
     * @param consultIds 需要删除的咨询主键
     * @return 结果
     */
    @Override
    public int deleteConsultByConsultIds(Long[] consultIds)
    {
        return consultMapper.deleteConsultByConsultIds(consultIds);
    }

    /**
     * 删除咨询信息
     * 
     * @param consultId 咨询主键
     * @return 结果
     */
    @Override
    public int deleteConsultByConsultId(Long consultId)
    {
        return consultMapper.deleteConsultByConsultId(consultId);
    }
}
