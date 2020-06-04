package com.starrysky.eduservice.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starrysky.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starrysky.eduservice.entity.frontvo.CourseFrontVo;
import com.starrysky.eduservice.entity.vo.CourseInfoVo;
import com.starrysky.eduservice.entity.vo.CoursePublishVo;
import com.starrysky.eduservice.entity.vo.CourseQuery;
import com.starrysky.eduservice.entity.frontvo.CourseWebVo;

import java.util.Map;

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
    //根据课程id获取课程发布信息
    CoursePublishVo publicCourseInfo(String id);
    //分页查询课程（后台）
    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);
    //删除课程
    boolean deleteCourse(String id);
    //根据条件查询课程
    Map<String, Object> getCourseFrontList(Page<EduCourse> page, CourseFrontVo courseQuery);

    CourseWebVo selectInfoWebById(String courseId);
    /**
     * 更新课程浏览数
     * @param id
     */
    void updatePageViewCount(String id);
}
