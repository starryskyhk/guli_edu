package com.starrysky.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starrysky.eduservice.entity.EduTeacher;
import com.starrysky.eduservice.entity.vo.TeacherQuery;

import java.util.Map;

/**
 * @author 韩坤
 * @create 2020-06-03-16:14
 * @Description:
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageTeacher, TeacherQuery teacherQuery);

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
