package com.project.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.entity.Exam;
import com.project.template.entity.Link;
import com.project.template.exception.CustomException;
import com.project.template.mapper.LinkMapper;
import com.project.template.service.LinkService;
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
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public boolean save(Link entity) {
        check(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Link entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(Link entity) {
//        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Link::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, Link::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<Link>().in(Link::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<Link> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Link::getId);
        if (ObjectUtils.isNotEmpty(query.get("name"))) {
            wrapper.like(Link::getName, query.get("name"));
        }

        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
