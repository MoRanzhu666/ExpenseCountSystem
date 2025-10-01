package com.lyrics.ecs.service;

import com.lyrics.ecs.bean.po.UsersPo;
import com.lyrics.ecs.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private UsersService usersService;

    /**
     * 用户登录并生成令牌
     */
    public String login(String username, String password) {
        UsersPo user = usersService.getByNamePWD(username, password);
        if (!ObjectUtils.isEmpty(user)) {
            String token = UUID.randomUUID().toString();
            
            redisTemplate.opsForValue().set(
                "auth:" + token, 
                user, 
                2, 
                TimeUnit.HOURS
            );
            
            return token;
        }
        return null;
    }
    
    /**
     * 验证令牌是否有效
     */
    public UsersPo verifyToken(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        
        String key = "auth:" + token;
        UsersPo user = (UsersPo) redisTemplate.opsForValue().get(key);
        
        if (user != null) {
            // 延长令牌有效期
            redisTemplate.expire(key, 2, TimeUnit.HOURS);
            return user;
        }
        
        return null;
    }
    
    /**
     * 用户登出，删除令牌
     */
    public boolean logout(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        
        return redisTemplate.delete("auth:" + token);
    }
}
