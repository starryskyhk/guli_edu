package com.starrysky.eduorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starrysky.commonutils.ordervo.CourseWebOrder;
import com.starrysky.commonutils.ordervo.UcenterMemberOrder;
import com.starrysky.eduorder.client.EduClient;
import com.starrysky.eduorder.client.UcenterClient;
import com.starrysky.eduorder.entity.Order;
import com.starrysky.eduorder.mapper.OrderMapper;
import com.starrysky.eduorder.service.OrderService;
import com.starrysky.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author starrysky
 * @since 2020-06-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public String saveOrder(String courseId, String memberId) {
        //通过远程调用获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);
        //通过远程调用获取课程信息
        CourseWebOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        //创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
