package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> getMenuByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenuRoleList();

    List<Menu> getAllMenus();
}
