package com.example.autocodetemplate.aspect;

import org.aspectj.lang.annotation.Aspect;
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
//
//    @Autowired
//    private RedisLock redisLock;
//
//    @Pointcut("@annotation(noRepeatSubmit)")
//    public void pointcut(NoRepeatSubmit noRepeatSubmit) {
//
//    }
//
//    @Around("pointcut(noRepeatSubmit)")
//    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) {
//        int lockSeconds = noRepeatSubmit.locriteria
//    }
}

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface NoRepeatSubmit {

    String desc() default "";
}