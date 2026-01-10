package com.lyrics.ecs.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyrics.ecs.bean.po.system.CCodePo;
import com.lyrics.ecs.bean.po.ResultPo;
import com.lyrics.ecs.bean.req.system.CCodeReq;
import com.lyrics.ecs.bean.resp.system.CCodeResp;
import com.lyrics.ecs.service.system.CCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/c_code")
public class CCodeController {
    @Autowired
    private CCodeService cCodeService;

    @GetMapping("/getPage")
    public ResultPo<CCodeResp> getPage(CCodeReq cCodeReq) {
        return ResultPo.success( cCodeService.getPage(cCodeReq));
    }

    @GetMapping("/categorySelector")
    public ResultPo<List<CCodeResp>> categorySelector(@RequestParam(value = "category", required = false) String category) {
        return ResultPo.success(cCodeService.getCategorySelector(category));
    }

    @PostMapping("/add")
    public ResultPo<CCodeResp> add(@RequestBody CCodeReq cCodeReq) {
        cCodeService.add(cCodeReq);
        return ResultPo.success();
    }


    @PutMapping("/update")
    public ResultPo<CCodeResp> update(@RequestBody CCodeReq cCodeReq) {
        cCodeService.update(cCodeReq);
        return ResultPo.success();
    }


    @DeleteMapping("/deleteByIds")
    public ResultPo<CCodeResp> deleteByIds( CCodeReq cCodeReq) {
        cCodeService.deleteByIds(cCodeReq);
        return ResultPo.success();
    }

}
