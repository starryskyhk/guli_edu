package com.starrysky.eduservice.service;

import com.starrysky.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.starrysky.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author starrysky
 * @since 2020-05-23
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
