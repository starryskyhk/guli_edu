package com.starrysky.eduservice.client;

import com.starrysky.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 韩坤
 * @create 2020-05-31-12:45
 * @Description:
 */
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    //定义调用的方法路径
    //根据视频id删除阿里云视频
    //PathVariable要指明参数名称
    @DeleteMapping("/eduvod/video/removeAlyVideo/{videoId}")
    R removeAlyVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/eduvod/video/deleteBatch")
    R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
