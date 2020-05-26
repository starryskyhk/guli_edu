package com.starrysky.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starrysky.eduservice.entity.EduCourse;
import com.starrysky.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author starrysky
 * @since 2020-05-25
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getCoursePublishInfo(String id);
}
