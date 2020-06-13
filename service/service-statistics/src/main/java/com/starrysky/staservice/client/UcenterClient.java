package com.starrysky.staservice.client;

import com.starrysky.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 韩坤
 * @create 2020-06-08-14:48
 * @Description:
 */
@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    @GetMapping(value = "/educenter/member/countregister/{day}")
    public R registerCount(@PathVariable("day") String day);
}
@Component
class UcenterClientImpl implements UcenterClient{

    @Override
    public R registerCount(String day) {
        return R.error().message("超时");
    }
}
