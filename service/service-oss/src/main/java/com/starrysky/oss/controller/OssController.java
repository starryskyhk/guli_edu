package com.starrysky.oss.controller;

import com.starrysky.commonutils.R;
import com.starrysky.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 韩坤
 * @create 2020-05-23-12:41
 * @Description:
 */
@Api(tags="阿里云文件管理")
@RestController
@CrossOrigin
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;
    @PostMapping
    @ApiOperation(value = "文件上传")
    public R upload(@ApiParam(name = "file", value = "文件", required = true) MultipartFile file) {
        //返回oss路径
        String url = ossService.uploadFileAvator(file);
        return R.ok().data("url",url);
    }
}
