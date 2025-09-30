package com.lyrics.ecs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.DailyExpensesPo;
import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.req.DailyExpensesReq;
import com.lyrics.ecs.bean.resp.DailyExpensesResp;
import com.lyrics.ecs.mapper.DailyExpensesMapper;
import com.lyrics.ecs.service.DailyExpensesService;
import jakarta.validation.Valid;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dailyExpenses")
public class DailyExpensesController {
    @Autowired
    private DailyExpensesService dailyExpensesService;

    @PostMapping("/add")
    public ResultPo<String> addDailyExpenses(@RequestBody(required = false) @Valid DailyExpensesResp dp){
        dailyExpensesService.add(dp);
        return ResultPo.success();
    }

    @PutMapping("/update")
    public ResultPo<String> updateDailyExpenses(@RequestBody @Valid DailyExpensesResp dp){
        dailyExpensesService.update(dp);
        return ResultPo.success();
    }

    @GetMapping("/getPage")
    public ResultPo<IPage<DailyExpensesResp>> getPage( @Valid DailyExpensesReq req){

        return ResultPo.success(dailyExpensesService.getPage(req));
    }

    @GetMapping("/getById")
    public ResultPo<DailyExpensesPo> getById( DailyExpensesReq req){
        return ResultPo.success(dailyExpensesService.selectById(req.getId()), "success");
    }

    @DeleteMapping("/deleteById")
    public ResultPo<String> deleteById( DailyExpensesReq req){
        dailyExpensesService.deleteById(req.getId());

        return ResultPo.success();
    }

    @DeleteMapping("/deleteByIds")
    public ResultPo<String> deleteByIds( DailyExpensesReq req){
        dailyExpensesService.deleteByIds(req.getIds());

        return ResultPo.success();
    }


}
