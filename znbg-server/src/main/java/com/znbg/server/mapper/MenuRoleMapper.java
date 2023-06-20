package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 批量添加
     * @param rid
     * @param mids
     */
    Integer batchInset(@Param("rid") Integer rid, @Param("mids") List<Integer> mids);
}
