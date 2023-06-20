package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.NationMapper;
import com.yzn.server.pojo.Nation;
import com.yzn.server.service.INationService;
import org.springframework.stereotype.Service;

@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
