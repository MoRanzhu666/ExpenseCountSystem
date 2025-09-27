package com.lyrics.ecs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.exceptions.BadRequestException;
import com.lyrics.ecs.bean.po.DailyExpensesPo;
import com.lyrics.ecs.bean.po.MonthlyExpensesPo;
import com.lyrics.ecs.bean.po.YearlyExpensesPo;
import com.lyrics.ecs.bean.req.DailyExpensesReq;
import com.lyrics.ecs.bean.req.MonthlyExpensesReq;
import com.lyrics.ecs.bean.req.YearlyExpensesReq;
import com.lyrics.ecs.mapper.DailyExpensesMapper;
import com.lyrics.ecs.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DailyExpensesService {
    @Autowired
    private DailyExpensesMapper dailyExpensesMapper;
    @Autowired
    private YearlyExpensesServiec yearlyExpensesServiec;
    @Autowired
    private MonthlyExpensesService monthlyExpensesService;


    @Transactional(rollbackFor = Exception.class)
    public void add(DailyExpensesPo dailyExpensesPo) {
        if (ObjectUtils.isEmpty(dailyExpensesPo)) {
            throw new BadRequestException("参数校验失败");
        }

        dailyExpensesPo.generateCreateInfo();
        dailyExpensesPo.generateUpdateInfo();
        dailyExpensesMapper.insert(dailyExpensesPo);
        this.insertAfter(dailyExpensesPo);
    }


    public void batchUpdateById(List<DailyExpensesPo> dailyExpensesPoList) {
        dailyExpensesPoList.forEach(dailyExpensesPo -> {
            dailyExpensesMapper.updateById(dailyExpensesPo);
        });
    }

    public IPage<DailyExpensesPo> selectByCondition(DailyExpensesReq dcondition) {
        return dailyExpensesMapper.selectByCondition(dcondition);
    }


    public void insertAfter(DailyExpensesPo dailyExpensesPo) {
        DailyExpensesPo operationParam = processDaily(dailyExpensesPo);

        processMonthly(operationParam);

        processYearly(operationParam);
    }

    private DailyExpensesPo processDaily(DailyExpensesPo dailyExpensesPo) {
        DailyExpensesReq dcondition = new DailyExpensesReq();
        dcondition.setMonth(dailyExpensesPo.getMonth());
        dcondition.setYear(dailyExpensesPo.getYear());
        dcondition.setDay(dailyExpensesPo.getDay());
        IPage<DailyExpensesPo> dipage = this.selectByCondition(dcondition);


        Double total = 0.0;
        for (DailyExpensesPo dpo : dipage.getRecords()) {
            total += dpo.getSingleExpense();
        }
        for (DailyExpensesPo dpo : dipage.getRecords()) {
            dpo.setDailyTotal(total);
        }


        dailyExpensesPo.setDailyTotal(total);

        this.batchUpdateById(dipage.getRecords());


        return dailyExpensesPo;
    }


    private void processMonthly(DailyExpensesPo dailyExpensesPo) {
        MonthlyExpensesReq mcondition = new MonthlyExpensesReq();
        mcondition.setMonth(dailyExpensesPo.getMonth());
        mcondition.setYear(dailyExpensesPo.getYear());
        IPage<MonthlyExpensesPo> mipage = monthlyExpensesService.selectByCondition(mcondition);

        MonthlyExpensesPo monthlyExpensesPo = !mipage.getRecords().isEmpty() ? mipage.getRecords().get(0) : new MonthlyExpensesPo();

        // 计算月合计 此时的日总和已经是统计完毕无误的
        if(ObjectUtils.isEmpty(monthlyExpensesPo)) {
            monthlyExpensesPo.setMonthlyTotal(dailyExpensesPo.getSingleExpense() );
        }else{
            monthlyExpensesPo.setMonthlyTotal(dailyExpensesPo.getSingleExpense() + monthlyExpensesPo.getMonthlyTotal());
        }


        // 更新月合计
        monthlyExpensesService.save(monthlyExpensesPo);

    }

    private void processYearly(DailyExpensesPo dailyExpensesPo) {
        YearlyExpensesReq ycondition = new YearlyExpensesReq();
        ycondition.setYear(dailyExpensesPo.getYear());
        IPage<YearlyExpensesPo> yipage = yearlyExpensesServiec.selectByCondition(ycondition);

        YearlyExpensesPo yearlyExpensesPo = !yipage.getRecords().isEmpty() ? yipage.getRecords().get(0) : new YearlyExpensesPo();

        if(ObjectUtils.isEmpty(yearlyExpensesPo)) {
            yearlyExpensesPo.setYearlyTotal(yearlyExpensesPo.getYearlyTotal() );
        }else{
            yearlyExpensesPo.setYearlyTotal(yearlyExpensesPo.getYearlyTotal() + dailyExpensesPo.getSingleExpense());
        }

        yearlyExpensesServiec.save(yearlyExpensesPo);

    }
}
