package com.example.autocodetemplate.ohter.practice.base;

import java.io.Serializable;

public class JvmDispatch {


}

// 静态多分派  参数【输入是什么就是什么】类型
class StaticDispatch {
    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println(" hello, guy！");
    }

    public void sayHello(Man guy) {
        System.out.println(" hello, gentleman！");
    }

    public void sayHello(Woman guy) {
        System.out.println(" hello, lady！");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}

// 重载只能是最合适  自动类型转换，装箱，查找父类接口
class Overload {
    public static void sayHello(Object arg) {
        System.out.println(" hello Object");
    }

//    public static void sayHello(int arg) {
//        System.out.println(" hello int");
//    }

    public static void sayHello(long arg) {
        System.out.println(" hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println(" hello Character");
    }
//
//    public static void sayHello(char arg) {
//        System.out.println(" hello char");
//    }

    public static void sayHello(Serializable arg) {
        System.out.println(" hello Serializable");
    }

    public static void main(String[] args) {
        sayHello('a');
    }
}

// 动态分派  查找引用实际指向对象类型，返回方法直接引用，若无，查找父类方法
class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println(" man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println(" woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}