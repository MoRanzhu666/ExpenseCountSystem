package com.lyrics.ecs.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lyrics.ecs.bean.common.BasePo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户每日花销明细表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("daily_expenses")
public class DailyExpensesPo extends BasePo {

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private Integer day;

    @NotNull
    private Double singleExpense;

    @NotNull
    private String expenseReason;

    @NotNull
    private String expenseContent;

    private Double dailyTotal;

}
    