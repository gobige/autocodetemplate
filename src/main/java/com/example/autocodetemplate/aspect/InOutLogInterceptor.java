package com.example.autocodetemplate.aspect;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class InOutLogInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(InOutLogInterceptor.class);
    private static final String INFO = "INFO";
    private static final String DEBUG = "DEBUG";
    private static final String ERROR = "ERROR";

    public InOutLogInterceptor() {
    }

    @Pointcut("@annotation(com.fenqile.platform.common.aop.annotation.InOutLog)||@within(com.fenqile.platform.common.aop.annotation.InOutLog)")
    public void inOutLogPointcut() {
    }

    @Around("inOutLogPointcut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        String level = this.getLevel(pjp);

        try {
            this.beginLog(level, pjp);
            long start = System.currentTimeMillis();
            Object respone = pjp.proceed();
            long end = System.currentTimeMillis();
            this.endLog(level, pjp, end, start, respone);
            return respone;
        } catch (Exception var8) {
            this.exceptionLog(pjp, var8);
            throw var8;
        }
    }

    private String getLevel(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        InOutLog inOutLog = (InOutLog)pjp.getTarget().getClass().getAnnotation(InOutLog.class);
        if (inOutLog != null) {
            return inOutLog.level();
        } else {
            MethodSignature methodSignature = this.getMehtodSignature(pjp);
            Method method = this.getMethod(pjp.getTarget(), methodSignature);
            inOutLog = (InOutLog)method.getAnnotation(InOutLog.class);
            return inOutLog != null ? inOutLog.level() : "INFO";
        }
    }

    private MethodSignature getMehtodSignature(ProceedingJoinPoint pjp) {
        return (MethodSignature)pjp.getSignature();
    }

    private Method getMethod(Object target, MethodSignature methodSignature) throws NoSuchMethodException {
        return target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    private void beginLog(String level, ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = this.getMehtodSignature(pjp);
        this.log(level, "{}.{}|IN|{}", pjp.getTarget().getClass().getName(), methodSignature.getMethod().getName(), JSONObject.toJSONString(pjp.getArgs()));
    }

    private void endLog(String level, ProceedingJoinPoint pjp, long end, long start, Object respone) {
        MethodSignature methodSignature = this.getMehtodSignature(pjp);
        this.log(level, "{}.{}|OUT|{}ms|{}", pjp.getTarget().getClass().getName(), methodSignature.getMethod().getName(), end - start, JSONObject.toJSONString(respone));
    }

    private void exceptionLog(ProceedingJoinPoint pjp, Exception e) {
        MethodSignature methodSignature = this.getMehtodSignature(pjp);
        this.log("ERROR", "{}.{}|Exception|{}", pjp.getTarget().getClass().getName(), methodSignature.getMethod().getName(), ExceptionUtils.getFullStackTrace(e));
    }

    private void log(String level, String format, Object... arguments) {
        if (level.equals("INFO")) {
            this.infoLog(format, arguments);
        } else if (level.equals("DEBUG")) {
            this.debugLog(format, arguments);
        } else if (level.equals("ERROR")) {
            this.errorLog(format, arguments);
        } else {
            this.infoLog(format, arguments);
        }

    }

    private void infoLog(String format, Object... arguments) {
        LOGGER.info(format, arguments);
    }

    private void debugLog(String format, Object... arguments) {
        LOGGER.debug(format, arguments);
    }

    private void errorLog(String format, Object... arguments) {
        LOGGER.error(format, arguments);
    }
}
