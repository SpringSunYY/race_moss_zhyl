package com.moss.zhyl.controller;

import com.moss.common.core.controller.BaseController;
import com.moss.common.core.domain.AjaxResult;
import com.moss.zhyl.service.IStaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: MossZhyl
 * Package: com.moss.zhyl.controller
 * Author: YY
 * CreateTime: 2024-11-01  22:13
 * Description: StaticsController
 * Version: 1.0
 */
@RestController
@RequestMapping("/zhyl/statics")
public class StaticsController extends BaseController {
    @Autowired
    private IStaticsService staticsService;

    @GetMapping(value = "/getCounts")
    public AjaxResult getCounts()
    {
        return success(staticsService.getCounts());
    }
}
