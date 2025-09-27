package com.lyrics.ecs.bean.req;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.common.CustomIPage;
import com.lyrics.ecs.bean.common.BasePo;
import com.lyrics.ecs.bean.po.DailyExpensesPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

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

}
    