package com.lyrics.ecs.controller;

import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.po.UsersPo;
import com.lyrics.ecs.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/getById")
    public ResultPo<UsersPo> getById(String id) {
        return ResultPo.success( usersService.getById(id));
    }

    @PostMapping("/add")
    public ResultPo<String> add(@RequestBody UsersPo usersPo) {
        usersService.add(usersPo);
        return ResultPo.success();
    }

    @PutMapping("/reset")
    public ResultPo<String> reset(@RequestBody UsersPo usersPo) {
        usersService.reset(usersPo);
        return ResultPo.success();
    }


}
