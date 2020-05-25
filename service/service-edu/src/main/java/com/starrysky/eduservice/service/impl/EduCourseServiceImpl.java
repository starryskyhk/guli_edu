package com.starrysky.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starrysky.eduservice.entity.EduCourse;
import com.starrysky.eduservice.entity.EduCourseDescription;
import com.starrysky.eduservice.entity.vo.CourseInfoVo;
import com.starrysky.eduservice.mapper.EduCourseMapper;
import com.starrysky.eduservice.service.EduCourseDescriptionService;
import com.starrysky.eduservice.service.EduCourseService;
import com.starrysky.servicebase.exceptionHandler.StarrySkyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author starrysky
 * @since 2020-05-25
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    //添加课程基本信息
    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程中添加基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0) {
            //添加失败
            throw  new StarrySkyException(20001,"添加课程信息失败");
        }
        String cid = eduCourse.getId();
        //2.向课程简介表中课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询出课程信息
        EduCourse course = this.getById(courseId);
        if(course == null){
            throw new StarrySkyException(20001, "数据不存在");
        }
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);
        //查询出课程详情信息
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        //课程不等于空，则给课程信息赋予详细信息的内容
        if(course != null){
            courseInfoVo.setDescription(courseDescription.getDescription());
        }

        return courseInfoVo;
    }

    @Override
    @Transactional
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, course);
        boolean resultCourseInfo = this.updateById(course);
        if(!resultCourseInfo){
            throw new StarrySkyException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        boolean resultDescription = courseDescriptionService.updateById(courseDescription);
        if(!resultDescription){
            throw new StarrySkyException(20001, "课程详情信息保存失败");
        }
    }
}
