package com.starrysky.eduorder.service;

import com.starrysky.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-06-06
 */
public interface OrderService extends IService<Order> {
    //创建订单
    String saveOrder(String courseId, String memberIdByJwtToken);
}
