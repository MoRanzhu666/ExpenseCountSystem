package com.lyrics.ecs.bean.req.system;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyrics.ecs.bean.common.CustomIPage;
import com.lyrics.ecs.bean.po.system.CCodePo;
import com.lyrics.ecs.utils.LocalDateTimeDeserializer;
import com.lyrics.ecs.utils.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CCodeReq extends CustomIPage<CCodePo> {

    @JsonProperty("key")
    private String key;

    @JsonProperty("category")
    private String category;

    @JsonProperty("code")
    private String code;

    @JsonProperty("describe")
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

    @JsonProperty("ids")
    private List<String> ids;


}
