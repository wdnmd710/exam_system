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
@ApiModel(value = "Link对象", description = "轮播图")
public class Link extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("网站名称")
    private String name;

    @ApiModelProperty("网站网址")
    private String link;
}
