package com.lyrics.ecs.bean.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户每日花销明细表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyExpensesResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("month")
    private Integer month;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("singleExpense")
    private Double singleExpense;

    @JsonProperty("expenseReason")
    private String expenseReason;

    @JsonProperty("expenseContent")
    private String expenseContent;

    @JsonProperty("dailyTotal")
    private Double dailyTotal;

    @JsonProperty("id")
    private String id;

    @JsonProperty("createBy")
    private String createBy;

    @JsonProperty("createByName")
    private String createByName;

    @JsonProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @JsonProperty("updateBy")
    private String updateBy;

    @JsonProperty("updateByName")
    private String updateByName;

    @JsonProperty("updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

}
