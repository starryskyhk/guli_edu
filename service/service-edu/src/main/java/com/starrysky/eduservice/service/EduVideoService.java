package com.starrysky.eduservice.service;

import com.starrysky.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-05-25
 */
public interface EduVideoService extends IService<EduVideo> {
    //根据课程id删除小节视频
    boolean removeByCourseId(String courseId);
}
