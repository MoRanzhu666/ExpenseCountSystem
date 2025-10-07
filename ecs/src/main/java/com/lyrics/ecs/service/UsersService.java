package com.lyrics.ecs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyrics.ecs.bean.enums.Roles;
import com.lyrics.ecs.bean.exceptions.BadRequestException;
import com.lyrics.ecs.bean.po.UsersPo;
import com.lyrics.ecs.mapper.UsersMapper;
import com.lyrics.ecs.utils.MD5Util;
import com.lyrics.ecs.utils.ObjectUtils;
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
                .eq(UsersPo::getPassword, MD5Util.getMD5( password)));
    }

    public void add(UsersPo usersPo) {
        if (ObjectUtils.isEmpty(usersPo) || ObjectUtils.isEmpty(usersPo.getName()) || ObjectUtils.isEmpty(usersPo.getPassword())) {
            throw new BadRequestException("参数校验失败");
        }
        usersPo.setRole(Roles.SYSTEM_MANAGER.getKey());
        usersPo.setPassword(MD5Util.getMD5(usersPo.getPassword()));
        usersPo.generateCreateInfo();
        usersPo.generateUpdateInfo();
        usersMapper.insert(usersPo);
    }

    public void reset(UsersPo usersPo) {
        if (ObjectUtils.isEmpty(usersPo)  || ObjectUtils.isEmpty(usersPo.getId())) {
            throw new BadRequestException("参数校验失败");
        }
        usersPo = this.getById(usersPo.getId());
        usersPo.setPassword(MD5Util.getMD5("123456"));
        usersPo.generateUpdateInfo();
        usersMapper.updateById(usersPo);

    }
}
