package com.starrysky.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 韩坤
 * @create 2020-05-23-12:40
 * @Description:
 */
public interface OssService {
    String uploadFileAvator(MultipartFile file);
}
