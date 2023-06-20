package com.yzn.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzn.server.pojo.Appraise;

import java.util.List;

public interface IAppraiseService extends IService<Appraise> {

    List<Appraise> getList();
}
