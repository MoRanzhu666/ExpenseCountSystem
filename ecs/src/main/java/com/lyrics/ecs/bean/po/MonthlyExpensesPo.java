package com.lyrics.ecs.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lyrics.ecs.bean.common.BasePo;
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
@TableName("monthly_expenses")
public class MonthlyExpensesPo extends BasePo {

    private Integer year;

    private Integer month;

    private Double monthlyTotal;

}
    