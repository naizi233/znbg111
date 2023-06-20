package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.MenuRoleMapper;
import com.yzn.server.pojo.MenuRole;
import com.yzn.server.pojo.RespBean;
import com.yzn.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    MenuRoleMapper menuRoleMapper;


    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, List<Integer> mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (mids == null || mids.size() == 0){
            return RespBean.success("更新成功!");
        }
        //批量添加权限
        Integer result = menuRoleMapper.batchInset(rid, mids);
        if (result == mids.size()) return RespBean.success("修改成功！");
        return RespBean.error("修改失败！");
    }
}
