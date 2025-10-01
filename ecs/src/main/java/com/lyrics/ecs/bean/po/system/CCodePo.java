package com.lyrics.ecs.bean.po.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lyrics.ecs.bean.common.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("c_code")
public class CCodePo extends BasePo {

    private String category;

    private String code;

    private String describe;


}
