package com.lyrics.ecs.bean.common;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class BasePo {
    private String id;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;

    public void generateCreateInfo(){
        this.createBy = "1";
        this.createTime = LocalDateTime.now();
    }
    public void generateUpdateInfo(){
        this.updateBy = "1";
        this.createTime = LocalDateTime.now();
    }
}
