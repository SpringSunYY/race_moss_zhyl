package com.moss.quartz.task;

import com.moss.zhyl.service.IAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Project: MossZhyl
 * Package: com.moss.quartz.task
 * Author: YY
 * CreateTime: 2024-11-01  15:20
 * Description: ReportTask
 * 健康报告
 * Version: 1.0
 */
@Component("reportTask")
public class ReportTask {
    @Autowired
    private IAIService aiService;

    /**
     * description: ai生成健康报告
     * author: YY
     * method: AIGenerateHealthReport
     * date: 2024/11/1 15:21
     * param:
     *
     * @param: threads 线程数
     * return: void
     **/
    public void AIGenerateHealthReport(Integer threads) {
        System.out.println("threads = " + threads);
        aiService.AIGenerateHealthReport(threads);
    }
}
