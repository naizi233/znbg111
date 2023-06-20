package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.SignIn;
import io.swagger.models.auth.In;

import java.util.List;

public interface SignInMapper extends BaseMapper<SignIn> {
    SignIn getByadminId(Integer adminId);

    List<SignIn> getAll();

    Integer getEidByAdminId(Integer adminId);
}
