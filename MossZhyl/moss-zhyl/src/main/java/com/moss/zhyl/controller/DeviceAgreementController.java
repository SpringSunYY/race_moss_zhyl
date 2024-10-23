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
import com.moss.zhyl.domain.DeviceAgreement;
import com.moss.zhyl.service.IDeviceAgreementService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 设备协议Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/deviceAgreement")
public class DeviceAgreementController extends BaseController
{
    @Autowired
    private IDeviceAgreementService deviceAgreementService;

    /**
     * 查询设备协议列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceAgreement:list')")
    @GetMapping("/list")
    public TableDataInfo list(DeviceAgreement deviceAgreement)
    {
        startPage();
        List<DeviceAgreement> list = deviceAgreementService.selectDeviceAgreementList(deviceAgreement);
        return getDataTable(list);
    }

    /**
     * 导出设备协议列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceAgreement:export')")
    @Log(title = "设备协议", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeviceAgreement deviceAgreement)
    {
        List<DeviceAgreement> list = deviceAgreementService.selectDeviceAgreementList(deviceAgreement);
        ExcelUtil<DeviceAgreement> util = new ExcelUtil<DeviceAgreement>(DeviceAgreement.class);
        util.exportExcel(response, list, "设备协议数据");
    }

    /**
     * 获取设备协议详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceAgreement:query')")
    @GetMapping(value = "/{agreementId}")
    public AjaxResult getInfo(@PathVariable("agreementId") Long agreementId)
    {
        return success(deviceAgreementService.selectDeviceAgreementByAgreementId(agreementId));
    }

    /**
     * 新增设备协议
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceAgreement:add')")
    @Log(title = "设备协议", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeviceAgreement deviceAgreement)
    {
        return toAjax(deviceAgreementService.insertDeviceAgreement(deviceAgreement));
    }

    /**
     * 修改设备协议
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceAgreement:edit')")
    @Log(title = "设备协议", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceAgreement deviceAgreement)
    {
        return toAjax(deviceAgreementService.updateDeviceAgreement(deviceAgreement));
    }

    /**
     * 删除设备协议
     */
    @PreAuthorize("@ss.hasPermi('zhyl:deviceAgreement:remove')")
    @Log(title = "设备协议", businessType = BusinessType.DELETE)
	@DeleteMapping("/{agreementIds}")
    public AjaxResult remove(@PathVariable Long[] agreementIds)
    {
        return toAjax(deviceAgreementService.deleteDeviceAgreementByAgreementIds(agreementIds));
    }
}
