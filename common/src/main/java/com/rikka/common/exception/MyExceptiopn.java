package com.rikka.common.exception;


import com.auth0.jwt.exceptions.JWTVerificationException;
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
     * jwt 解析错误
     * @return
     */
    @ExceptionHandler(JWTVerificationException.class)
    @ResponseBody
    public Object JwtException(Exception e){
        e.printStackTrace();
        return "内部错误，错误原因："+e.getMessage();
    }

    /**
     * 接口错误时的处理(404处理)
     */

}
