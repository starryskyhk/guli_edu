package com.starrysky.oss.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.starrysky.oss.service.OssService;
import com.starrysky.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 韩坤
 * @create 2020-05-23-12:41
 * @Description:
 */
@Service
@CrossOrigin

public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvator(MultipartFile file) {

        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
//        String fileHost = ConstantPropertiesUtil.FILE_HOST;

        String uploadUrl = null;

        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
//            if (!ossClient.doesBucketExist(bucketName)) {
//                //创建bucket
//                ossClient.createBucket(bucketName);
//                //设置oss实例的访问权限：公共读
//                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
//            }

            //获取上传文件流
            InputStream inputStream = file.getInputStream();

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;
            //将文件按日期分类

            String dataPath = new DateTime().toString("yyyy/MM/dd");
            fileName=dataPath+"/"+fileName;
            //文件上传至阿里云
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //获取url地址
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileName;

        } catch (IOException e) {
//            throw new StarrySkyException(20001,FILE_UPLOAD_ERROR);
        }

        return uploadUrl;
    }
}
