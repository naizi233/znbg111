package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.PoliticsStatusMapper;
import com.yzn.server.pojo.PoliticsStatus;
import com.yzn.server.service.IPoliticsStatusService;
import org.springframework.stereotype.Service;

@Service
public class PoliticsStatusServiceImpl extends ServiceImpl<PoliticsStatusMapper, PoliticsStatus> implements IPoliticsStatusService {

}
