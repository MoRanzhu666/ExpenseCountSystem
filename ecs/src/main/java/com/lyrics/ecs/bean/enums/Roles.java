package com.lyrics.ecs.bean.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Roles {
    SYSTEM_MANAGER("SYSTEM_MANAGER", "系统管理员")
    ;

    private String key;
    private String value;
}
