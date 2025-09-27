package com.lyrics.ecs.controller;

import com.lyrics.ecs.bean.po.DailyExpensesPo;
import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.mapper.DailyExpensesMapper;
import com.lyrics.ecs.service.DailyExpensesService;
import jakarta.validation.Valid;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dailyExpenses")
public class DailyExpensesController {
    @Autowired
    private DailyExpensesService dailyExpensesService;

    @PostMapping("/add")
    public ResultPo<String> addDailyExpenses(@RequestBody(required = false) @Valid DailyExpensesPo dp){
        dailyExpensesService.add(dp);
        return ResultPo.success();
    }
}
