//package com.lyrics.ecs.bean.trigger;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.lyrics.ecs.bean.po.expense.DailyExpensesPo;
//import com.lyrics.ecs.bean.po.expense.MonthlyExpensesPo;
//import com.lyrics.ecs.bean.po.expense.YearlyExpensesPo;
//import com.lyrics.ecs.bean.req.expense.DailyExpensesReq;
//import com.lyrics.ecs.bean.req.expense.MonthlyExpensesReq;
//import com.lyrics.ecs.bean.req.expense.YearlyExpensesReq;
//import com.lyrics.ecs.mapper.DailyExpensesMapper;
//import com.lyrics.ecs.mapper.MonthlyExpensesMapper;
//import com.lyrics.ecs.service.expense.DailyExpensesService;
//import com.lyrics.ecs.service.expense.MonthlyExpensesService;
//import com.lyrics.ecs.service.expense.YearlyExpensesServiec;
//import com.lyrics.ecs.utils.ObjectUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//
//public class DailyExpensesTrigger {
//    @Autowired
//    private MonthlyExpensesService monthlyExpensesService;
//    @Autowired
//    private DailyExpensesService dailyExpensesService;
//    @Autowired
//    private YearlyExpensesServiec yearlyExpensesServiec;
//
//    public void insertAfter(DailyExpensesPo dailyExpensesPo) {
//        DailyExpensesPo operationParam = processDaily(dailyExpensesPo);
//
//        processMonthly(operationParam);
//
//        processYearly(operationParam);
//    }
//
//    private DailyExpensesPo processDaily(DailyExpensesPo dailyExpensesPo) {
//        DailyExpensesReq dcondition = new DailyExpensesReq();
//        dcondition.setMonth(dailyExpensesPo.getMonth());
//        dcondition.setYear(dailyExpensesPo.getYear());
//        dcondition.setDay(dailyExpensesPo.getDay());
//        IPage<DailyExpensesPo> dipage = dailyExpensesService.selectByCondition(dcondition);
//
//        if (dipage.getTotal() < 1) { // 当天没有数据
//            dailyExpensesPo.setDailyTotal(dailyExpensesPo.getSingleExpense());
//
//            dailyExpensesService.add(dailyExpensesPo);
//        } else {
//            Double total = 0.0;
//            total += dailyExpensesPo.getSingleExpense();
//            for (DailyExpensesPo dpo : dipage.getRecords()) {
//                total += dpo.getSingleExpense();
//            }
//            for (DailyExpensesPo dpo : dipage.getRecords()) {
//                dpo.setDailyTotal(total);
//            }
//
//
//            dailyExpensesPo.setDailyTotal(total);
//
//            dailyExpensesService.batchUpdateById(dipage.getRecords());
//            dailyExpensesService.add(dailyExpensesPo);
//        }
//
//
//        return dailyExpensesPo;
//    }
//
//
//    private void processMonthly(DailyExpensesPo dailyExpensesPo) {
//        MonthlyExpensesReq mcondition = new MonthlyExpensesReq();
//        mcondition.setMonth(dailyExpensesPo.getMonth());
//        mcondition.setYear(dailyExpensesPo.getYear());
//        IPage<MonthlyExpensesPo> mipage = monthlyExpensesService.selectByCondition(mcondition);
//
//        MonthlyExpensesPo monthlyExpensesPo = mipage.getTotal() > 0 ? mipage.getRecords().get(0) : new MonthlyExpensesPo();
//
//        // 计算月合计 此时的日总和已经是统计完毕无误的
//        monthlyExpensesPo.setMonthlyTotal(dailyExpensesPo.getSingleExpense() + monthlyExpensesPo.getMonthlyTotal());
//
//        // 更新月合计
//        monthlyExpensesService.save(monthlyExpensesPo);
//
//    }
//
//    private void processYearly(DailyExpensesPo dailyExpensesPo) {
//        YearlyExpensesReq ycondition = new YearlyExpensesReq();
//        ycondition.setYear(dailyExpensesPo.getYear());
//        IPage<YearlyExpensesPo> yipage = yearlyExpensesServiec.selectByCondition(ycondition);
//
//        YearlyExpensesPo yearlyExpensesPo = yipage.getTotal() > 0 ? yipage.getRecords().get(0) : new YearlyExpensesPo();
//
//        yearlyExpensesPo.setYearlyTotal(yearlyExpensesPo.getYearlyTotal() + dailyExpensesPo.getSingleExpense());
//
//        yearlyExpensesServiec.save(yearlyExpensesPo);
//
//    }
//
//
//}
