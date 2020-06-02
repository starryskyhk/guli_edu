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
    //获取章节列表
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
    //删除章节
    boolean deleteChapter(String chapterId);
    //根据课程id删除章节
    boolean removeByCourseId(String courseId);

}
