package com.starrysky.eduservice.controller;

import com.starrysky.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author 韩坤
 * @create 2020-05-22-15:54
 * @Description:
 */
@Api(tags = "用户登录")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {
    //登录
    @ApiOperation(value = "登录")
    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }
    //info
    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/65423f14-49a9-4092-baf5-6d0ef9686a85.png");
    }

}
