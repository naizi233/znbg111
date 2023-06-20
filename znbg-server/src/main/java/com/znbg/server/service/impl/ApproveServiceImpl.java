package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.ApproveMapper;
import com.yzn.server.pojo.Admin;
import com.yzn.server.pojo.Approve;
import com.yzn.server.service.IApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ApproveServiceImpl extends ServiceImpl<ApproveMapper, Approve> implements IApproveService {

    @Autowired
    private ApproveMapper approveMapper;

    @Override
    public Approve getApprove(String a) {
        Integer adminId = ((Admin) SecurityContextHolder
                .getContext()//获取登录用户全局上下文
                .getAuthentication()//获取验证后的信息
                .getPrincipal())//权限验证
                .getId();
        return approveMapper.getApprove(adminId,a);
    }
}
