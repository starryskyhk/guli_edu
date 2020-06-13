package com.starrysky.eduorder.service;

import com.starrysky.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-06-06
 */
public interface PayLogService extends IService<PayLog> {
    //生成微信支付二维码
    Map createNative(String orderNo);
    //调用查询接口查询支付状态
    Map<String, String> queryPayStatus(String orderNo);
    //更新支付状态
    void updateOrderStatus(Map<String, String> map);
}
