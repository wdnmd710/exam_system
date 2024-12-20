package com.project.template.entity;

import com.project.template.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 轮播图
 * </p>
 *
 * @author B站：程序员瑞哥
 *
 */
@Getter
@Setter
@ApiModel(value = "Banner对象", description = "轮播图")
public class Banner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("轮播备注")
    private String name;

    @ApiModelProperty("轮播图")
    private String img;

    @ApiModelProperty("跳转网址")
    private String link;
}
