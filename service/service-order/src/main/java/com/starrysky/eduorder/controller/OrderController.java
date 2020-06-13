package com.starrysky.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starrysky.commonutils.JwtUtils;
import com.starrysky.commonutils.R;
import com.starrysky.eduorder.entity.Order;
import com.starrysky.eduorder.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author starrysky
 * @since 2020-06-06
 */
@Api(tags = "订单接口")
@CrossOrigin
@RestController
@RequestMapping("/eduorder/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //根据课程id和用户id创建订单，返回订单id
    @ApiOperation("生成订单")
    @GetMapping("createOrder/{courseId}")
    public R createOrder(@ApiParam(name = "courseId",value = "课程号",required = true)@PathVariable String courseId, HttpServletRequest request) {
     String orderId = orderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));

        return R.ok().data("orderId", orderId);
    }
    @ApiOperation("根据订单id获取订单信息")
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@ApiParam(name = "orderId",value = "订单号",required = true)@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("item", order);
    }
    @ApiOperation("根据课程id和用户id查询支付状态")
    @GetMapping("isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable String memberid,
                               @PathVariable String id) {
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<Order>().eq("member_id", memberid).eq("course_id", id).eq("status", 1));
        if(count>0) {
            return true;
        } else {
            return false;
        }
    }
}

