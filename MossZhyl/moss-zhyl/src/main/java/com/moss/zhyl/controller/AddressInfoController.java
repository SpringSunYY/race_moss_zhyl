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
import com.moss.common.core.domain.entity.AddressInfo;
import com.moss.zhyl.service.IAddressInfoService;
import com.moss.common.utils.poi.ExcelUtil;

/**
 * 地址信息Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/addressInfo")
public class AddressInfoController extends BaseController
{
    @Autowired
    private IAddressInfoService addressInfoService;

    /**
     * 查询地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:addressInfo:list')")
    @GetMapping("/list")
    public AjaxResult list(AddressInfo addressInfo)
    {
        List<AddressInfo> list = addressInfoService.selectAddressInfoList(addressInfo);
        return success(list);
    }

    /**
     * 查询地址信息列表树形结构
     */
    @PreAuthorize("@ss.hasPermi('zhyl:addressInfo:list')")
    @GetMapping("/addressTree")
    public AjaxResult addressTree(AddressInfo addressInfo) {
        return success(addressInfoService.addressTree(addressInfo));
    }

    /**
     * 导出地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:addressInfo:export')")
    @Log(title = "地址信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AddressInfo addressInfo)
    {
        List<AddressInfo> list = addressInfoService.selectAddressInfoList(addressInfo);
        ExcelUtil<AddressInfo> util = new ExcelUtil<AddressInfo>(AddressInfo.class);
        util.exportExcel(response, list, "地址信息数据");
    }

    /**
     * 获取地址信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:addressInfo:query')")
    @GetMapping(value = "/{addressId}")
    public AjaxResult getInfo(@PathVariable("addressId") Long addressId)
    {
        return success(addressInfoService.selectAddressInfoByAddressId(addressId));
    }

    /**
     * 新增地址信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:addressInfo:add')")
    @Log(title = "地址信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AddressInfo addressInfo)
    {
        return toAjax(addressInfoService.insertAddressInfo(addressInfo));
    }

    /**
     * 修改地址信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:addressInfo:edit')")
    @Log(title = "地址信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AddressInfo addressInfo)
    {
        return toAjax(addressInfoService.updateAddressInfo(addressInfo));
    }

    /**
     * 删除地址信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:addressInfo:remove')")
    @Log(title = "地址信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{addressIds}")
    public AjaxResult remove(@PathVariable Long[] addressIds)
    {
        return toAjax(addressInfoService.deleteAddressInfoByAddressIds(addressIds));
    }
}
