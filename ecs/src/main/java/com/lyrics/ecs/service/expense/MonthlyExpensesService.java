package com.lyrics.ecs.service.expense;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.common.BasePo;
import com.lyrics.ecs.bean.po.UsersPo;
import com.lyrics.ecs.bean.po.expense.MonthlyExpensesPo;
import com.lyrics.ecs.bean.req.expense.MonthlyExpensesReq;
import com.lyrics.ecs.bean.resp.expense.MonthlyExpensesResp;
import com.lyrics.ecs.mapper.MonthlyExpensesMapper;
import com.lyrics.ecs.service.UsersService;
import com.lyrics.ecs.utils.ObjectUtils;
import com.lyrics.ecs.utils.PageUtils;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MonthlyExpensesService {
    @Autowired
    private MonthlyExpensesMapper monthlyExpensesMapper;
    @Autowired
    private UsersService usersService;

    private Logger logger = Logger.getLogger(String.valueOf(MonthlyExpensesService.class));

    public void save(MonthlyExpensesPo monthlyExpensesPo) {
        if(ObjectUtils.isEmpty(monthlyExpensesPo

                .getId())){
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

    public IPage< MonthlyExpensesResp> getPage(@Valid MonthlyExpensesReq req) {
        IPage<MonthlyExpensesResp> monthlyExpensesRespIPage = monthlyExpensesMapper.getPage(req);
        List<MonthlyExpensesResp> records = monthlyExpensesRespIPage.getRecords();
        List<String> allIds = records.stream()
                .flatMap(record -> Stream.of( // 合并
                        record.getCreateBy(),
                        record.getUpdateBy()
                ))
                .filter(Objects::nonNull)  // 过滤掉null值
                .distinct()  // 去重
                .toList();

        Map<String, String> userMap = usersService.getUserNameMapByIds(allIds);
        for (MonthlyExpensesResp record : records) {
            record.setCreateByName( userMap.get(record.getCreateBy()));
            record.setUpdateByName( userMap.get(record.getUpdateBy()));
        }
        return  monthlyExpensesRespIPage;
    }
}
