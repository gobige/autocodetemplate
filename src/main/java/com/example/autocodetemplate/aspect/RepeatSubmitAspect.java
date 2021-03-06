package com.example.autocodetemplate.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验表单重复提交
 */
@Aspect
@Component
public class RepeatSubmitAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointcut(NoRepeatSubmit noRepeatSubmit) {

    }

    @Around("pointcut(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) {
        String uniq = noRepeatSubmit.uniqueKey();

        boolean setSuccess = redisTemplate.opsForValue().setIfPresent(uniq, "");
        if (!setSuccess) {

        }

        return null;
    }
}

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface NoRepeatSubmit {

    String desc() default "";
    String uniqueKey() default "UUID().toString()";
}