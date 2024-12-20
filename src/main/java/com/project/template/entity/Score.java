package com.project.template.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.project.template.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 学生成绩表
 * </p>
 *
 * @author B站：程序员瑞哥
 *
 */
@Getter
@Setter
@ApiModel(value = "Score对象", description = "学生成绩表")
public class Score extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("考试ID")
    private Integer examId;

    @ApiModelProperty("教师ID")
    private Integer teacherId;

    @ApiModelProperty("成绩")
    private Integer score;

    @ApiModelProperty("学生提交的答案")
    private String userAnswers;

    @ApiModelProperty("考试名称")
    private String name;

    @ApiModelProperty("题目ids")
    private String ids;

    @TableField(exist = false)
    private String user;

    @ApiModelProperty("是否评分")
    private String isScore;
}
