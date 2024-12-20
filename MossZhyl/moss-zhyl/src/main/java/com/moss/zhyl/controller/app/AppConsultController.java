package com.moss.zhyl.controller.app;

import com.moss.common.annotation.Log;
import com.moss.common.core.controller.BaseController;
import com.moss.common.core.domain.AjaxResult;
import com.moss.common.core.page.TableDataInfo;
import com.moss.common.enums.BusinessType;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.zhyl.domain.Consult;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.service.IConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 咨询Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/app/zhyl/consult")
public class AppConsultController extends BaseController
{
    @Autowired
    private IConsultService consultService;

    /**
     * 查询咨询列表
     */
    @PreAuthorize("@ss.hasUserAnyRole('elderly,elderly_family')")
    @GetMapping("/list")
    public TableDataInfo list(Consult consult)
    {
        startPage();
        consult.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        List<Consult> list = consultService.selectConsultList(consult);
        return getDataTable(list);
    }

    /**
     * 导出咨询列表
     */
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
