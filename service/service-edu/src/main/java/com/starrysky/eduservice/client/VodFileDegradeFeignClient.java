package com.starrysky.eduservice.client;

import com.starrysky.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 韩坤
 * @create 2020-05-31-16:20
 * @Description:
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {

    @Override
    public R removeAlyVideo(String videoId) {
        return R.error().message("time out");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("time out");
    }
}
