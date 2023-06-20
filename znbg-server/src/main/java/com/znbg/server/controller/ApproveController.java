package com.yzn.server.controller;

import com.yzn.server.pojo.Admin;
import com.yzn.server.pojo.Approve;
import com.yzn.server.pojo.RespBean;
import com.yzn.server.service.IApproveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/approve")
@Api(value = "审批管理", tags = "审批管理")
public class ApproveController {

    @Autowired
    private IApproveService approveService;

    @ApiOperation("查询申请")
    @GetMapping("/get")
    public Approve getApprove(@RequestParam Integer a){
        if(a==1){
            return approveService.getApprove("请假");
        } else if(a==2){
            return approveService.getApprove("报销");
        } else {
            return approveService.getApprove("调动");
        }
    }

    @ApiOperation("新建申请")
    @PostMapping("/add")
    public RespBean addApprove(@RequestBody Approve a){
        Integer adminId = ((Admin) SecurityContextHolder
                .getContext()//获取登录用户全局上下文
                .getAuthentication()//获取验证后的信息
                .getPrincipal())//权限验证
                .getId();
        a.setEid(adminId);
        a.setApproveDate(LocalDateTime.now());
        a.setType("请假");
        a.setStatus("已提交");
        if(getApprove(1)!=null)
            return RespBean.success("已经存在未完成申请！");
        else if (approveService.save(a)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation("新建申请")
    @PostMapping("/addR")
    public RespBean addApproveR(@RequestBody Approve a){
        Integer adminId = ((Admin) SecurityContextHolder
                .getContext()//获取登录用户全局上下文
                .getAuthentication()//获取验证后的信息
                .getPrincipal())//权限验证
                .getId();
        a.setEid(adminId);
        a.setApproveDate(LocalDateTime.now());
        a.setType("报销");
        a.setStatus("已提交");
        if(getApprove(2)!=null)
            return RespBean.success("已经存在未完成申请！");
        else if (approveService.save(a)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation("新建申请")
    @PostMapping("/addT")
    public RespBean addApproveT(@RequestBody Approve a){
        Integer adminId = ((Admin) SecurityContextHolder
                .getContext()//获取登录用户全局上下文
                .getAuthentication()//获取验证后的信息
                .getPrincipal())//权限验证
                .getId();
        a.setEid(adminId);
        a.setApproveDate(LocalDateTime.now());
        a.setType("调动");
        a.setStatus("已提交");
        if(getApprove(3)!=null)
            return RespBean.success("已经存在未完成申请！");
        else if (approveService.save(a)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation("修改申请")
    @PutMapping("/update")
    public RespBean updateApprove(@RequestBody Approve a){
        String name = ((Admin) SecurityContextHolder
                .getContext()//获取登录用户全局上下文
                .getAuthentication()//获取验证后的信息
                .getPrincipal())//权限验证
                .getName();
        a.setApprovePerson(name);
        if(a.getStatus().equals("待审批")||a.getStatus().equals("已提交")){
            a.setStatus("已通过");
            if (approveService.updateById(a)){
                return RespBean.success("更新成功！");
            }
            return RespBean.error("更新失败！");
        }
        else
            return RespBean.success("已处理！");
    }

    @ApiOperation("删除申请")
    @DeleteMapping("/delete")
    public RespBean deleteApprove(@RequestParam("id")Long id){
        approveService.removeById(id);
        return RespBean.success("删除成功！");
    }

}
