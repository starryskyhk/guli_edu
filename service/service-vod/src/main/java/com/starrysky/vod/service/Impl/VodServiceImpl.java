package com.starrysky.vod.service.Impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.starrysky.servicebase.exceptionHandler.StarrySkyException;
import com.starrysky.vod.service.VodService;
import com.starrysky.vod.utils.ConstantVodUtils;
import com.starrysky.vod.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 韩坤
 * @create 2020-05-27-14:10
 * @Description:
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAly(MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantVodUtils.ACCESS_KEY_ID,
                    ConstantVodUtils.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                if(StringUtils.isEmpty(videoId)){
                    throw new StarrySkyException(20001, errorMessage);
                }
            }

            return videoId;
        } catch (IOException e) {
            throw new StarrySkyException(20001, "视频上传失败");
        }
    }

    @Override
    public Boolean removeVideo(String videoId) {
        try{
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            //调用初始化对象实现方法
            client.getAcsResponse(request);
            return true;
        } catch (Exception e) {
            throw new StarrySkyException(20001,"删除失败");
        }
    }

    @Override
    public void removeVideoList(List videoIdList) {
        try {
            //初始化
            DefaultAcsClient client = InitVodClient.initVodClient(
                    ConstantVodUtils.ACCESS_KEY_ID,
                    ConstantVodUtils.ACCESS_KEY_SECRET);

            //创建请求对象
            //一次只能批量删20个
            String str = StringUtils.join(videoIdList.toArray(), ",");
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(str);

            //获取响应
            DeleteVideoResponse response = client.getAcsResponse(request);

            System.out.print("RequestId = " + response.getRequestId() + "\n");

        } catch (Exception e) {
            throw new StarrySkyException(20001, "视频删除失败");
        }
    }
}
