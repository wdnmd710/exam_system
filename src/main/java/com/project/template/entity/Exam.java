package com.project.template.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.project.template.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 考试表
 * </p>
 *
 * @author B站：程序员瑞哥
 *
 */
@Getter
@Setter
@ApiModel(value = "Exam对象", description = "考试表")
public class Exam extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("考试标题")
    private String name;

    @ApiModelProperty("封面图")
    private String img;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("试题列表")
    private String question;

    @ApiModelProperty("考试总分")
    private Integer score;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @TableField(exist = false)
    private String isExam;

    @TableField(exist = false)
    private String user;
}
