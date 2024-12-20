package com.project.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.entity.ExamQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 试题表 服务类
 * </p>
 *
 * @author B站：程序员瑞哥
 *
 */
public interface ExamQuestionService extends IService<ExamQuestion> {
        Page<ExamQuestion> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
