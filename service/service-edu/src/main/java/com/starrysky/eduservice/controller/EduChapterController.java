package com.starrysky.eduservice.controller;


import com.starrysky.commonutils.R;
import com.starrysky.eduservice.entity.EduChapter;
import com.starrysky.eduservice.entity.chapter.ChapterVo;
import com.starrysky.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author starrysky
 * @since 2020-05-25
 */
@Api(tags="课程章节管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", chapterVoList);
    }
    @ApiOperation(value = "新增章节")
    @PostMapping("addChapter")
    public R addChapter(
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }
    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@ApiParam(name = "chapterId", value = "章节ID", required = true) @PathVariable String chapterId){

        EduChapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter", chapter);
    }
    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("updateChapter")
    public R updateChapter(
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter chapter){
        chapterService.updateById(chapter);
        return R.ok();
    }
    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("{chapterId}")
    public R deleteChapter(
            @ApiParam(name = "chapterId", value = "章节ID", required = true)
            @PathVariable String chapterId){

        boolean result = chapterService.deleteChapter(chapterId);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

