package com.starrysky.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starrysky.eduservice.entity.EduSubject;
import com.starrysky.eduservice.entity.excel.SubjectData;
import com.starrysky.eduservice.listener.SubjectExcelListener;
import com.starrysky.eduservice.mapper.EduSubjectMapper;
import com.starrysky.eduservice.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author starrysky
 * @since 2020-05-23
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try{
            //文件输入流
            InputStream in = file.getInputStream();
            System.out.println("走到这里");
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
