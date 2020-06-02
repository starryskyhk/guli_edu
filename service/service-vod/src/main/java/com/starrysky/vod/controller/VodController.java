package com.starrysky.vod.controller;

import com.starrysky.commonutils.R;
import com.starrysky.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 韩坤
 * @create 2020-05-27-14:08
 * @Description:
 */

@Api(tags="阿里云视频点播微服务")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduvod/video")
public class VodController {
    @Autowired
    private VodService vodService;

    @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        String videoId = vodService.uploadVideoAly(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }

    @DeleteMapping("removeAlyVideo/{videoId}")
    public R removeAlyVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable String videoId){

        Boolean flag = vodService.removeVideo(videoId);
        if(flag){
            return R.ok().message("视频删除成功");
        }else{
            return R.error().message("视频删除失败");
        }

    }
    @DeleteMapping("deleteBatch")
    public R deleteBatch( @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
                              @RequestParam("videoIdList") List<String> videoIdList) {

        vodService.removeVideoList(videoIdList);
        return R.ok().message("视频删除成功");
    }
}
