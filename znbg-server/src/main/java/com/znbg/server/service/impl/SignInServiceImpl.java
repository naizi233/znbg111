package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.SignInMapper;
import com.yzn.server.pojo.SignIn;
import com.yzn.server.service.ISignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn> implements ISignInService {

    @Autowired
    private SignInMapper signInMapper;

    @Override
    public List<SignIn> getAll() {
        return signInMapper.getAll();
    }

    @Override
    public SignIn getByadminId(Integer adminId) {
        return signInMapper.getByadminId(adminId);
    }

    @Override
    public Integer getEidByAdminId(Integer adminId) {
        return signInMapper.getEidByAdminId(adminId);
    }

}
