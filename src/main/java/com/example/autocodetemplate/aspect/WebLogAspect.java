package com.example.autocodetemplate.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Aspect // 切面
 * @JoinPoint // 连接点
 * Advice 通知 连接点执行动作
 * Pointcut 切入点 如何匹配连接点
 * introduction 引入 为现有类型声明额外方法和属性
 * target object 目标对象
 * aop proxy aop代理对象，jdk，cglib
 * weaving 织入 连接切面和目标对象或类型创建代理过程
 */
@Component
@Aspect
@Slf4j
public class WebLogAspect {


    @Pointcut("execution(* com.example.autocodetemplate.controller..*.*(..))")
    public void webLog(){}

    private ThreadLocal<Long> localStartTime = new ThreadLocal();

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            localStartTime.set(System.currentTimeMillis());
            log.info("aop记录web日志开始：{}",localStartTime.get());

            log.info("请求url:[{}],请求方法[{}]，请求ip[{}]",request.getRequestURL(),request.getMethod(),request.getRemoteAddr());
        }

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        if (ret != null) {
            log.info("aop记录web日志完成：【{}】耗时【{}】",ret.toString(),(System.currentTimeMillis() - localStartTime.get()));
        }

    }
}
