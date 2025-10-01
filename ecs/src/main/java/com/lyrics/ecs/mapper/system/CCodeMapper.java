package com.lyrics.ecs.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.DailyExpensesPo;
import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.po.system.CCodePo;
import com.lyrics.ecs.bean.req.system.CCodeReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CCodeMapper extends BaseMapper<CCodePo> {

    IPage<CCodePo> selectByCondition(@Param("condition") CCodeReq cCodeReq);
}
