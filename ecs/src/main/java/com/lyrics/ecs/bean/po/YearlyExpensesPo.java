package com.lyrics.ecs.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lyrics.ecs.bean.common.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 用户年度花销汇总表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("yearly_expenses")
public class YearlyExpensesPo extends BasePo {

    private Integer year;

    private Double yearlyTotal;

}
    