package com.starrysky.staservice.controller;


import com.starrysky.commonutils.R;
import com.starrysky.staservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author starrysky
 * @since 2020-06-08
 */
@Api(tags = "统计模块")
@CrossOrigin
@RestController
@RequestMapping("/staservice/statistics")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService dailyService;

    @ApiOperation("统计当前日期注册人数")
    @PostMapping("{day}")
    public R createStatisticsByDate(@ApiParam(name = "day", value = "日期",required = true) @PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }
    @ApiOperation("统计图表数据")
    @GetMapping("showData/{begin}/{end}/{type}")
    public R showData(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return R.ok().data(map);
    }


}

