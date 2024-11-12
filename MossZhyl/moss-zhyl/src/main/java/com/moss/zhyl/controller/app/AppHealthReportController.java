package com.moss.zhyl.controller.app;

import com.moss.common.core.controller.BaseController;
import com.moss.common.core.domain.AjaxResult;
import com.moss.common.core.page.TableDataInfo;
import com.moss.common.utils.SecurityUtils;
import com.moss.zhyl.domain.healthReport.HealthReport;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.domain.healthReport.reportReturn.HealthReportData;
import com.moss.zhyl.mapper.HealthReportMapper;
import com.moss.zhyl.service.IHealthReportService;
import com.moss.zpai.model.enums.AsyncReportTaskStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 健康报告Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/app/zhyl/healthReport")
public class AppHealthReportController extends BaseController
{
    @Autowired
    private IHealthReportService healthReportService;

    @Autowired
    private HealthReportMapper healthReportMapper;

    /**
     * 查询健康报告列表
     */
    @PreAuthorize("@ss.hasUserAnyRole('elderly,elderly_family')")
    @GetMapping("/list")
    public TableDataInfo healthReportList(HealthReport healthReport)
    {
        startPage();
        healthReport.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        healthReport.setUserInfoId(SecurityUtils.getLoginUserInfo().getUserInfoId());
        healthReport.setTaskStatus(AsyncReportTaskStatusEnum.HEALTH_REPORT_TASK_STATUS_SUCCESS.getValue());
        List<HealthReport> list = healthReportMapper.selectHealthReportList(healthReport);
        for (HealthReport report : list) {
            report.setUseModel(null);
            report.setUseTokens(null);
            report.setReportReturn(null);
            report.setReportContent(null);
            report.setTaskId(null);
        }
        return getDataTable(list);
    }

    /**
     * 获取健康报告详细信息
     */
    @PreAuthorize("@ss.hasUserAnyRole('elderly,elderly_family')")
    @GetMapping(value = "/{healthReportId}")
    public AjaxResult getInfo(@PathVariable("healthReportId") Long healthReportId)
    {
        HealthReport healthReport = healthReportService.selectHealthReportByHealthReportId(healthReportId);
        HealthReportData healthReportData = new HealthReportData();
        HealthReportData data = healthReportData.jsonFormatObject(healthReport.getReportReturn());
        healthReport.setReportReturnData(data);
        return success(healthReport);
    }
}
