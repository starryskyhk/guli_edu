package com.starrysky.eduservice.controller;


import com.starrysky.commonutils.R;
import com.starrysky.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public R addSubject(MultipartFile file) {
        //上传过来的文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
}

