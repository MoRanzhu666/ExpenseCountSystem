package com.lyrics.ecs.bean.req.expense;

import com.lyrics.ecs.bean.common.CustomIPage;
import com.lyrics.ecs.bean.po.expense.YearlyExpensesPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * 用户年度花销汇总表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class YearlyExpensesReq extends CustomIPage<YearlyExpensesPo> {

    private Integer year;

    private Double yearlyTotal;

    private List<MonthlyExpensesReq> monthlyExpenses;

    private String key;

    private String id;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Set<String> years;
}
    