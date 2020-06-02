package com.starrysky.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starrysky.commonutils.R;
import com.starrysky.eduservice.entity.EduTeacher;
import com.starrysky.eduservice.entity.vo.TeacherQuery;
import com.starrysky.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author starrysky
 * @since 2020-05-21
 */
@Api(tags = "讲师管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    //查询讲师表所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTecher() {
//        try {
//            int i = 10 / 0;
//        } catch (Exception e) {
//            throw new StarrySkyException(20001, "除数不能为0");
//        }

        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    //逻辑删除讲师
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //3.分页查询
    //current,当前页，limit 每页记录数
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current, @ApiParam(name = "limit", value = "每页显示条数", required = true) @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //调用方法分页
        teacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "分页条件查询讲师列表")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageQuery(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherQuery teacherQuery) {

        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        teacherService.pageQuery(pageTeacher, teacherQuery);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();

        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {

        boolean save = teacherService.save(teacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }

    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {

        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("updateTeacher")
    public R updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        boolean update = teacherService.updateById(teacher);
        if (update) {
            return R.ok();
        } else {
            return R.error();
        }

    }
}
