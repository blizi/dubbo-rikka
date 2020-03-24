package com.rikka.common.exception;


import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class MyExceptiopn {
    /**
     * 全局异常返回的信息
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object myException(Exception e){
        e.printStackTrace();
        return "内部错误，错误原因："+e.getMessage();
    }


    /**
     * 接口错误时的处理(404处理)
     */

}
