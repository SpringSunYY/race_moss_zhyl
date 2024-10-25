package com.moss.zhyl.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.moss.zhyl.domain.dto.UserInfoElderlyDto;
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
import com.moss.zhyl.domain.UserInfo;
import com.moss.zhyl.service.IUserInfoService;
import com.moss.common.utils.poi.ExcelUtil;
import com.moss.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 * 
 * @author YY
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/zhyl/userInfo")
public class UserInfoController extends BaseController
{
    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:userInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserInfo userInfo)
    {
        startPage();
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('zhyl:userInfo:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserInfo userInfo)
    {
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        ExcelUtil<UserInfo> util = new ExcelUtil<UserInfo>(UserInfo.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:userInfo:query')")
    @GetMapping(value = "/{userInfoId}")
    public AjaxResult getInfo(@PathVariable("userInfoId") Long userInfoId)
    {
        return success(userInfoService.selectUserInfoByUserInfoIdResultDto(userInfoId));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:userInfo:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserInfoElderlyDto userInfo)
    {
        return toAjax(userInfoService.insertUserInfo(userInfo));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:userInfo:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserInfoElderlyDto userInfoElderlyDto)
    {
        return toAjax(userInfoService.updateUserInfo(userInfoElderlyDto));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('zhyl:userInfo:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userInfoIds}")
    public AjaxResult remove(@PathVariable Long[] userInfoIds)
    {
        return toAjax(userInfoService.deleteUserInfoByUserInfoIds(userInfoIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('zhyl:userInfo:updatePassword')")
    @Log(title = "用户信息管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody UserInfo user)
    {
        return toAjax(userInfoService.resetPwd(user));
    }
}
