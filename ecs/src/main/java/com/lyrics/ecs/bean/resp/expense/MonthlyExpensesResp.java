package com.lyrics.ecs.bean.resp.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lyrics.ecs.bean.common.CustomIPage;
import com.lyrics.ecs.bean.po.expense.MonthlyExpensesPo;
import com.lyrics.ecs.bean.req.expense.MonthlyExpensesReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户月度花销汇总表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MonthlyExpensesResp extends MonthlyExpensesPo {


    @JsonProperty("createByName")
    private String createByName;

    @JsonProperty("updateByName")
    private String updateByName;

    @JsonProperty("averageExpenses")
    private BigDecimal averageExpenses;

    @JsonProperty("countExpenses")
    private  Integer countExpenses;

    @JsonProperty("maxExpenses")
    private BigDecimal maxExpenses;

    @JsonProperty("minExpenses")
    private BigDecimal minExpenses;

    @JsonProperty("secondMaxExpenses")
    private BigDecimal secondMaxExpenses;

    @JsonProperty("secondMinExpenses")
    private BigDecimal secondMinExpenses;
}
    