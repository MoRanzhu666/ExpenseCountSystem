package com.lyrics.ecs.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.exceptions.BadRequestException;
import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.po.system.CCodePo;
import com.lyrics.ecs.bean.req.system.CCodeReq;
import com.lyrics.ecs.bean.resp.system.CCodeResp;
import com.lyrics.ecs.mapper.system.CCodeMapper;
import com.lyrics.ecs.service.UsersService;
import com.lyrics.ecs.utils.ObjectUtils;
import com.lyrics.ecs.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(rollbackFor = Exception.class)
    public void add(CCodeReq cCodeReq) {
        if(ObjectUtils.isEmpty(cCodeReq)) {
            throw new BadRequestException("参数校验失败");
        }

        CCodePo cCodePo = new CCodePo();
        BeanUtils.copyProperties(cCodeReq, cCodePo);
        cCodePo.generateCreateInfo();
        cCodePo.generateUpdateInfo();
        cCodeMapper.insertInfo(cCodePo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(CCodeReq cCodeReq) {
        if(ObjectUtils.isEmpty(cCodeReq) || ObjectUtils.isEmpty(cCodeReq.getId())) {
            throw new BadRequestException("参数校验失败");
        }

        CCodePo cCodePo = new CCodePo();
        BeanUtils.copyProperties(cCodeReq, cCodePo);
        cCodePo.generateUpdateInfo();
        cCodeMapper.updateInfoById(cCodePo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(CCodeReq cCodeReq) {
        if(ObjectUtils.isEmpty(cCodeReq.getIds())) {
            throw new BadRequestException("参数校验失败");
        }

        cCodeMapper.deleteBatchIds(cCodeReq.getIds());
    }

    public List<CCodeResp> getCategorySelector(String category) {
        return cCodeMapper.selectCategorySelector(category);
    }
}
