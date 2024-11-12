package com.moss.zhyl.service;

import java.util.List;
import com.moss.zhyl.domain.healthReport.HealthReport;

/**
 * 健康报告Service接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface IHealthReportService 
{
    /**
     * 查询健康报告
     * 
     * @param healthReportId 健康报告主键
     * @return 健康报告
     */
    public HealthReport selectHealthReportByHealthReportId(Long healthReportId);

    /**
     * 查询健康报告列表
     * 
     * @param healthReport 健康报告
     * @return 健康报告集合
     */
    public List<HealthReport> selectHealthReportList(HealthReport healthReport);

    /**
     * 新增健康报告
     * 
     * @param healthReport 健康报告
     * @return 结果
     */
    public int insertHealthReport(HealthReport healthReport);

    /**
     * 修改健康报告
     * 
     * @param healthReport 健康报告
     * @return 结果
     */
    public int updateHealthReport(HealthReport healthReport);

    /**
     * 批量删除健康报告
     * 
     * @param healthReportIds 需要删除的健康报告主键集合
     * @return 结果
     */
    public int deleteHealthReportByHealthReportIds(Long[] healthReportIds);

    /**
     * 删除健康报告信息
     * 
     * @param healthReportId 健康报告主键
     * @return 结果
     */
    public int deleteHealthReportByHealthReportId(Long healthReportId);

    /**
     * 生成健康报告
     */
}
