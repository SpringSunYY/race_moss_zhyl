package com.moss.zhyl.domain.healthReport;

import com.alibaba.fastjson2.JSON;
import com.moss.zhyl.domain.deviceUploadingData.Argument;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl.domain.healthReport
 * Author: YY
 * CreateTime: 2024-11-12  09:12
 * Description: reportReturn
 * Version: 1.0
 */
public abstract class ReportReturn {
    public ReportReturn jsonFormatObject(String json) {
        return JSON.parseObject(json, ReportReturn.class);
    }
}
