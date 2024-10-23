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
import com.moss.zhyl.domain.DeviceBrand;
import com.moss.zhyl.service.IDeviceBrandService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 设备品牌Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/deviceBrand")
public class DeviceBrandController extends BaseController
{
    @Autowired
    private IDeviceBrandService deviceBrandService;

    /**
     * 查询设备品牌列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceBrand:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceBrand deviceBrand)
    {
        startPage();
        List<DeviceBrand> list = deviceBrandService.selectDeviceBrandList(deviceBrand);
        return getDataTable(list);
    }

    /**
     * 导出设备品牌列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceBrand:export')")
    @Log(title = "设备品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceBrand deviceBrand)
    {
        List<DeviceBrand> list = deviceBrandService.selectDeviceBrandList(deviceBrand);
        ExcelUtil<DeviceBrand> util = new ExcelUtil<DeviceBrand>(DeviceBrand.class);
        util.exportExcel(response, list, "设备品牌数据");
    }

    /**
     * 获取设备品牌详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceBrand:query')")
    @GetMapping(value = "/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId)
    {
        return success(deviceBrandService.selectDeviceBrandByBrandId(brandId));
    }

    /**
     * 新增设备品牌
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceBrand:add')")
    @Log(title = "设备品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceBrand deviceBrand)
    {
        return toAjax(deviceBrandService.insertDeviceBrand(deviceBrand));
    }

    /**
     * 修改设备品牌
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceBrand:edit')")
    @Log(title = "设备品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceBrand deviceBrand)
    {
        return toAjax(deviceBrandService.updateDeviceBrand(deviceBrand));
    }

    /**
     * 删除设备品牌
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceBrand:remove')")
    @Log(title = "设备品牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{brandIds}")
    public AjaxResult remove(@PathVariable Long[] brandIds)
    {
        return toAjax(deviceBrandService.deleteDeviceBrandByBrandIds(brandIds));
    }
}
