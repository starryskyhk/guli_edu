package com.starrysky.staservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starrysky.staservice.entity.StatisticsDaily;

import java.util.Map;


/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-06-08
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDay(String day);

    Map<String, Object> getChartData(String begin, String end, String type);
}

