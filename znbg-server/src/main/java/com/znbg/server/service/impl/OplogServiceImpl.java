package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.OplogMapper;
import com.yzn.server.pojo.Oplog;
import com.yzn.server.service.IOplogService;
import org.springframework.stereotype.Service;

@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
