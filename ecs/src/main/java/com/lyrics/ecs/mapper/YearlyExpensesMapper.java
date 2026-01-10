package com.lyrics.ecs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.expense.YearlyExpensesPo;
import com.lyrics.ecs.bean.req.expense.YearlyExpensesReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YearlyExpensesMapper extends BaseMapper<YearlyExpensesPo> {
    IPage<YearlyExpensesPo> selectByCondition(YearlyExpensesReq ycondition);
}
