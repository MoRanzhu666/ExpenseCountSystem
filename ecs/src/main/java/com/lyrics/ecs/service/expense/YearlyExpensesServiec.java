package com.lyrics.ecs.service.expense;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.expense.YearlyExpensesPo;
import com.lyrics.ecs.bean.req.expense.YearlyExpensesReq;
import com.lyrics.ecs.bean.resp.expense.MonthlyExpensesResp;
import com.lyrics.ecs.bean.resp.expense.YearlyExpensesResp;
import com.lyrics.ecs.mapper.YearlyExpensesMapper;
import com.lyrics.ecs.service.UsersService;
import com.lyrics.ecs.utils.ObjectUtils;
import com.lyrics.ecs.utils.PageUtils;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YearlyExpensesServiec {
    @Autowired
    private YearlyExpensesMapper yearlyExpensesMapper;
    @Autowired
    private UsersService usersService;

    public IPage<YearlyExpensesPo> selectByCondition(YearlyExpensesReq ycondition) {
        return yearlyExpensesMapper.selectByCondition(ycondition);
    }

    public void save(YearlyExpensesPo yearlyExpensesPo) {
        if(ObjectUtils.isEmpty(yearlyExpensesPo.getId())){
            yearlyExpensesPo.generateCreateInfo();
            yearlyExpensesPo.generateUpdateInfo();
            yearlyExpensesMapper.insert(yearlyExpensesPo);
        }else{
            yearlyExpensesPo.generateUpdateInfo();
            yearlyExpensesMapper.updateById(yearlyExpensesPo);
        }
    }

    public YearlyExpensesResp getPage(@Valid YearlyExpensesReq req) {
        YearlyExpensesResp resp = new YearlyExpensesResp();
        IPage<YearlyExpensesResp> respipage = PageUtils.convertPage(yearlyExpensesMapper.selectByCondition(req), YearlyExpensesResp.class);
        BeanUtils.copyProperties(respipage, resp);

        resp.getRecords().forEach(item -> {
            item.setCreateByName( usersService.getById(item.getCreateBy()).getName());
            item.setUpdateByName( usersService.getById(item.getUpdateBy()).getName());
        });


        return resp;
    }
}
