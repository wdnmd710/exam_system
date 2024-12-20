package com.project.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 学生成绩表 服务类
 * </p>
 *
 * @author B站：程序员瑞哥
 *
 */
public interface ScoreService extends IService<Score> {
        Page<Score> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
