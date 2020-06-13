package com.starrysky.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 韩坤
 * @create 2020-06-07-16:38
 * @Description:
 */
@Component
@FeignClient(value = "service-order",fallback = OrderClientImpl.class )
public interface OrderClient {
    //查询订单信息
    
    @GetMapping("/eduorder/order/isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable("memberid") String memberid, @PathVariable("id") String id);
}
@Component
class OrderClientImpl implements OrderClient{

    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}
