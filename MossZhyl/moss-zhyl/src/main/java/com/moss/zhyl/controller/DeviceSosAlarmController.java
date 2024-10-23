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
import com.moss.zhyl.domain.DeviceSosAlarm;
import com.moss.zhyl.service.IDeviceSosAlarmService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 设备预警Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/deviceSosAlarm")
public class DeviceSosAlarmController extends BaseController
{
    @Autowired
    private IDeviceSosAlarmService deviceSosAlarmService;

    /**
     * 查询设备预警列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarm:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceSosAlarm deviceSosAlarm)
    {
        startPage();
        List<DeviceSosAlarm> list = deviceSosAlarmService.selectDeviceSosAlarmList(deviceSosAlarm);
        return getDataTable(list);
    }

    /**
     * 导出设备预警列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarm:export')")
    @Log(title = "设备预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceSosAlarm deviceSosAlarm)
    {
        List<DeviceSosAlarm> list = deviceSosAlarmService.selectDeviceSosAlarmList(deviceSosAlarm);
        ExcelUtil<DeviceSosAlarm> util = new ExcelUtil<DeviceSosAlarm>(DeviceSosAlarm.class);
        util.exportExcel(response, list, "设备预警数据");
    }

    /**
     * 获取设备预警详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarm:query')")
    @GetMapping(value = "/{sosAlarmId}")
    public AjaxResult getInfo(@PathVariable("sosAlarmId") Long sosAlarmId)
    {
        return success(deviceSosAlarmService.selectDeviceSosAlarmBySosAlarmId(sosAlarmId));
    }

    /**
     * 新增设备预警
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarm:add')")
    @Log(title = "设备预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceSosAlarm deviceSosAlarm)
    {
        return toAjax(deviceSosAlarmService.insertDeviceSosAlarm(deviceSosAlarm));
    }

    /**
     * 修改设备预警
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarm:edit')")
    @Log(title = "设备预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceSosAlarm deviceSosAlarm)
    {
        return toAjax(deviceSosAlarmService.updateDeviceSosAlarm(deviceSosAlarm));
    }

    /**
     * 删除设备预警
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceSosAlarm:remove')")
    @Log(title = "设备预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sosAlarmIds}")
    public AjaxResult remove(@PathVariable Long[] sosAlarmIds)
    {
        return toAjax(deviceSosAlarmService.deleteDeviceSosAlarmBySosAlarmIds(sosAlarmIds));
    }
}
