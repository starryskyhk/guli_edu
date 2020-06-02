package com.starrysky.educms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starrysky.commonutils.R;
import com.starrysky.educms.entity.CrmBanner;
import com.starrysky.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author starrysky
 * @since 2020-06-01
 */
@Api(tags = "Banner管理")
@CrossOrigin
@RestController
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit 2");
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.ok().data("bannerList", list);
    }

}

