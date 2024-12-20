package com.project.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.dto.SysUserDTO;
import com.project.template.entity.Banner;
import com.project.template.entity.Exam;
import com.project.template.entity.Score;
import com.project.template.entity.SysUser;
import com.project.template.exception.CustomException;
import com.project.template.mapper.ExamMapper;
import com.project.template.service.ExamService;
import com.project.template.service.ScoreService;
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
 * 考试表 服务实现类
 * </p>
 *
 * @author B站：程序员瑞哥
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Resource
    private ScoreService scoreService;

    @Resource
    private SysUserService userService;

    @Override
    public boolean save(Exam entity) {
        check(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Exam entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(Exam entity) {
//        LambdaQueryWrapper<Exam> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Exam::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, Exam::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<Exam>().in(Exam::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<Exam> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Exam> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Exam::getId);

        if (ObjectUtils.isNotEmpty(query.get("name"))) {
            wrapper.like(Exam::getName, query.get("name"));
        }

        SysUserDTO currentUser = UserThreadLocal.getCurrentUser();        // 学生查老师布置的作业
        if ("TEACHER".equals(currentUser.getRoleType())) {
            wrapper.eq(Exam::getUserId, currentUser.getId());
        }
        Page<Exam> page = page(new Page<>(pageNum, pageSize), wrapper);
        List<Exam> list = page.getRecords();
        //查找用户id 和作业id相同的数据
        list.forEach(item->{
            SysUser user = userService.getById(item.getUserId());
            if(user!=null){
                item.setUser(user.getUsername());
            }
            LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
            scoreLambdaQueryWrapper.eq(Score::getUserId,currentUser.getId())
                    .eq(Score::getExamId,item.getId())
                    .last("limit 1");
            Score one = scoreService.getOne(scoreLambdaQueryWrapper);
            if(one!=null){
                item.setIsExam("已考试");
            }else{
                item.setIsExam("立即考试");
            }
        });
        page.setRecords(list);
        return page;
    }
}
