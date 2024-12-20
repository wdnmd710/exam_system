package com.project.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.entity.Banner;
import com.project.template.entity.Notice;
import com.project.template.exception.CustomException;
import com.project.template.mapper.BannerMapper;
import com.project.template.service.BannerService;
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
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    public boolean save(Banner entity) {
        check(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Banner entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(Banner entity) {
//        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Banner::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, Banner::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<Banner>().in(Banner::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<Banner> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Banner::getId);
        if (ObjectUtils.isNotEmpty(query.get("name"))) {
            wrapper.like(Banner::getName, query.get("name"));
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
