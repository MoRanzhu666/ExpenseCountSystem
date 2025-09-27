package com.lyrics.ecs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.DailyExpensesPo;
import com.lyrics.ecs.bean.req.DailyExpensesReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DailyExpensesMapper extends BaseMapper<DailyExpensesPo> {
     IPage<DailyExpensesPo> selectByCondition(@Param("condition") DailyExpensesReq dcondition);
}
