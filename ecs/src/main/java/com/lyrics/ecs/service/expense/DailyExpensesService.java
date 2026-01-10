package com.lyrics.ecs.service.expense;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.exceptions.BadRequestException;
import com.lyrics.ecs.bean.po.expense.DailyExpensesPo;
import com.lyrics.ecs.bean.po.expense.MonthlyExpensesPo;
import com.lyrics.ecs.bean.po.expense.YearlyExpensesPo;
import com.lyrics.ecs.bean.req.expense.DailyExpensesReq;
import com.lyrics.ecs.bean.req.expense.MonthlyExpensesReq;
import com.lyrics.ecs.bean.req.expense.YearlyExpensesReq;
import com.lyrics.ecs.bean.resp.expense.DailyExpensesResp;
import com.lyrics.ecs.bean.resp.expense.ExpensesResp;
import com.lyrics.ecs.mapper.DailyExpensesMapper;
import com.lyrics.ecs.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    public void add(DailyExpensesResp dailyExpensesResp) {
        if (ObjectUtils.isEmpty(dailyExpensesResp)) {
            throw new BadRequestException("参数校验失败");
        }
        DailyExpensesPo dailyExpensesPo = new DailyExpensesPo();
        BeanUtils.copyProperties(dailyExpensesResp, dailyExpensesPo);
        LocalDate date = dailyExpensesResp.getDate();
        dailyExpensesPo.setYear(date.getYear());
        dailyExpensesPo.setMonth(date.getMonthValue());
        dailyExpensesPo.setDay(date.getDayOfMonth());
        dailyExpensesPo.setDailyTotal(0.0);

        dailyExpensesPo.generateCreateInfo();
        dailyExpensesPo.generateUpdateInfo();
        dailyExpensesMapper.insert(dailyExpensesPo);
        this.operationDataAfter(dailyExpensesPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateById(List<DailyExpensesPo> dailyExpensesPoList) {
        dailyExpensesPoList.forEach(dailyExpensesPo -> {
            dailyExpensesMapper.updateById(dailyExpensesPo);
        });
    }

    public IPage<DailyExpensesPo> selectByCondition(DailyExpensesReq dcondition) {
        return dailyExpensesMapper.selectByCondition(dcondition);
    }

    public DailyExpensesPo selectById(String id) {
        return dailyExpensesMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(DailyExpensesResp dailyExpensesResp) {
        if (ObjectUtils.isEmpty(dailyExpensesResp)) {
            throw new BadRequestException("参数校验失败");
        }
        DailyExpensesPo dailyExpensesPo = new DailyExpensesPo();
        BeanUtils.copyProperties(dailyExpensesResp, dailyExpensesPo);
        LocalDate date = dailyExpensesResp.getDate();
        dailyExpensesPo.setYear(date.getYear());
        dailyExpensesPo.setMonth(date.getMonthValue());
        dailyExpensesPo.setDay(date.getDayOfMonth());

        dailyExpensesPo.generateUpdateInfo();
        dailyExpensesMapper.updateById(dailyExpensesPo);
        operationDataAfter(dailyExpensesPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void operationDataAfter(DailyExpensesPo dailyExpensesPo) {
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

        DailyExpensesReq condition = new DailyExpensesReq();
        condition.setYear(dailyExpensesPo.getYear());
        condition.setMonth(dailyExpensesPo.getMonth());
        condition.setSize(Long.MAX_VALUE);
        IPage<DailyExpensesPo> dmipage = this.selectByCondition(condition);

        // 计算月合计 此时的日总和已经是统计完毕无误的
        if (ObjectUtils.isEmpty(monthlyExpensesPo)) {
            monthlyExpensesPo.setMonthlyTotal(dailyExpensesPo.getSingleExpense());
            monthlyExpensesPo.setYear(dailyExpensesPo.getYear());
            monthlyExpensesPo.setMonth(dailyExpensesPo.getMonth());
        } else {
            Double total = 0.0;
            for (DailyExpensesPo dpo : dmipage.getRecords()) {
                total += dpo.getSingleExpense();
            }
            monthlyExpensesPo.setMonthlyTotal(total);
        }

        // 更新月合计
        monthlyExpensesService.save(monthlyExpensesPo);

    }

    private void processYearly(DailyExpensesPo dailyExpensesPo) {
        YearlyExpensesReq ycondition = new YearlyExpensesReq();
        ycondition.setYear(dailyExpensesPo.getYear());
        IPage<YearlyExpensesPo> yipage = yearlyExpensesServiec.selectByCondition(ycondition);
        YearlyExpensesPo yearlyExpensesPo = !yipage.getRecords().isEmpty() ? yipage.getRecords().get(0) : new YearlyExpensesPo();

        DailyExpensesReq condition = new DailyExpensesReq();
        condition.setYear(dailyExpensesPo.getYear());
        condition.setSize(Long.MAX_VALUE);
        IPage<DailyExpensesPo> dyipage = this.selectByCondition(condition);

        if (ObjectUtils.isEmpty(yearlyExpensesPo)) {
            yearlyExpensesPo.setYearlyTotal(yearlyExpensesPo.getYearlyTotal());
            yearlyExpensesPo.setYear(dailyExpensesPo.getYear());
        } else {
            Double total = 0.0;
            for (DailyExpensesPo dpo : dyipage.getRecords()) {
                total += dpo.getSingleExpense();
            }
            yearlyExpensesPo.setYearlyTotal(total);
        }

        yearlyExpensesServiec.save(yearlyExpensesPo);

    }

    public IPage<DailyExpensesResp> getPage(DailyExpensesReq req) {
        if (ObjectUtils.isEmpty(req)) {
            req = new DailyExpensesReq();
        }
        if (ObjectUtils.isEmpty(req.getId())) {
            return selectRespByCondition(req);
        } else {
            return selectRespById(req.getId());
        }

    }

    private IPage<DailyExpensesResp> selectRespById(String id) {
        return dailyExpensesMapper.selectRespById(id);
    }

    private IPage<DailyExpensesResp> selectRespByCondition(DailyExpensesReq req) {
        return dailyExpensesMapper.selectRespByCondition(req);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        DailyExpensesPo dailyExpensesPo = this.selectById(id);
        dailyExpensesMapper.deleteById(id);
        operationDataAfter(dailyExpensesPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<String> ids) {
        List<DailyExpensesPo> dailyExpensesPo = this.selectByIds(ids);
        dailyExpensesMapper.deleteBatchIds(ids);
        dailyExpensesPo.forEach(this::operationDataAfter);
    }

    private List<DailyExpensesPo> selectByIds(List<String> ids) {
        return dailyExpensesMapper.selectBatchIds(ids);
    }

    public ExpensesResp getSelectedExpense(DailyExpensesReq req) {
//        HashSet<String> months = new HashSet<>();
//        HashSet<String> years = new HashSet<>();
//
//        ExpensesResp expensesResp = new ExpensesResp();
//        List<DailyExpensesPo> dailyExpensesPos = selectByIds(req.getIds());
//        List<MonthlyExpensesPo> monthlyExpensesPos = new ArrayList<>();
//        List<YearlyExpensesPo> yearlyExpensesPos = new ArrayList<>();
//
//        dailyExpensesPos.forEach(dpo -> {
//            months.add(dpo.getMonth().toString());
//            years.add(dpo.getYear().toString());
//
//            HashMap<String, Double> map = new HashMap<>();
//            if (ObjectUtils.isEmpty(expensesResp.getDailyTotal().get(dpo.getDay().toString()))) {
//                map.put(dpo.getDay().toString(), dpo.getSingleExpense());
//                expensesResp.setDailyTotal(map);
//            } else {
//                map.put(dpo.getDay().toString(), dpo.getSingleExpense() + map.get(dpo.getDay().toString()));
//                expensesResp.setDailyTotal(map);
//            }
//
//        });
//        MonthlyExpensesReq mcondition = new MonthlyExpensesReq();
//        mcondition.setMonths(months);
//        monthlyExpensesPos = monthlyExpensesService.selectByCondition(mcondition).getRecords();
//        YearlyExpensesReq ycondition = new YearlyExpensesReq();
//        ycondition.setYears(years);
//        yearlyExpensesPos = yearlyExpensesServiec.selectByCondition(ycondition).getRecords();
//
//        monthlyExpensesPos.forEach(mpo -> {
//            HashMap<String, Double> map = new HashMap<>();
//            if (ObjectUtils.isEmpty(expensesResp.getMonthlyTotal().get(mpo.getMonth().toString()))) {
//                map.put(mpo.getMonth().toString(), mpo.getSingleExpense());
//                expensesResp.setDailyTotal(map);
//            } else {
//                map.put(dpo.getDay().toString(), dpo.getSingleExpense() + map.get(dpo.getDay().toString()));
//                expensesResp.setDailyTotal(map);
//            }
//
//        });


        return null;
    }
}
