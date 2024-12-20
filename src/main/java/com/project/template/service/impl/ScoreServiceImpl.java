package com.project.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.entity.Exam;
import com.project.template.entity.Link;
import com.project.template.entity.Score;
import com.project.template.entity.SysUser;
import com.project.template.enums.RoleType;
import com.project.template.exception.CustomException;
import com.project.template.mapper.ScoreMapper;
import com.project.template.service.ScoreService;
import com.project.template.service.SysUserService;
import com.project.template.utils.Utils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生成绩表 服务实现类
 * </p>
 *
 * @author B站：程序员瑞哥
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Resource
    private SysUserService userService;

    @Override
    public boolean save(Score entity) {
        check(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Score entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(Score entity) {
//        LambdaQueryWrapper<Score> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Score::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, Score::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<Score>().in(Score::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<Score> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {



        LambdaQueryWrapper<Score> wrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(query.get("name"))) {
            wrapper.like(Score::getName, query.get("name"));
        }

        wrapper.orderByDesc(Score::getId);
        if (RoleType.TEACHER.getKey().equals(Utils.getUser().getRoleType())) {
            wrapper.eq(Score::getTeacherId, Utils.getUser().getId());
        }
        if (RoleType.USER.getKey().equals(Utils.getUser().getRoleType())) {
            wrapper.eq(Score::getUserId, Utils.getUser().getId());
        }
        Page<Score> page = page(new Page<>(pageNum, pageSize), wrapper);
        page.getRecords().forEach(item->{
            SysUser user = userService.getById(item.getUserId());
            if(user!=null){
                item.setUser(user.getUsername());
            }
        });
        return page;
    }
}
