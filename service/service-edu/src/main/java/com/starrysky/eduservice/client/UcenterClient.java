package com.starrysky.eduservice.client;

import com.starrysky.eduservice.entity.frontvo.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 韩坤
 * @create 2020-06-04-14:02
 * @Description:
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    //根据用户id获取用户信息
    @GetMapping("/educenter/member/getInfoUc/{id}")
    public UcenterMember getUcenterPay(@PathVariable("id") String id);
}

@Component
class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMember getUcenterPay(String memberId) {
        return null;
    }
}
