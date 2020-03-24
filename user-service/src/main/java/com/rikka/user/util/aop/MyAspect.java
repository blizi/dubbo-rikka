package com.rikka.user.util.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Aspect
@Component
@Slf4j
public class MyAspect {
    @Pointcut("@annotation(com.rikka.user.util.MyTest)")
    public void annotationPointcut() {

    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        // 此处进入到方法前  可以实现一些业务逻辑
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] params = methodSignature.getParameterNames();// 获取参数名称
        Object[] args = joinPoint.getArgs();// 获取参数值
        log.info("params:{}", params[0]);
        log.info("params:{}", params[1]);
        log.info("args:{}", args[0]);
        log.info("args:{}", args[1]);
        Assert.notNull(null, "授权错误");
    }
}
