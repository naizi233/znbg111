package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.Approve;
import org.apache.ibatis.annotations.Param;

public interface ApproveMapper extends BaseMapper<Approve> {

    Approve getApprove(@Param("eid") Integer eid, @Param("type") String a);

}
