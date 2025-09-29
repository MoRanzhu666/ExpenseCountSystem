package com.lyrics.ecs.bean.resp;

import com.lyrics.ecs.bean.common.BasePo;
import com.lyrics.ecs.bean.common.CustomIPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户每日花销明细表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DailyExpensesResp extends CustomIPage<DailyExpensesResp> {

    private Integer year;

    private Integer month;

    private Integer day;

    private Double singleExpense;

    private String expenseReason;

    private String expenseContent;

    private Double dailyTotal;

    private String id;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}
    