package com.starrysky.eduservice.controller;
import com.starrysky.commonutils.R;
import com.starrysky.eduservice.entity.subject.OneSubject;
import com.starrysky.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author starrysky
 * @since 2020-05-23
 */
@Api(tags = "课程分类管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addStudent")
    public R addSubject(@ApiParam(value = "excel文件",required = true) MultipartFile file) {
        //上传过来的文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
    //课程分类列表显示(树形)
    @ApiOperation(value = "查询课程信息，树状显示")
    @GetMapping("getAllSubjet")
    public R getAllSubjet() {
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }

}

