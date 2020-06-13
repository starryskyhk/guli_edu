package com.starrysky.educenter.controller;


import com.starrysky.commonutils.JwtUtils;
import com.starrysky.commonutils.R;
import com.starrysky.commonutils.ordervo.UcenterMemberOrder;
import com.starrysky.educenter.entity.UcenterMember;
import com.starrysky.educenter.entity.vo.LoginVo;
import com.starrysky.educenter.entity.vo.RegisterVo;
import com.starrysky.educenter.service.UcenterMemberService;
import com.starrysky.servicebase.exceptionHandler.StarrySkyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
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
    @ApiOperation(value = "根据请求头中的token获取登录信息")
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
    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping("getInfoUc/{id}")
    public UcenterMember getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = memberService.getById(id);
        System.out.println("注册端："+ucenterMember);
        return ucenterMember;
    }
    //通过id获取用户信息
    @ApiOperation(value = "根据用户id获取用户信息")
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        //把member中的值赋给UcenterMemberOrder
        UcenterMemberOrder memberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,memberOrder);
        return memberOrder;
    }
    @ApiOperation("查询某一天注册人数")
    @GetMapping(value = "countregister/{day}")
    public R registerCount(@ApiParam(name = "day",value = "日期",required = true) @PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }



}

