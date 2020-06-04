package com.starrysky.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starrysky.commonutils.R;
import com.starrysky.eduservice.entity.EduCourse;
import com.starrysky.eduservice.entity.EduTeacher;
import com.starrysky.eduservice.service.EduCourseService;
import com.starrysky.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 韩坤
 * @create 2020-06-03-15:59
 * @Description:
 */
@Api(tags = "前台讲师")
@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    @ApiOperation("分页查询讲师")
    @PostMapping("getTeacherFrontList/{current}/{limit}")
    public R getTeacherFrontList( @ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                                  @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher);
        return R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getById(
            @ApiParam(name = "teacherId", value = "讲师ID", required = true)
            @PathVariable String teacherId){

        //查询讲师信息
        EduTeacher teacher = teacherService.getById(teacherId);

        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = courseService.list(new QueryWrapper<EduCourse>().eq("teacher_id",teacherId));

        return R.ok().data("teacher", teacher).data("courseList", courseList);
    }
}
