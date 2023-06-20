package com.yzn.server.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 全局异常处理
 */
@RestControllerAdvice
public class GlobalException {

//    @ExceptionHandler(SQLException.class)
//    public RespBean mySqlException(SQLException e){
//        if (e instanceof SQLIntegrityConstraintViolationException){
//            return RespBean.error("该数据有关联数据，操作失败！");
//        }
//        //除了上面捕获的知道sql异常，其他sql异常都报这个错误
//        return RespBean.error("数据异常，操作失败!");
//    }
}