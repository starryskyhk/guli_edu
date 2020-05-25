package com.starrysky.eduservice.entity.subject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 韩坤
 * @create 2020-05-25-1:18
 * @Description:
 */
@Data
@ApiModel(value = "以及课程", description = "一级课程的信息")
public class TwoSubject {
    @ApiModelProperty(value = "课程ID")
    private String id;
    @ApiModelProperty(value = "课程名")
    private String title;

}
