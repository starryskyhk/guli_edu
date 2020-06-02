package com.starrysky.educms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starrysky.commonutils.R;
import com.starrysky.educms.entity.CrmBanner;
import com.starrysky.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 韩坤
 * @create 2020-06-01-15:55
 * @Description:
 */
@Api(tags = "Banner管理")
@CrossOrigin
@RestController
@RequestMapping("/educms/banner")
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;
    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{current}/{limit}")
    public R index(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {

        Page<CrmBanner> pageParam = new Page<>(current, limit);
        bannerService.page(pageParam,null);
        return R.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get( @ApiParam(name = "id", value = "Banner的id", required = true) @PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public R save( @ApiParam(name = "banner", value = "Banner对象", required = true) @RequestBody CrmBanner banner) {
        bannerService.saveBanner(banner);
        return R.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@ApiParam(name = "banner", value = "Banner对象", required = true) @RequestBody CrmBanner banner) {
        bannerService.updateBanner(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@ApiParam(name = "id", value = "Banner的id", required = true) @PathVariable String id) {
        bannerService.removeBanner(id);
        return R.ok();
    }
}
