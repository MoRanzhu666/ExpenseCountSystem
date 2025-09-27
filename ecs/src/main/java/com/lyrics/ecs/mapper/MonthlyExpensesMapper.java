package com.lyrics.ecs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.MonthlyExpensesPo;
import com.lyrics.ecs.bean.req.MonthlyExpensesReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonthlyExpensesMapper extends BaseMapper<MonthlyExpensesPo> {

    IPage<MonthlyExpensesPo> selectByCondition(MonthlyExpensesReq mcondition);
}
