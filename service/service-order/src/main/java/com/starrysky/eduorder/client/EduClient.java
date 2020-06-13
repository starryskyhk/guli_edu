package com.starrysky.eduorder.client;

import com.starrysky.commonutils.ordervo.CourseWebOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 韩坤
 * @create 2020-06-06-16:16
 * @Description:
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebOrder getCourseInfoOrder(@PathVariable("id") String id);
}
