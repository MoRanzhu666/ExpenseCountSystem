package com.lyrics.ecs.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.system.CCodePo;
import com.lyrics.ecs.bean.req.system.CCodeReq;
import com.lyrics.ecs.bean.resp.system.CCodeResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CCodeMapper extends BaseMapper<CCodePo> {

    IPage<CCodePo> selectByCondition(@Param("condition") CCodeReq cCodeReq);


    void insertInfo(CCodePo cCodePo);

    void updateInfoById(CCodePo cCodePo);

    List<CCodeResp> selectCategorySelector(@Param("category") String category);


}
