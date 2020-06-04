package com.starrysky.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.starrysky.commonutils.R;
import com.starrysky.vod.service.VodService;
import com.starrysky.vod.utils.ConstantVodUtils;
import com.starrysky.vod.utils.InitVodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("上传视频")
    @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        String videoId = vodService.uploadVideoAly(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }
    @ApiOperation("删除视频")
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
    @ApiOperation("批量删除视频")
    @DeleteMapping("deleteBatch")
    public R deleteBatch( @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
                              @RequestParam("videoIdList") List<String> videoIdList) {

        vodService.removeVideoList(videoIdList);
        return R.ok().message("视频删除成功");
    }

    @ApiOperation("根据视频id获取凭证")
    @GetMapping("getPlayAuth/{videoId}")
    public R getVideoPlayAuth(@PathVariable("videoId") String videoId) throws Exception {

        //获取阿里云存储相关常量
        String accessKeyId = ConstantVodUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantVodUtils.ACCESS_KEY_SECRET;

        //初始化
        DefaultAcsClient client = InitVodClient.initVodClient(accessKeyId, accessKeySecret);

        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);

        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        //得到播放凭证
        String playAuth = response.getPlayAuth();

        //返回结果
        return R.ok().message("获取凭证成功").data("playAuth", playAuth);
    }
}
