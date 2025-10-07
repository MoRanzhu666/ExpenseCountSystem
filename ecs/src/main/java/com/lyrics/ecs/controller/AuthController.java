package com.lyrics.ecs.controller;

import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.po.UsersPo;
import com.lyrics.ecs.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResultPo<String> login(@RequestBody UsersPo usersPo) {
        String token = authService.login(usersPo.getName(), usersPo.getPassword());
        if (token != null) {
            return ResultPo.success( token,"登录成功");
        }
        return ResultPo.error( 401, "用户名或密码错误");
    }

    @GetMapping("/verify")
    public ResultPo<UsersPo> verifyToken(@RequestParam String token) {
        UsersPo user = authService.verifyToken(token);
        if (user != null) {
             return ResultPo.success( user);
        }
        return ResultPo.error( 401, "验证失败");
    }

    @PostMapping("/logout")
    public ResultPo<String> logout(@RequestParam String token) {
        if (authService.logout(token)) {
            return ResultPo.success( "登出成功","登出成功");
        }
        return ResultPo.error( 400, "登出失败");
    }
}
