package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.Appraise;

import java.util.List;

public interface AppraiseMapper extends BaseMapper<Appraise> {

    List<Appraise> getList();
}
