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
import com.moss.zhyl.domain.TagManagement;
import com.moss.zhyl.service.ITagManagementService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 标签Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/tagManagement")
public class TagManagementController extends BaseController
{
    @Autowired
    private ITagManagementService tagManagementService;

    /**
     * 查询标签列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:tagManagement:list')")
    @GetMapping("/list")
    public TableDataInfo list(TagManagement tagManagement)
    {
        startPage();
        List<TagManagement> list = tagManagementService.selectTagManagementList(tagManagement);
        return getDataTable(list);
    }

    /**
     * 导出标签列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:tagManagement:export')")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TagManagement tagManagement)
    {
        List<TagManagement> list = tagManagementService.selectTagManagementList(tagManagement);
        ExcelUtil<TagManagement> util = new ExcelUtil<TagManagement>(TagManagement.class);
        util.exportExcel(response, list, "标签数据");
    }

    /**
     * 获取标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:tagManagement:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId)
    {
        return success(tagManagementService.selectTagManagementByTagId(tagId));
    }

    /**
     * 新增标签
     */
    @PreAuthorize("@ss.hasPermi('zhyl:tagManagement:add')")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TagManagement tagManagement)
    {
        return toAjax(tagManagementService.insertTagManagement(tagManagement));
    }

    /**
     * 修改标签
     */
    @PreAuthorize("@ss.hasPermi('zhyl:tagManagement:edit')")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TagManagement tagManagement)
    {
        return toAjax(tagManagementService.updateTagManagement(tagManagement));
    }

    /**
     * 删除标签
     */
    @PreAuthorize("@ss.hasPermi('zhyl:tagManagement:remove')")
    @Log(title = "标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds)
    {
        return toAjax(tagManagementService.deleteTagManagementByTagIds(tagIds));
    }
}
