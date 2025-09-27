package com.lyrics.ecs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.YearlyExpensesPo;
import com.lyrics.ecs.bean.req.YearlyExpensesReq;
import com.lyrics.ecs.mapper.YearlyExpensesMapper;
import com.lyrics.ecs.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YearlyExpensesServiec {
    @Autowired
    private YearlyExpensesMapper yearlyExpensesMapper;

    public IPage<YearlyExpensesPo> selectByCondition(YearlyExpensesReq ycondition) {
        return yearlyExpensesMapper.selectByCondition(ycondition);
    }

    public void save(YearlyExpensesPo yearlyExpensesPo) {
        if(ObjectUtils.isEmpty(yearlyExpensesPo.getId())){
            yearlyExpensesMapper.insert(yearlyExpensesPo);
        }else{
            yearlyExpensesMapper.updateById(yearlyExpensesPo);
        }
    }
}
