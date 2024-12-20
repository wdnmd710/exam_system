package com.project.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 考试表 服务类
 * </p>
 *
 * @author B站：程序员瑞哥
 *
 */
public interface ExamService extends IService<Exam> {
        Page<Exam> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
