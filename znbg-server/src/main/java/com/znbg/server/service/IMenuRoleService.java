package com.yzn.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzn.server.pojo.MenuRole;
import com.yzn.server.pojo.RespBean;

import java.util.List;

public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, List<Integer> mids);
}
