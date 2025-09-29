package com.lyrics.ecs.bean.resp;

import com.lyrics.ecs.bean.common.BasePo;
import com.lyrics.ecs.bean.common.CustomIPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户年度花销汇总表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class YearlyExpensesResp extends CustomIPage<YearlyExpensesResp> {

    private Integer year;

    private Double yearlyTotal;

    private List<MonthlyExpensesResp> monthlyExpenses;

    private String id;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;
}
    