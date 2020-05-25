package com.starrysky.eduservice.service;

import com.starrysky.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starrysky.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-05-25
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
