package com.lyrics.ecs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyrics.ecs.bean.po.UsersPo;
import com.lyrics.ecs.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;

    public UsersPo getById(String id) {
        return usersMapper.selectById(id);
    }

    public UsersPo getByNamePWD(String username, String password) {
        return usersMapper.selectOne(new QueryWrapper<UsersPo>().lambda()
                .eq(UsersPo::getName, username)
                .eq(UsersPo::getPassword, password));
    }
}
