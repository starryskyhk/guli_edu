package com.starrysky.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starrysky.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starrysky.eduservice.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-05-21
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageTeacher, TeacherQuery teacherQuery);
}
