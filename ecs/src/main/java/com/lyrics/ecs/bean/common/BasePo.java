package com.lyrics.ecs.bean.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyrics.ecs.utils.LocalDateTimeDeserializer;
import com.lyrics.ecs.utils.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class BasePo {

    private String id;

    private String createBy;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    private String updateBy;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

    public void generateCreateInfo(){
        this.createBy = "1";
        this.createTime = LocalDateTime.now();
    }
    public void generateUpdateInfo(){
        this.updateBy = "1";
        this.updateTime = LocalDateTime.now();
    }
}
