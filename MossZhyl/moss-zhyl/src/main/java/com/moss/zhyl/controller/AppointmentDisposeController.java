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
import com.moss.zhyl.domain.AppointmentDispose;
import com.moss.zhyl.service.IAppointmentDisposeService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 预约处理Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/appointmentDispose")
public class AppointmentDisposeController extends BaseController
{
    @Autowired
    private IAppointmentDisposeService appointmentDisposeService;

    /**
     * 查询预约处理列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:appointmentDispose:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppointmentDispose appointmentDispose)
    {
        startPage();
        List<AppointmentDispose> list = appointmentDisposeService.selectAppointmentDisposeList(appointmentDispose);
        return getDataTable(list);
    }

    /**
     * 导出预约处理列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:appointmentDispose:export')")
    @Log(title = "预约处理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppointmentDispose appointmentDispose)
    {
        List<AppointmentDispose> list = appointmentDisposeService.selectAppointmentDisposeList(appointmentDispose);
        ExcelUtil<AppointmentDispose> util = new ExcelUtil<AppointmentDispose>(AppointmentDispose.class);
        util.exportExcel(response, list, "预约处理数据");
    }

    /**
     * 获取预约处理详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:appointmentDispose:query')")
    @GetMapping(value = "/{disposeId}")
    public AjaxResult getInfo(@PathVariable("disposeId") Long disposeId)
    {
        return success(appointmentDisposeService.selectAppointmentDisposeByDisposeId(disposeId));
    }

    /**
     * 新增预约处理
     */
    @PreAuthorize("@ss.hasPermi('zhyl:appointmentDispose:add')")
    @Log(title = "预约处理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppointmentDispose appointmentDispose)
    {
        return toAjax(appointmentDisposeService.insertAppointmentDispose(appointmentDispose));
    }

    /**
     * 修改预约处理
     */
    @PreAuthorize("@ss.hasPermi('zhyl:appointmentDispose:edit')")
    @Log(title = "预约处理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppointmentDispose appointmentDispose)
    {
        return toAjax(appointmentDisposeService.updateAppointmentDispose(appointmentDispose));
    }

    /**
     * 删除预约处理
     */
    @PreAuthorize("@ss.hasPermi('zhyl:appointmentDispose:remove')")
    @Log(title = "预约处理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{disposeIds}")
    public AjaxResult remove(@PathVariable Long[] disposeIds)
    {
        return toAjax(appointmentDisposeService.deleteAppointmentDisposeByDisposeIds(disposeIds));
    }
}
