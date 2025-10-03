package com.lyrics.ecs.controller.expense;


import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.req.expense.MonthlyExpensesReq;
import com.lyrics.ecs.bean.req.expense.YearlyExpensesReq;
import com.lyrics.ecs.bean.resp.expense.MonthlyExpensesResp;
import com.lyrics.ecs.bean.resp.expense.YearlyExpensesResp;
import com.lyrics.ecs.service.expense.MonthlyExpensesService;
import com.lyrics.ecs.service.expense.YearlyExpensesServiec;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense/yearlyExpense")
public class YearlyExpensesController {
    @Autowired
    YearlyExpensesServiec yearlyExpensesServiec;

    @GetMapping("/getPage")
    public ResultPo<YearlyExpensesResp> getPage(@Valid YearlyExpensesReq req){

        return ResultPo.success(yearlyExpensesServiec.getPage(req));
    }

}