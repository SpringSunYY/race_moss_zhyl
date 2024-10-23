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
import com.moss.zhyl.domain.Elderly;
import com.moss.zhyl.service.IElderlyService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 长者信息Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/elderly")
public class ElderlyController extends BaseController
{
    @Autowired
    private IElderlyService elderlyService;

    /**
     * 查询长者信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderly:list')")
    @GetMapping("/list")
    public TableDataInfo list(Elderly elderly)
    {
        startPage();
        List<Elderly> list = elderlyService.selectElderlyList(elderly);
        return getDataTable(list);
    }

    /**
     * 导出长者信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderly:export')")
    @Log(title = "长者信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Elderly elderly)
    {
        List<Elderly> list = elderlyService.selectElderlyList(elderly);
        ExcelUtil<Elderly> util = new ExcelUtil<Elderly>(Elderly.class);
        util.exportExcel(response, list, "长者信息数据");
    }

    /**
     * 获取长者信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderly:query')")
    @GetMapping(value = "/{elderlyId}")
    public AjaxResult getInfo(@PathVariable("elderlyId") Long elderlyId)
    {
        return success(elderlyService.selectElderlyByElderlyId(elderlyId));
    }

    /**
     * 新增长者信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderly:add')")
    @Log(title = "长者信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Elderly elderly)
    {
        return toAjax(elderlyService.insertElderly(elderly));
    }

    /**
     * 修改长者信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderly:edit')")
    @Log(title = "长者信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Elderly elderly)
    {
        return toAjax(elderlyService.updateElderly(elderly));
    }

    /**
     * 删除长者信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:elderly:remove')")
    @Log(title = "长者信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{elderlyIds}")
    public AjaxResult remove(@PathVariable Long[] elderlyIds)
    {
        return toAjax(elderlyService.deleteElderlyByElderlyIds(elderlyIds));
    }
}
