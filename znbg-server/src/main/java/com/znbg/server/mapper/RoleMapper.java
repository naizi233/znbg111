package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(@Param("adminId") Integer adminId);
}
