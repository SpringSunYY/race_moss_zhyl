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
import com.moss.zhyl.domain.DeviceType;
import com.moss.zhyl.service.IDeviceTypeService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 设备类型Controller
 * 
 * @author YY
 * @date 2024-10-26
 */
@RestController
@RequestMapping("/zhyl/deviceType")
public class DeviceTypeController extends BaseController
{
    @Autowired
    private IDeviceTypeService deviceTypeService;

    /**
     * 查询设备类型列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceType:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceType deviceType)
    {
        startPage();
        List<DeviceType> list = deviceTypeService.selectDeviceTypeList(deviceType);
        return getDataTable(list);
    }

    /**
     * 导出设备类型列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceType:export')")
    @Log(title = "设备类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceType deviceType)
    {
        List<DeviceType> list = deviceTypeService.selectDeviceTypeList(deviceType);
        ExcelUtil<DeviceType> util = new ExcelUtil<DeviceType>(DeviceType.class);
        util.exportExcel(response, list, "设备类型数据");
    }

    /**
     * 获取设备类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceType:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(deviceTypeService.selectDeviceTypeByTypeId(typeId));
    }

    /**
     * 新增设备类型
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceType:add')")
    @Log(title = "设备类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceType deviceType)
    {
        return toAjax(deviceTypeService.insertDeviceType(deviceType));
    }

    /**
     * 修改设备类型
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceType:edit')")
    @Log(title = "设备类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceType deviceType)
    {
        return toAjax(deviceTypeService.updateDeviceType(deviceType));
    }

    /**
     * 删除设备类型
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceType:remove')")
    @Log(title = "设备类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(deviceTypeService.deleteDeviceTypeByTypeIds(typeIds));
    }
}
