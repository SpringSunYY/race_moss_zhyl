package com.moss.zhyl.service;

/**
 * ai操作
 */
public interface IAIService {
    /**
     * description: ai生成健康报告功能
     * @author YY
     * method: AIGenerateHealthReport
     * date: 2024/11/1 14:59
     * @param threads 线程数
     * return: void
     **/
    public void AIGenerateHealthReport(Integer threads);

    /**
     * description: 获取ai生成健康报告结果
     * author: YY
     * method: GetAIGenerateHealthReportResult
     * date: 2024/11/1 15:11
     * param:
     * @param threads 线程数
     * return: void
     **/
    public void GetAIGenerateHealthReportResult(Integer threads);
}
