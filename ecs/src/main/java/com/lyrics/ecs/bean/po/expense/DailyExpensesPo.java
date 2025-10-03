package com.lyrics.ecs.bean.po.expense;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lyrics.ecs.bean.common.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户每日花销明细表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("daily_expenses")
public class DailyExpensesPo extends BasePo {

    private Integer year;

    private Integer month;

    private Integer day;

    private Double singleExpense;

    private String expenseReason;

    private String expenseContent;

    private Double dailyTotal;

}
    