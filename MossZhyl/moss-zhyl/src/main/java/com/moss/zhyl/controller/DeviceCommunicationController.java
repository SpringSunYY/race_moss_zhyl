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
import com.moss.zhyl.domain.DeviceCommunication;
import com.moss.zhyl.service.IDeviceCommunicationService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 设备通信记录Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/deviceCommunication")
public class DeviceCommunicationController extends BaseController
{
    @Autowired
    private IDeviceCommunicationService deviceCommunicationService;

    /**
     * 查询设备通信记录列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceCommunication:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceCommunication deviceCommunication)
    {
        startPage();
        List<DeviceCommunication> list = deviceCommunicationService.selectDeviceCommunicationList(deviceCommunication);
        return getDataTable(list);
    }

    /**
     * 导出设备通信记录列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceCommunication:export')")
    @Log(title = "设备通信记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceCommunication deviceCommunication)
    {
        List<DeviceCommunication> list = deviceCommunicationService.selectDeviceCommunicationList(deviceCommunication);
        ExcelUtil<DeviceCommunication> util = new ExcelUtil<DeviceCommunication>(DeviceCommunication.class);
        util.exportExcel(response, list, "设备通信记录数据");
    }

    /**
     * 获取设备通信记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceCommunication:query')")
    @GetMapping(value = "/{deviceCommunicationId}")
    public AjaxResult getInfo(@PathVariable("deviceCommunicationId") Long deviceCommunicationId)
    {
        return success(deviceCommunicationService.selectDeviceCommunicationByDeviceCommunicationId(deviceCommunicationId));
    }

    /**
     * 新增设备通信记录
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceCommunication:add')")
    @Log(title = "设备通信记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceCommunication deviceCommunication)
    {
        return toAjax(deviceCommunicationService.insertDeviceCommunication(deviceCommunication));
    }

    /**
     * 修改设备通信记录
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceCommunication:edit')")
    @Log(title = "设备通信记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceCommunication deviceCommunication)
    {
        return toAjax(deviceCommunicationService.updateDeviceCommunication(deviceCommunication));
    }

    /**
     * 删除设备通信记录
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceCommunication:remove')")
    @Log(title = "设备通信记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deviceCommunicationIds}")
    public AjaxResult remove(@PathVariable Long[] deviceCommunicationIds)
    {
        return toAjax(deviceCommunicationService.deleteDeviceCommunicationByDeviceCommunicationIds(deviceCommunicationIds));
    }
}
