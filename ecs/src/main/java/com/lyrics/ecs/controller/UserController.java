package com.lyrics.ecs.controller;

import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.po.UsersPo;
import com.lyrics.ecs.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/getById")
    public ResultPo<UsersPo> getById(String id) {
        return ResultPo.success( usersService.getById(id));
    }
}
