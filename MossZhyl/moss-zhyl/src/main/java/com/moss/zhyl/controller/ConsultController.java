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
import com.moss.zhyl.domain.Consult;
import com.moss.zhyl.service.IConsultService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 咨询Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/consult")
public class ConsultController extends BaseController
{
    @Autowired
    private IConsultService consultService;

    /**
     * 查询咨询列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:consult:list')")
    @GetMapping("/list")
    public TableDataInfo list(Consult consult)
    {
        startPage();
        List<Consult> list = consultService.selectConsultList(consult);
        return getDataTable(list);
    }

    /**
     * 导出咨询列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:consult:export')")
    @Log(title = "咨询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Consult consult)
    {
        List<Consult> list = consultService.selectConsultList(consult);
        ExcelUtil<Consult> util = new ExcelUtil<Consult>(Consult.class);
        util.exportExcel(response, list, "咨询数据");
    }

    /**
     * 获取咨询详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:consult:query')")
    @GetMapping(value = "/{consultId}")
    public AjaxResult getInfo(@PathVariable("consultId") Long consultId)
    {
        return success(consultService.selectConsultByConsultId(consultId));
    }

    /**
     * 新增咨询
     */
    @PreAuthorize("@ss.hasPermi('zhyl:consult:add')")
    @Log(title = "咨询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Consult consult)
    {
        return toAjax(consultService.insertConsult(consult));
    }

    /**
     * 修改咨询
     */
    @PreAuthorize("@ss.hasPermi('zhyl:consult:edit')")
    @Log(title = "咨询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Consult consult)
    {
        return toAjax(consultService.updateConsult(consult));
    }

    /**
     * 删除咨询
     */
    @PreAuthorize("@ss.hasPermi('zhyl:consult:remove')")
    @Log(title = "咨询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consultIds}")
    public AjaxResult remove(@PathVariable Long[] consultIds)
    {
        return toAjax(consultService.deleteConsultByConsultIds(consultIds));
    }
}
