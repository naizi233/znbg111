package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.AppraiseMapper;
import com.yzn.server.pojo.Appraise;
import com.yzn.server.service.IAppraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppraiseServiceImpl extends ServiceImpl<AppraiseMapper, Appraise> implements IAppraiseService {

    @Autowired
    private AppraiseMapper appraiseMapper;

    @Override
    public List<Appraise> getList() {
        return appraiseMapper.getList();
    }
}
