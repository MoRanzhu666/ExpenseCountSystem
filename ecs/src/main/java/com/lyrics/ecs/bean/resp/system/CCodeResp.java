package com.lyrics.ecs.bean.resp.system;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyrics.ecs.bean.common.CustomIPage;
import com.lyrics.ecs.utils.LocalDateTimeDeserializer;
import com.lyrics.ecs.utils.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CCodeResp extends CustomIPage<CCodeResp> {
    private String category;

    private String code;

    private String describe;


    private String id;

    private String createBy;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    private String updateBy;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

    private String createByName;

    private String updateByName;
}
