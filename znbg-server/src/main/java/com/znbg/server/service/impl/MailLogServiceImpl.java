package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.MailLogMapper;
import com.yzn.server.pojo.MailLog;
import com.yzn.server.service.IMailLogService;
import org.springframework.stereotype.Service;

@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
