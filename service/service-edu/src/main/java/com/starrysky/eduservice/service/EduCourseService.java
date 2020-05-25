package com.starrysky.eduservice.service;

import com.starrysky.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starrysky.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-05-25
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);
    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);
    //根据课程id修改课程基本信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
