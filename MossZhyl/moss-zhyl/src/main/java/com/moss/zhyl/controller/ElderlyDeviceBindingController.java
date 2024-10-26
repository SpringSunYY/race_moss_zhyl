package com.moss.zhyl.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.zhyl.service.IElderlyDeviceBindingService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 长者设备绑定Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/elderlyDeviceBinding")
public class ElderlyDeviceBindingController extends BaseController
{
    @Autowired
    private IElderlyDeviceBindingService elderlyDeviceBindingService;

    /**
     * 查询长者设备绑定列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyDeviceBinding:list')")
    @GetMapping("/list")
    public TableDataInfo list(ElderlyDeviceBinding elderlyDeviceBinding)
    {
        startPage();
        List<ElderlyDeviceBinding> list = elderlyDeviceBindingService.selectElderlyDeviceBindingList(elderlyDeviceBinding);
        return getDataTable(list);
    }

    /**
     * 导出长者设备绑定列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyDeviceBinding:export')")
    @Log(title = "长者设备绑定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ElderlyDeviceBinding elderlyDeviceBinding)
    {
        List<ElderlyDeviceBinding> list = elderlyDeviceBindingService.selectElderlyDeviceBindingList(elderlyDeviceBinding);
        ExcelUtil<ElderlyDeviceBinding> util = new ExcelUtil<ElderlyDeviceBinding>(ElderlyDeviceBinding.class);
        util.exportExcel(response, list, "长者设备绑定数据");
    }

    /**
     * 获取长者设备绑定详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyDeviceBinding:query')")
    @GetMapping(value = "/{bindingId}")
    public AjaxResult getInfo(@PathVariable("bindingId") Long bindingId)
    {
        return success(elderlyDeviceBindingService.selectElderlyDeviceBindingByBindingId(bindingId));
    }

    /**
     * 新增长者设备绑定
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyDeviceBinding:add')")
    @Log(title = "长者设备绑定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ElderlyDeviceBinding elderlyDeviceBinding)
    {
        return toAjax(elderlyDeviceBindingService.insertElderlyDeviceBinding(elderlyDeviceBinding));
    }

    /**
     * 修改长者设备绑定
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyDeviceBinding:edit')")
    @Log(title = "长者设备绑定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ElderlyDeviceBinding elderlyDeviceBinding)
    {
        return toAjax(elderlyDeviceBindingService.updateElderlyDeviceBinding(elderlyDeviceBinding));
    }

    /**
     * 删除长者设备绑定
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyDeviceBinding:remove')")
    @Log(title = "长者设备绑定", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bindingIds}")
    public AjaxResult remove(@PathVariable Long[] bindingIds)
    {
        return toAjax(elderlyDeviceBindingService.deleteElderlyDeviceBindingByBindingIds(bindingIds));
    }
}
