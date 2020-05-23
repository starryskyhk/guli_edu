package com.starrysky.servicebase.exceptionHandler;

/**
 * @author 韩坤
 * @create 2020-05-22-2:44
 * @Description:
 */

import com.starrysky.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    //指定出现什么异常执行这个方法
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常");
    }

    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常");
    }
    //自定义异常处理
    @ExceptionHandler(StarrySkyException.class)
    @ResponseBody
    public R error(StarrySkyException e){
        //getMessage：系统的异常，getMsg：自定义的异常
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
