package com.project.template.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.template.common.PageVO;
import com.project.template.common.Result;
import com.project.template.entity.ExamQuestion;
import com.project.template.service.ExamQuestionService;
import com.project.template.utils.UserThreadLocal;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试题表 前端控制器
 * </p>
 *
 * @author B站：程序员瑞哥
 *
 */
@RestController
@RequestMapping("/examQuestion")
public class ExamQuestionController {
    @Resource
    private ExamQuestionService service;
    /**
     * 列表
     */
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Result<List<ExamQuestion>> list() {
        return new Result<>().success(service.list());
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<PageVO<ExamQuestion>> findPage(@RequestParam Map<String, Object> query,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize) {
        Page<ExamQuestion> page = service.page(query, pageNum, pageSize);
        return new Result<>().success(new PageVO<>(page));
    }

    /**
     * 数据新增
     */
    @ApiOperation(value = "数据新增", notes = "数据新增")
    @PostMapping("/add")
    public Result add(@Validated @RequestBody ExamQuestion entity) {
        entity.setUserId(UserThreadLocal.getCurrentUser().getId());
        service.save(entity);
        return new Result<>().success();
    }

    /**
     * 数据更新
     */
    @ApiOperation(value = "数据更新", notes = "数据更新")
    @PutMapping("/update")
    public Result updateById(@Validated @RequestBody ExamQuestion entity) {
        service.updateById(entity);
        return new Result<>().success();
    }

    /**
     * 根据id删除
     */
    @ApiOperation(value = "数据根据id批量删除", notes = "数据根据id批量删除")
    @DeleteMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        service.removeByIds(ids);
        return new Result<>().success();
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping("/getById")
    public Result<ExamQuestion> getById(@RequestParam("id") Integer id) {
        ExamQuestion entity = service.getById(id);
        return new Result<>().success(entity);
    }
    @GetMapping("/getQuestion")
    public Result getQuestion(@RequestParam("ids") Integer[] ids) {
        List<ExamQuestion> list = new ArrayList<>();
        for (Integer id : ids) {
            ExamQuestion byId = service.getById(id);
            list.add(byId);
        }
        return new Result<>().success(list);
    }

}
