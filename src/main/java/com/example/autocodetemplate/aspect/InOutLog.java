package com.example.autocodetemplate.aspect;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Order(2147483647)
public @interface InOutLog {
    String level() default "INFO";
}
