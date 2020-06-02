package com.starrysky.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 韩坤
 * @create 2020-05-27-14:10
 * @Description:
 */
public interface VodService {
    //上传视频
    String uploadVideoAly(MultipartFile file);
    //删除视频
    Boolean removeVideo(String videoId);
    //删除多个视频
    void removeVideoList(List videoIdList);
}
