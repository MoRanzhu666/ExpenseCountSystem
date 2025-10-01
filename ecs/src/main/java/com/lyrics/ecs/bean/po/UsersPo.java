package com.lyrics.ecs.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyrics.ecs.bean.common.BasePo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类
 * 对应数据库表：users
 */
@Data
@TableName("users") // 指定对应的数据库表名
public class UsersPo extends BasePo {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_UUID) // 若使用MyBatis-Plus，可指定ID生成策略
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 角色
     */
    private String role;

    /**
     * 密码
     */
    private String password;
}
    