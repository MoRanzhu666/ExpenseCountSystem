package com.lyrics.ecs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyrics.ecs.bean.po.DailyExpensesPo;
import com.lyrics.ecs.bean.po.UsersPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends BaseMapper<UsersPo> {

}
