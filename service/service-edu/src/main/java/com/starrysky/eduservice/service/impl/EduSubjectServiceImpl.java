package com.starrysky.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starrysky.eduservice.entity.EduSubject;
import com.starrysky.eduservice.entity.excel.SubjectData;
import com.starrysky.eduservice.entity.subject.OneSubject;
import com.starrysky.eduservice.entity.subject.TwoSubject;
import com.starrysky.eduservice.listener.SubjectExcelListener;
import com.starrysky.eduservice.mapper.EduSubjectMapper;
import com.starrysky.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try{
            //文件输入流
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);

        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);

        //最终要的到的数据列表
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //封装一级分类
        for(EduSubject subject : subjects) {
            OneSubject oneSubject = new OneSubject();
            //将subject赋给oneSubject
            BeanUtils.copyProperties(subject,oneSubject);
            finalSubjectList.add(oneSubject);
            //在一级分类中创建二级分类
            List<TwoSubject> twoFinalSubject = new ArrayList<>();
            //遍历二级分类
            for(EduSubject subject1:subSubjects) {
                if(subject1.getParentId().equals(subject.getId())) {
                    TwoSubject twoSubject =new TwoSubject();
                    BeanUtils.copyProperties(subject1,twoSubject);
                    twoFinalSubject.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubject);
        }



        return finalSubjectList;
    }
}
