package com.lyrics.ecs.bean.req.expense;

import com.lyrics.ecs.bean.common.CustomIPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户月度花销汇总表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MonthlyExpensesReq extends CustomIPage<MonthlyExpensesReq> {

    private Integer monthlyId;

    private Integer year;

    private Integer month;

    private Double monthlyTotal;

    private List<DailyExpensesReq> dailyExpenses;

    private String key;

    private String id;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}
    