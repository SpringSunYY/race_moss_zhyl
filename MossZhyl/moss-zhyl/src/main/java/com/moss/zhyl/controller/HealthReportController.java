package com.moss.zhyl.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moss.common.annotation.Log;
import com.moss.common.core.controller.BaseController;
import com.moss.common.core.domain.AjaxResult;
import com.moss.common.enums.BusinessType;
import com.moss.zhyl.domain.healthReport.HealthReport;
import com.moss.zhyl.service.IHealthReportService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 健康报告Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/healthReport")
public class HealthReportController extends BaseController
{
    @Autowired
    private IHealthReportService healthReportService;

    /**
     * 查询健康报告列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:healthReport:list')")
    @GetMapping("/list")
    public TableDataInfo list(HealthReport healthReport)
    {
        startPage();
        List<HealthReport> list = healthReportService.selectHealthReportList(healthReport);
        return getDataTable(list);
    }

    /**
     * 导出健康报告列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:healthReport:export')")
    @Log(title = "健康报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HealthReport healthReport)
    {
        List<HealthReport> list = healthReportService.selectHealthReportList(healthReport);
        ExcelUtil<HealthReport> util = new ExcelUtil<HealthReport>(HealthReport.class);
        util.exportExcel(response, list, "健康报告数据");
    }

    /**
     * 获取健康报告详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:healthReport:query')")
    @GetMapping(value = "/{healthReportId}")
    public AjaxResult getInfo(@PathVariable("healthReportId") Long healthReportId)
    {
        return success(healthReportService.selectHealthReportByHealthReportId(healthReportId));
    }

    /**
     * 新增健康报告
     */
    @PreAuthorize("@ss.hasPermi('zhyl:healthReport:add')")
    @Log(title = "健康报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HealthReport healthReport)
    {
        return toAjax(healthReportService.insertHealthReport(healthReport));
    }

    /**
     * 修改健康报告
     */
    @PreAuthorize("@ss.hasPermi('zhyl:healthReport:edit')")
    @Log(title = "健康报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HealthReport healthReport)
    {
        return toAjax(healthReportService.updateHealthReport(healthReport));
    }

    /**
     * 删除健康报告
     */
    @PreAuthorize("@ss.hasPermi('zhyl:healthReport:remove')")
    @Log(title = "健康报告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{healthReportIds}")
    public AjaxResult remove(@PathVariable Long[] healthReportIds)
    {
        return toAjax(healthReportService.deleteHealthReportByHealthReportIds(healthReportIds));
    }
}
