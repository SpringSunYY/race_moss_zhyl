package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.HealthReportMapper;
import com.moss.zhyl.domain.HealthReport;
import com.moss.zhyl.service.IHealthReportService;

/**
 * 健康报告Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class HealthReportServiceImpl implements IHealthReportService 
{
    @Autowired
    private HealthReportMapper healthReportMapper;

    /**
     * 查询健康报告
     * 
     * @param healthReportId 健康报告主键
     * @return 健康报告
     */
    @Override
    public HealthReport selectHealthReportByHealthReportId(Long healthReportId)
    {
        return healthReportMapper.selectHealthReportByHealthReportId(healthReportId);
    }

    /**
     * 查询健康报告列表
     * 
     * @param healthReport 健康报告
     * @return 健康报告
     */
    @Override
    public List<HealthReport> selectHealthReportList(HealthReport healthReport)
    {
        return healthReportMapper.selectHealthReportList(healthReport);
    }

    /**
     * 新增健康报告
     * 
     * @param healthReport 健康报告
     * @return 结果
     */
    @Override
    public int insertHealthReport(HealthReport healthReport)
    {
        healthReport.setCreateTime(DateUtils.getNowDate());
        return healthReportMapper.insertHealthReport(healthReport);
    }

    /**
     * 修改健康报告
     * 
     * @param healthReport 健康报告
     * @return 结果
     */
    @Override
    public int updateHealthReport(HealthReport healthReport)
    {
        return healthReportMapper.updateHealthReport(healthReport);
    }

    /**
     * 批量删除健康报告
     * 
     * @param healthReportIds 需要删除的健康报告主键
     * @return 结果
     */
    @Override
    public int deleteHealthReportByHealthReportIds(Long[] healthReportIds)
    {
        return healthReportMapper.deleteHealthReportByHealthReportIds(healthReportIds);
    }

    /**
     * 删除健康报告信息
     * 
     * @param healthReportId 健康报告主键
     * @return 结果
     */
    @Override
    public int deleteHealthReportByHealthReportId(Long healthReportId)
    {
        return healthReportMapper.deleteHealthReportByHealthReportId(healthReportId);
    }
}
