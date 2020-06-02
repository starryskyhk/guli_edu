package com.starrysky.eduservice.controller;


import com.starrysky.commonutils.R;
import com.starrysky.eduservice.entity.EduVideo;
import com.starrysky.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author starrysky
 * @since 2020-05-25
 */
@Api(tags="课程小节管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @ApiOperation(value = "添加课时")
    @PostMapping("addVideo")
    public R addVideo(@ApiParam(name = "video", value = "课时对象", required = true)  @RequestBody EduVideo video) {
        videoService.save(video);
        return R.ok();
    }
    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("getVideo/{id}")
    public R getVideInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        EduVideo video = videoService.getById(id);
        return R.ok().data("video", video);
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("updateVideo")
    public R updateVideo(
            @ApiParam(name = "video", value = "课时基本信息", required = true)
            @RequestBody EduVideo video){

        videoService.updateById(video);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    //TODO 后面需要完善，删除小节时，同时要删除视频
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){
        boolean result = videoService.removeVideoById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

