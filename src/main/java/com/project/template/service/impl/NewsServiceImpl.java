package com.project.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.entity.News;
import com.project.template.entity.Notice;
import com.project.template.entity.SysUser;
import com.project.template.exception.CustomException;
import com.project.template.mapper.NewsMapper;
import com.project.template.service.NewsService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 轮播图 服务实现类
 * </p>
 *
 * @author B站：程序员瑞哥
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Override
    public boolean save(News entity) {
        check(entity);
        entity.setViews(0);
        return super.save(entity);
    }

    @Override
    public boolean updateById(News entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(News entity) {
//        LambdaQueryWrapper<News> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(News::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, News::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<News>().in(News::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<News> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<News> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(News::getId);

        if (ObjectUtils.isNotEmpty(query.get("name"))) {
            wrapper.like(News::getName, query.get("name"));
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
