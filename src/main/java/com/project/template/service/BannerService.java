package com.project.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 轮播图 服务类
 * </p>
 *
 * @author B站：程序员瑞哥
 *
 */
public interface BannerService extends IService<Banner> {
        Page<Banner> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
