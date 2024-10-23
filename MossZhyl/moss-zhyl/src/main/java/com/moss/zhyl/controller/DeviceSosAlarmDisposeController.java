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
import com.moss.zhyl.domain.DeviceSosAlarmDispose;
import com.moss.zhyl.service.IDeviceSosAlarmDisposeService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 设备预警处理Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/deviceSosAlarmDispose")
public class DeviceSosAlarmDisposeController extends BaseController
{
    @Autowired
    private IDeviceSosAlarmDisposeService deviceSosAlarmDisposeService;

    /**
     * 查询设备预警处理列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarmDispose:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceSosAlarmDispose deviceSosAlarmDispose)
    {
        startPage();
        List<DeviceSosAlarmDispose> list = deviceSosAlarmDisposeService.selectDeviceSosAlarmDisposeList(deviceSosAlarmDispose);
        return getDataTable(list);
    }

    /**
     * 导出设备预警处理列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarmDispose:export')")
    @Log(title = "设备预警处理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceSosAlarmDispose deviceSosAlarmDispose)
    {
        List<DeviceSosAlarmDispose> list = deviceSosAlarmDisposeService.selectDeviceSosAlarmDisposeList(deviceSosAlarmDispose);
        ExcelUtil<DeviceSosAlarmDispose> util = new ExcelUtil<DeviceSosAlarmDispose>(DeviceSosAlarmDispose.class);
        util.exportExcel(response, list, "设备预警处理数据");
    }

    /**
     * 获取设备预警处理详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarmDispose:query')")
    @GetMapping(value = "/{disposeId}")
    public AjaxResult getInfo(@PathVariable("disposeId") Long disposeId)
    {
        return success(deviceSosAlarmDisposeService.selectDeviceSosAlarmDisposeByDisposeId(disposeId));
    }

    /**
     * 新增设备预警处理
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarmDispose:add')")
    @Log(title = "设备预警处理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceSosAlarmDispose deviceSosAlarmDispose)
    {
        return toAjax(deviceSosAlarmDisposeService.insertDeviceSosAlarmDispose(deviceSosAlarmDispose));
    }

    /**
     * 修改设备预警处理
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarmDispose:edit')")
    @Log(title = "设备预警处理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceSosAlarmDispose deviceSosAlarmDispose)
    {
        return toAjax(deviceSosAlarmDisposeService.updateDeviceSosAlarmDispose(deviceSosAlarmDispose));
    }

    /**
     * 删除设备预警处理
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarmDispose:remove')")
    @Log(title = "设备预警处理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{disposeIds}")
    public AjaxResult remove(@PathVariable Long[] disposeIds)
    {
        return toAjax(deviceSosAlarmDisposeService.deleteDeviceSosAlarmDisposeByDisposeIds(disposeIds));
    }
}
