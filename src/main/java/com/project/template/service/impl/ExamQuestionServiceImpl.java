package com.project.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.dto.SysUserDTO;
import com.project.template.entity.ExamQuestion;
import com.project.template.entity.Score;
import com.project.template.entity.SysUser;
import com.project.template.enums.RoleType;
import com.project.template.exception.CustomException;
import com.project.template.mapper.ExamQuestionMapper;
import com.project.template.service.ExamQuestionService;
import com.project.template.service.SysUserService;
import com.project.template.utils.UserThreadLocal;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试题表 服务实现类
 * </p>
 *
 * @author B站：程序员瑞哥
 */
@Service
public class ExamQuestionServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion> implements ExamQuestionService {

    @Resource
    private SysUserService userService;

    @Override
    public boolean save(ExamQuestion entity) {
        check(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(ExamQuestion entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(ExamQuestion entity) {
//        LambdaQueryWrapper<ExamQuestion> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(ExamQuestion::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, ExamQuestion::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<ExamQuestion>().in(ExamQuestion::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<ExamQuestion> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<ExamQuestion> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ExamQuestion::getId);

        if (ObjectUtils.isNotEmpty(query.get("name"))) {
            wrapper.like(ExamQuestion::getName, query.get("name"));
        }

        //老师只能看到自己的
        SysUserDTO currentUser = UserThreadLocal.getCurrentUser();
        if(RoleType.TEACHER.getKey().equals(currentUser.getRoleType())){
            wrapper.eq(ExamQuestion::getUserId,currentUser.getId());
        }
        Page<ExamQuestion> page = page(new Page<>(pageNum, pageSize), wrapper);


        page.getRecords().forEach(v->{
            SysUser user = userService.getById(v.getUserId());
            if(user!=null){
                v.setUser(user.getUsername());
            }
        });
        return page;
    }
}