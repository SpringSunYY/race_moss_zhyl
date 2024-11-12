package com.moss.zhyl.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.moss.zhyl.strategy.deviceUploadingDataStrategy.saveStrategy.DeviceUploadingDataSaveStrategyExecutor;
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
import com.moss.zhyl.domain.deviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.service.IDeviceUploadingDataService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 设备上传数据Controller
 *
 * @author YY
 * @date 2024-10-26
 */
@RestController
@RequestMapping("/zhyl/deviceUploadingData")
public class DeviceUploadingDataController extends BaseController {
    @Autowired
    private IDeviceUploadingDataService deviceUploadingDataService;

    @Autowired
    private DeviceUploadingDataSaveStrategyExecutor deviceUploadingDataSaveStrategyExecutor;

    /**
     * 查询设备上传数据列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceUploadingData:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceUploadingData deviceUploadingData) {
        startPage();
        List<DeviceUploadingData> list = deviceUploadingDataService.selectDeviceUploadingDataList(deviceUploadingData);
        return getDataTable(list);
    }

    /**
     * 导出设备上传数据列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceUploadingData:export')")
    @Log(title = "设备上传数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceUploadingData deviceUploadingData) {
        List<DeviceUploadingData> list = deviceUploadingDataService.selectDeviceUploadingDataList(deviceUploadingData);
        ExcelUtil<DeviceUploadingData> util = new ExcelUtil<DeviceUploadingData>(DeviceUploadingData.class);
        util.exportExcel(response, list, "设备上传数据数据");
    }

    /**
     * 获取设备上传数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceUploadingData:query')")
    @GetMapping(value = "/{dataId}")
    public AjaxResult getInfo(@PathVariable("dataId") Long dataId) {
        return success(deviceUploadingDataService.selectDeviceUploadingDataByDataId(dataId));
    }

    /**
     * 新增设备上传数据
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceUploadingData:add')")
    @Log(title = "设备上传数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceUploadingData deviceUploadingData) {
        return toAjax(deviceUploadingDataSaveStrategyExecutor.saveDeviceUploadingData(deviceUploadingData));
    }

    /**
     * 修改设备上传数据
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceUploadingData:edit')")
    @Log(title = "设备上传数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceUploadingData deviceUploadingData) {
        return toAjax(deviceUploadingDataService.updateDeviceUploadingData(deviceUploadingData));
    }

    /**
     * 删除设备上传数据
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceUploadingData:remove')")
    @Log(title = "设备上传数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dataIds}")
    public AjaxResult remove(@PathVariable Long[] dataIds) {
        return toAjax(deviceUploadingDataService.deleteDeviceUploadingDataByDataIds(dataIds));
    }
}
