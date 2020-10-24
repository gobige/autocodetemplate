package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 单例模式
 *
 * 定义：一个类只允许创建一个对象
 * 用处：1资源访问冲突(使用同一个对象对某个业务做操作，例如：记录日志到文件)，2只应该在系统中保存一份数据的场景
 * 实现：懒汉，饿汉，双重检测，静态内部类，枚举
 * 问题：1 对OOP特性支持不好 唯一的对象实现方式已经使用方式，无法对不同业务做不同的扩展
 *      2 隐藏类之间的关系 单例不需要创建，不需要依赖参数传递，影响可读性
 *      3 对代码扩展性不好 例如：我们需要将慢sql进行隔离到其他线程池，避免资源的长期占有，需要在创建一个线程池
 *      4 对代码可测试性不好 无法依赖参数传递，无法对其MOCK
 *      5 不支持有参构造函数  通过先init方法，再调用getInstance方法 2 参数放入getInstance 3通过config静态类进行参数传入配置
 *
 *  解决：通过外部依赖注入该单例类，实现扩展性优化
 *
 *  线程单例使用hashmap来制定唯一，进程单例使用锁来进行对象加锁
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