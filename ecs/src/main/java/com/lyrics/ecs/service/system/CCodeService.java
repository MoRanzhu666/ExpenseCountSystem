package com.lyrics.ecs.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.po.system.CCodePo;
import com.lyrics.ecs.bean.req.system.CCodeReq;
import com.lyrics.ecs.bean.resp.system.CCodeResp;
import com.lyrics.ecs.mapper.system.CCodeMapper;
import com.lyrics.ecs.service.UsersService;
import com.lyrics.ecs.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CCodeService {
    @Autowired
    private CCodeMapper cCodeMapper;
    @Autowired
    private UsersService usersService;

    public CCodeResp getPage(CCodeReq cCodeReq) {
        IPage<CCodePo> cCodePoIPage = cCodeMapper.selectByCondition(cCodeReq);
        CCodeResp result = new CCodeResp();

        IPage<CCodeResp> resultPage = PageUtils.convertPage(cCodePoIPage, CCodeResp.class, resp -> {
            resp.setCreateByName(usersService.getById(resp.getCreateBy()).getName());
            resp.setUpdateByName(usersService.getById(resp.getUpdateBy()).getName());
        });

        BeanUtils.copyProperties(resultPage, result);


        return result;
    }
}
