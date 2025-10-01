package com.lyrics.ecs.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.system.CCodePo;
import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.req.system.CCodeReq;
import com.lyrics.ecs.bean.resp.system.CCodeResp;
import com.lyrics.ecs.service.system.CCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/c_code")
public class CCodeController {
    @Autowired
    private CCodeService cCodeService;

    @GetMapping("/getPage")
    public ResultPo<CCodeResp> getPage(CCodeReq cCodeReq) {
        return ResultPo.success( cCodeService.getPage(cCodeReq), "查询成功");
    }

}
