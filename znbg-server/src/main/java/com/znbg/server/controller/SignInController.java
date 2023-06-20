package com.yzn.server.controller;

import com.yzn.server.pojo.Admin;
import com.yzn.server.pojo.SignIn;
import com.yzn.server.pojo.RespBean;
import com.yzn.server.service.ISignInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(tags = "签到")
@RestController
@RequestMapping("/signIn")
public class SignInController {

    private int[] months={31,28,31,30,31,30,31,31,30,31,30,31};

    @Autowired
    private ISignInService signInService;

    @ApiOperation("统计所有签到信息")
    @GetMapping("/list")
    public List<SignIn> getAllSignIn(){
        List<SignIn> list = signInService.getAll();
        return list;
    }

    @ApiOperation("查询当前用户签到信息")
    @GetMapping("/admin")
    public SignIn getSignIn(){
        Integer adminId = ((Admin) SecurityContextHolder
            .getContext()//获取登录用户全局上下文
            .getAuthentication()//获取验证后的信息
            .getPrincipal())//权限验证
            .getId();
        SignIn signin = signInService.getByadminId(adminId);
        Calendar cal = Calendar.getInstance();
        float sumDay=signin.getNumber()+signin.getNumberLate();
        float num = sumDay/months[cal.get(Calendar.MONTH)]*100;
        signin.setPercentage(Math.round(num));
        return signin;
    }

    @ApiOperation("修改签到次数")
    @PutMapping("/update")
    public RespBean updateSignIn(){
        Integer adminId = ((Admin) SecurityContextHolder
                .getContext()//获取登录用户全局上下文
                .getAuthentication()//获取验证后的信息
                .getPrincipal())//权限验证
                .getId();
        Integer eid = signInService.getEidByAdminId(adminId);
        SignIn signIn = signInService.getById(eid);
        if(!signIn.getLastDate().isBefore(LocalDate.now())){
            return RespBean.success("请不要重复打卡！",2);
        } else {
            signIn.setLastDate(LocalDate.now());
            Date date = new Date();
            int hours = date.getHours();
            if (hours <= 8) {
                signIn.setNumber(signIn.getNumber() + 1);
            } else {
                signIn.setNumberLate(signIn.getNumberLate() + 1);
            }
            if (signInService.updateById(signIn)) {
                return RespBean.success("打卡成功！",1);
            }
            return RespBean.error("打卡失败！");
        }
    }

    @ApiOperation("删除记录")
    @DeleteMapping("/delete")
    public RespBean deleteSignIn(@RequestParam("id")Long id){
        signInService.removeById(id);
        return RespBean.success("删除成功！");
    }

    @ApiOperation("批量删除记录")
    @DeleteMapping("/delete/ids")
    public RespBean deleteByIds(@RequestParam("ids")List<Long> ids){
        signInService.removeByIds(ids);
        return RespBean.success("删除成功");
    }

}
