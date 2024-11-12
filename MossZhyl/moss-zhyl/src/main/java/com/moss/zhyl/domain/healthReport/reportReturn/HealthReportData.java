package com.moss.zhyl.domain.healthReport.reportReturn;

import com.alibaba.fastjson2.JSON;
import com.moss.zhyl.domain.deviceUploadingData.Argument;
import com.moss.zhyl.domain.healthReport.ReportReturn;
import lombok.Data;

import java.util.List;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl.domain.healthReport.reportReturn
 * Author: YY
 * CreateTime: 2024-11-12  09:12
 * Description: HealthReportData
 * Version: 1.0
 */
@Data
public class HealthReportData extends ReportReturn {
    private String overallAssessment;
    private DetailedAnalysis detailedAnalysis;
    private List<String> dietarySuggestions;
    private List<String> exerciseSuggestions;
    private List<String> emergencyHandling;

    @Data
    public static class DetailedAnalysis {
        private Status heartRate;
        private Status bloodPressure;
        private Status oxygen;
        private Status bloodSugar;
        private Status temperature;

        @Data
        public static class Status {
            private String currentStatus;
            private String potentialRisks;
            private List<String> suggestions;
        }
    }

    @Override
    public HealthReportData jsonFormatObject(String json) {
        return JSON.parseObject(json,HealthReportData.class);
    }
}
