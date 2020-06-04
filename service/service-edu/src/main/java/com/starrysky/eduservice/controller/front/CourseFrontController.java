package com.starrysky.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starrysky.commonutils.R;
import com.starrysky.eduservice.entity.EduCourse;
import com.starrysky.eduservice.entity.chapter.ChapterVo;
import com.starrysky.eduservice.entity.frontvo.CourseFrontVo;
import com.starrysky.eduservice.entity.frontvo.CourseWebVo;
import com.starrysky.eduservice.service.EduChapterService;
import com.starrysky.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 韩坤
 * @create 2020-06-04-0:26
 * @Description:
 */
@Api(tags = "前台课程")
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @ApiOperation(value = "分页课程列表")
    @PostMapping("getFrontCourseList/{current}/{limit}")
    public R pageList(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseFrontVo courseQuery){
        Page<EduCourse> page = new Page<EduCourse>(current, limit);
        Map<String, Object> map = courseService.getCourseFrontList(page, courseQuery);
        return  R.ok().data(map);
    }


    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);

        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }
}
