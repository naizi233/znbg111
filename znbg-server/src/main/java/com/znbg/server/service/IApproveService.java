package com.yzn.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzn.server.pojo.Approve;

public interface IApproveService extends IService<Approve> {
    Approve getApprove(String a);
}
