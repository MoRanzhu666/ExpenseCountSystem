package com.lyrics.ecs.controller.expense;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.po.expense.MonthlyExpensesPo;
import com.lyrics.ecs.bean.req.expense.MonthlyExpensesReq;
import com.lyrics.ecs.bean.resp.expense.MonthlyExpensesResp;
import com.lyrics.ecs.service.expense.MonthlyExpensesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense/monthlyExpense")
public class MonthlyExpensesController {
    @Autowired
    MonthlyExpensesService monthlyExpensesService;

    @GetMapping("/getPage")
    public ResultPo<IPage<MonthlyExpensesResp>> getPage(@Valid MonthlyExpensesReq req){

        return ResultPo.success(monthlyExpensesService.getPage(req));
    }

}
