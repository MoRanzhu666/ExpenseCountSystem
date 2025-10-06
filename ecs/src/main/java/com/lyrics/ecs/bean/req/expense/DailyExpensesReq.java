package com.lyrics.ecs.bean.req.expense;

import com.lyrics.ecs.bean.common.CustomIPage;
import com.lyrics.ecs.bean.po.expense.DailyExpensesPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * 用户每日花销明细表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DailyExpensesReq extends CustomIPage<DailyExpensesPo> {

    private Integer year;

    private Integer month;

    private Integer day;

    private Double singleExpense;

    private String expenseReason;

    private String expenseContent;

    private Double dailyTotal;

    private String key;

    private String id;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private List<String> ids;

    private Set<String> months;

    private Set<String> years;

}
    