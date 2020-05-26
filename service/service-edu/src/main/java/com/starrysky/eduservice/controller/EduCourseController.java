package com.starrysky.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starrysky.commonutils.R;
import com.starrysky.eduservice.entity.EduCourse;
import com.starrysky.eduservice.entity.vo.CourseInfoVo;
import com.starrysky.eduservice.entity.vo.CoursePublishVo;
import com.starrysky.eduservice.entity.vo.CourseQuery;
import com.starrysky.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@Api(tags = "课程管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;
    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery){

        Page<EduCourse> pageParam = new Page<>(page, limit);

        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    //添加课程基本信息
    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@ApiParam(value = "课程信息", required = true) @RequestBody CourseInfoVo courseInfoVo) {
        String courseId = courseService.saveCourseInfo(courseInfoVo);
        if (!StringUtils.isEmpty(courseId)) {
            return R.ok().data("courseId", courseId);
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("getCourseInfo/{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {

        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("updateCourseInfo")
    public R updateCourseInfo(
            @ApiParam(name = "courseInfoVo", value = "课程基本信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {

        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CoursePublishVo coursePublishVo = courseService.publicCourseInfo(id);
        return R.ok().data("coursePublishVo", coursePublishVo);
    }
    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public R deleteCourse(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        boolean result = courseService.deleteCourse(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
    @ApiOperation(value = "最终发布课程")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@ApiParam(name = "id", value = "课程ID", required = true) @PathVariable String id){
        EduCourse course = new EduCourse().setId(id).setStatus(EduCourse.COURSE_NORMAL);
        boolean result = courseService.updateById(course);
        if(result) {
            return R.ok();
        }else{
            return R.error().message("发布失败");
        }
    }


}

