package com.lyrics.ecs.controller.expense;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.expense.DailyExpensesPo;
import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.req.expense.DailyExpensesReq;
import com.lyrics.ecs.bean.resp.expense.DailyExpensesResp;
import com.lyrics.ecs.service.expense.DailyExpensesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense/dailyExpenses")
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
