package com.lyrics.ecs.service.expense;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.expense.MonthlyExpensesPo;
import com.lyrics.ecs.bean.req.expense.MonthlyExpensesReq;
import com.lyrics.ecs.bean.resp.expense.MonthlyExpensesResp;
import com.lyrics.ecs.mapper.MonthlyExpensesMapper;
import com.lyrics.ecs.service.UsersService;
import com.lyrics.ecs.utils.ObjectUtils;
import com.lyrics.ecs.utils.PageUtils;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthlyExpensesService {
    @Autowired
    private MonthlyExpensesMapper monthlyExpensesMapper;
    @Autowired
    private UsersService usersService;

    public void save(MonthlyExpensesPo monthlyExpensesPo) {
        if(ObjectUtils.isEmpty(monthlyExpensesPo.getId())){
            monthlyExpensesPo.generateCreateInfo();
            monthlyExpensesPo.generateUpdateInfo();
            monthlyExpensesMapper.insert(monthlyExpensesPo);
        }else{
            monthlyExpensesPo.generateUpdateInfo();
            monthlyExpensesMapper.updateById(monthlyExpensesPo);
        }
    }

    public IPage<MonthlyExpensesPo> selectByCondition(MonthlyExpensesReq mcondition) {
        return monthlyExpensesMapper.selectByCondition(mcondition);
    }

    public MonthlyExpensesResp getPage(@Valid MonthlyExpensesReq req) {
        MonthlyExpensesResp resp = new MonthlyExpensesResp();
        IPage<MonthlyExpensesResp> respipage = PageUtils.convertPage(monthlyExpensesMapper.selectByCondition(req), MonthlyExpensesResp.class);
        BeanUtils.copyProperties(respipage, resp);

        resp.getRecords().forEach(item -> {
            item.setCreateByName( usersService.getById(item.getCreateBy()).getName());
            item.setUpdateByName( usersService.getById(item.getUpdateBy()).getName());
        });


        return resp;
    }
}
