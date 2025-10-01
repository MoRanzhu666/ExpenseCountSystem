package com.lyrics.ecs.bean.req.system;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyrics.ecs.bean.common.CustomIPage;
import com.lyrics.ecs.bean.po.system.CCodePo;
import com.lyrics.ecs.utils.LocalDateTimeDeserializer;
import com.lyrics.ecs.utils.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CCodeReq extends CustomIPage<CCodePo> {

    private String key;

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


}
