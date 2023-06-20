package com.yzn.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzn.server.pojo.SignIn;

import java.util.List;

public interface ISignInService extends IService<SignIn> {

    List<SignIn> getAll();

    SignIn getByadminId(Integer adminId);

    Integer getEidByAdminId(Integer adminId);
}
