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
import com.moss.zhyl.domain.ServiceStation;
import com.moss.zhyl.service.IServiceStationService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 服务站信息Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/serviceStation")
public class ServiceStationController extends BaseController
{
    @Autowired
    private IServiceStationService serviceStationService;

    /**
     * 查询服务站信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:serviceStation:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServiceStation serviceStation)
    {
        startPage();
        List<ServiceStation> list = serviceStationService.selectServiceStationList(serviceStation);
        return getDataTable(list);
    }

    /**
     * 导出服务站信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:serviceStation:export')")
    @Log(title = "服务站信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceStation serviceStation)
    {
        List<ServiceStation> list = serviceStationService.selectServiceStationList(serviceStation);
        ExcelUtil<ServiceStation> util = new ExcelUtil<ServiceStation>(ServiceStation.class);
        util.exportExcel(response, list, "服务站信息数据");
    }

    /**
     * 获取服务站信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:serviceStation:query')")
    @GetMapping(value = "/{serviceStationId}")
    public AjaxResult getInfo(@PathVariable("serviceStationId") Long serviceStationId)
    {
        return success(serviceStationService.selectServiceStationByServiceStationId(serviceStationId));
    }

    /**
     * 新增服务站信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:serviceStation:add')")
    @Log(title = "服务站信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceStation serviceStation)
    {
        return toAjax(serviceStationService.insertServiceStation(serviceStation));
    }

    /**
     * 修改服务站信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:serviceStation:edit')")
    @Log(title = "服务站信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceStation serviceStation)
    {
        return toAjax(serviceStationService.updateServiceStation(serviceStation));
    }

    /**
     * 删除服务站信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:serviceStation:remove')")
    @Log(title = "服务站信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serviceStationIds}")
    public AjaxResult remove(@PathVariable Long[] serviceStationIds)
    {
        return toAjax(serviceStationService.deleteServiceStationByServiceStationIds(serviceStationIds));
    }
}
