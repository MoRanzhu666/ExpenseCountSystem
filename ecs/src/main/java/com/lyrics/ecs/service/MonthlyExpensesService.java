package com.lyrics.ecs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.MonthlyExpensesPo;
import com.lyrics.ecs.bean.req.MonthlyExpensesReq;
import com.lyrics.ecs.mapper.MonthlyExpensesMapper;
import com.lyrics.ecs.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthlyExpensesService {
    @Autowired
    private MonthlyExpensesMapper monthlyExpensesMapper;

    public void save(MonthlyExpensesPo monthlyExpensesPo) {
        if(ObjectUtils.isEmpty(monthlyExpensesPo.getId())){
            monthlyExpensesMapper.insert(monthlyExpensesPo);
        }else{
            monthlyExpensesMapper.updateById(monthlyExpensesPo);
        }
    }

    public IPage<MonthlyExpensesPo> selectByCondition(MonthlyExpensesReq mcondition) {
        return monthlyExpensesMapper.selectByCondition(mcondition);
    }
}
