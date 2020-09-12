package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 单例模式
 *
 * 定义：一个类只允许创建一个对象
 * 用处：1资源访问冲突，2只应该在系统中保存一份数据的场景
 * 实现：懒汉，饿汉，双重检测，静态内部类，枚举
 * 问题：对OOP特性支持不好 2 隐藏类之间的关系 3对代码扩展性不好 4对代码可测试性不好 5不支持有参构造函数
 */
public class SingleTonPattern {
    public static void main(String[] args) {

    }
}

class HungrySingleTon {
    private HungrySingleTon(){}

    private static HungrySingleTon hungrySingleTon = new HungrySingleTon();

    public static HungrySingleTon getInstance() {
        return hungrySingleTon;
    }
}

class lazySingleTonOfDcl {
    private lazySingleTonOfDcl(){}

    private static volatile lazySingleTonOfDcl singleTon = null;

    public static lazySingleTonOfDcl getInstance() {
        if (singleTon == null) {
            synchronized (lazySingleTonOfDcl.class) {
                if (singleTon == null) {
                    singleTon = new lazySingleTonOfDcl();
                }
            }
        }
        return singleTon;
    }
}