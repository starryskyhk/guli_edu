package com.starrysky.eduservice.entity.subject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 韩坤
 * @create 2020-05-25-1:17
 * @Description:
 */
@Data
@ApiModel(value = "以及课程", description = "一级课程的信息")
public class OneSubject {
    @ApiModelProperty(value = "课程ID")
    private String id;
    @ApiModelProperty(value = "课程名")
    private String title;
    @ApiModelProperty(value = "二级分类")
    private List<TwoSubject> children = new ArrayList<>();
}
