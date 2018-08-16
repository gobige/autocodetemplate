package com.example.autocodetemplate.ohter.practice.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@MyIF(author = "muyibeyond", desc = "test anotation class")
public class AnnotationTest {
    @MyIF(author = "muyibeyond", desc = "test anotation method")
    public void method() {
        System.out.println("do something");
    }

    public static void main(String[] args) {
        try {
            // 加载类
            Class c = Class.forName("practice.base.AnnotationTest");
            // 获取是否有对应注解
            boolean classAnoIsExist = c.isAnnotationPresent(MyIF.class);

            if (classAnoIsExist) {
                // 拿到注解实例，解析注解
                MyIF d = (MyIF) c.getAnnotation(MyIF.class);
                System.out.println("编写这个类的作者：" + d.author() + "类描述：" + d.desc());
            }

            // 获取类方法
            Method[] ms = c.getMethods();
            for (Method m : ms) {
                boolean meheodAnoIsExist = m.isAnnotationPresent(MyIF.class);
                if (meheodAnoIsExist) {
                    MyIF d = m.getAnnotation(MyIF.class);
                    System.out.println("编写这个方法的作者：" + d.author() + "类描述：" + d.desc());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyIF {
    String author() default "yates";

    String desc() default "";
}