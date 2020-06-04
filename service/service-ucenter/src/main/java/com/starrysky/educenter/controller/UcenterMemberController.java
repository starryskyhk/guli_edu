package com.starrysky.educenter.controller;


import com.starrysky.commonutils.JwtUtils;
import com.starrysky.commonutils.R;
import com.starrysky.educenter.entity.UcenterMember;
import com.starrysky.educenter.entity.vo.LoginVo;
import com.starrysky.educenter.entity.vo.RegisterVo;
import com.starrysky.educenter.service.UcenterMemberService;
import com.starrysky.servicebase.exceptionHandler.StarrySkyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author starrysky
 * @since 2020-06-02
 */
@Api(tags = "登录注册")
@CrossOrigin
@RestController
@RequestMapping("/educenter/member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;


    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            UcenterMember member = memberService.getById(memberId);
            return R.ok().data("member", member);
        }catch (Exception e){
            e.printStackTrace();
            throw new StarrySkyException(20001,"error");
        }
    }

    //根据token字符串获取用户信息
    @GetMapping("getInfoUc/{id}")
    public UcenterMember getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = memberService.getById(id);
        System.out.println("注册端："+ucenterMember);
        return ucenterMember;
    }

}

