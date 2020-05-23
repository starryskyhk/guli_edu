package com.starrysky.servicebase.exceptionHandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 韩坤
 * @create 2020-05-22-2:59
 * @Description:
 */
@Data
@AllArgsConstructor //生成有参方法
@NoArgsConstructor  ///生成无参方法
public class StarrySkyException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;

}
