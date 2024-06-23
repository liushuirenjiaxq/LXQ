package com.bwl.controller;

import com.bwl.mapper.ContentMapper;
import com.bwl.mapper.UserMapper;
import com.bwl.service.BackLogService;
import com.bwl.service.ContentService;
import com.bwl.service.UserService;
import jakarta.annotation.Resource;

//公共方法
public class BaseController {
    @Resource
    protected UserService userService;
    @Resource
    protected ContentService contentService;
    @Resource
    protected BackLogService backLogService;
}
