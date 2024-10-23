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
import com.moss.zhyl.domain.ElderlyFamily;
import com.moss.zhyl.service.IElderlyFamilyService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 家属信息Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/elderlyFamily")
public class ElderlyFamilyController extends BaseController
{
    @Autowired
    private IElderlyFamilyService elderlyFamilyService;

    /**
     * 查询家属信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyFamily:list')")
    @GetMapping("/list")
    public TableDataInfo list(ElderlyFamily elderlyFamily)
    {
        startPage();
        List<ElderlyFamily> list = elderlyFamilyService.selectElderlyFamilyList(elderlyFamily);
        return getDataTable(list);
    }

    /**
     * 导出家属信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyFamily:export')")
    @Log(title = "家属信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ElderlyFamily elderlyFamily)
    {
        List<ElderlyFamily> list = elderlyFamilyService.selectElderlyFamilyList(elderlyFamily);
        ExcelUtil<ElderlyFamily> util = new ExcelUtil<ElderlyFamily>(ElderlyFamily.class);
        util.exportExcel(response, list, "家属信息数据");
    }

    /**
     * 获取家属信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyFamily:query')")
    @GetMapping(value = "/{elderlyFamilyId}")
    public AjaxResult getInfo(@PathVariable("elderlyFamilyId") Long elderlyFamilyId)
    {
        return success(elderlyFamilyService.selectElderlyFamilyByElderlyFamilyId(elderlyFamilyId));
    }

    /**
     * 新增家属信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyFamily:add')")
    @Log(title = "家属信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ElderlyFamily elderlyFamily)
    {
        return toAjax(elderlyFamilyService.insertElderlyFamily(elderlyFamily));
    }

    /**
     * 修改家属信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyFamily:edit')")
    @Log(title = "家属信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ElderlyFamily elderlyFamily)
    {
        return toAjax(elderlyFamilyService.updateElderlyFamily(elderlyFamily));
    }

    /**
     * 删除家属信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderlyFamily:remove')")
    @Log(title = "家属信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{elderlyFamilyIds}")
    public AjaxResult remove(@PathVariable Long[] elderlyFamilyIds)
    {
        return toAjax(elderlyFamilyService.deleteElderlyFamilyByElderlyFamilyIds(elderlyFamilyIds));
    }
}
