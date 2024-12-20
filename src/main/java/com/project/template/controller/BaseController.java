package com.project.template.controller;

import com.project.template.service.BannerService;
import com.project.template.service.NoticeService;
import com.project.template.service.SysRoleService;
import com.project.template.service.SysUserService;

import com.project.template.service.impl.SysRoleServiceImpl;
import com.project.template.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BaseController {
    @Resource
    SysUserService sysUserService;

    @Resource
    NoticeService noticeService;

    @Resource
    SysRoleService sysRoleService;

    @Resource
    BannerService bannerService;
}
