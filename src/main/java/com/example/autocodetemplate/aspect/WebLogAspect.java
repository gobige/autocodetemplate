package com.example.autocodetemplate.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class WebLogAspect {

    private final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(* com.example.autocodetemplate.controller..*.*(..))")
    public void webLog(){}

    private ThreadLocal<Long> localStartTime = new ThreadLocal();

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            localStartTime.set(System.currentTimeMillis());
            logger.info("aop记录web日志开始：{}",localStartTime.get());

            logger.info("请求url:[{}],请求方法[{}]，请求ip[{}]",request.getRequestURL(),request.getMethod(),request.getRemoteAddr());
        }

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        if (ret != null) {
            logger.info("aop记录web日志完成：【{}】耗时【{}】",ret.toString(),(System.currentTimeMillis() - localStartTime.get()));
        }

    }
}
