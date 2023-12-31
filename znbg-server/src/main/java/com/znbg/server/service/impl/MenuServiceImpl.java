package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.MenuMapper;
import com.yzn.server.pojo.Admin;
import com.yzn.server.pojo.Menu;
import com.yzn.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //获取当前登录用户的ID
//    Integer adminId = ((Admin) SecurityContextHolder
//            .getContext()
//            .getAuthentication()
//            .getPrincipal())
//            .getId();
//
//    ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//    List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
//    //如果是空的，就用mapper去获取当前登录用户的菜单，当前用户id查询的
//        if (CollectionUtils.isEmpty(menus)){
//        menus = menuMapper.getMenuByAdminId(adminId);
//        //将数据设置到redis中
//        valueOperations.set("menu_"+adminId,menus);
//    }
//        return menus;

    /**
     * 根据用户ID查询菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuByAdminId() {
        //用 security 自带的方法，来获取当前登录用户的基本信息，其中就包括ID
        Integer adminId = ((Admin) SecurityContextHolder
                .getContext()//获取登录用户全局上下文
                .getAuthentication()//获取验证后的信息
                .getPrincipal())//权限验证
                .getId();

        //将菜单信息放入缓存
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //从redis获取菜单数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        //如果是空的，就用mapper去获取当前登录用户的菜单，当前用户id查询的
        if (CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenuByAdminId(adminId);
            //将数据设置到redis中
            valueOperations.set("menu_"+adminId,menus);
        }
        return menus;
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuRoleList() {
        return menuMapper.getMenuRoleList();
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }


}
