package com.example.autocodetemplate.ohter.practice.spi;

/**
 * Java SPI ：Service Provider Interface,一种服务扩展机制，在相应配置文件中定义某个接口的实现类，再根据接口去配置文件中加载类，实例化
 *
 * Java SPI 的使用 @see SPITest
 *2
 * Java SPI机制：
 *
 * 1. ServiceLoader 类的load方法 ：获取【线程上下文类加载器】作为参数，将service接口 一起传入  实例化 ServiceLoader对象
 *
 * 2. 实例化 ServiceLoader对象：service，loader【成员变量赋值】，【实例化一个懒加载iterator】
 *
 * 3. 当调用iterator方法时，通过【LazyIterator】的  hasNextService 和 nextService 遍历配置，加载类并实例化
 *
 * 实用场景：JDBC，Dubbo（Dubbo自己实现SPI机制，因为JavaSPI机制【一次性实例化】所有实现，可能浪费资源和耗时，2不支持Ioc和AOP）
 */
public interface Developer {
    void sayHi();
}