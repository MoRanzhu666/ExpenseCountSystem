package com.lyrics.ecs.bean.resp;

import com.lyrics.ecs.bean.common.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户月度花销汇总表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MonthlyExpensesResp extends BasePo {

    private Integer monthlyId;

    private Integer year;

    private Integer month;

    private Double monthlyTotal;

    private List<DailyExpensesResp> dailyExpenses;

}
    