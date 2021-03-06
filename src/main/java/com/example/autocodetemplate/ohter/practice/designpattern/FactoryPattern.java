package com.example.autocodetemplate.ohter.practice.designpattern;

import java.util.Date;

/**
 * 工厂模式
 * 用处： 对象创建逻辑比较复杂，将对象创建和使用分开  例：1规则配置解析，多IF-ELSE判断  2根据不同类型创建对象，初始化
 * 简单工厂：对象创建逻辑简单
 * 工厂方法模式：创建逻辑拆分更细，各自创建逻辑放到各自工厂类
 * 实现：懒汉，饿汉，双重检测，静态内部类，枚举
 * 问题：对OOP特性支持不好 2 隐藏类之间的关系 3对代码扩展性不好 4对代码可测试性不好 5不支持有参构造函数
 */
public class FactoryPattern {


    public Order getOrder(Integer orderId, OrderTypeEnum orderTypeEnum) {
        if (OrderTypeEnum.GOODSORDERTYPE == orderTypeEnum) {
            // 返回商品订单
            return new GoodsOrder();
        } else if(OrderTypeEnum.SERVICEORDERTYPE == orderTypeEnum) {
            // 返回服务订单
            return new ServicesOrder();
        } else if (OrderTypeEnum.AFTERSALEORDERTYPE == orderTypeEnum) {
            // 返回售后订单
            return new AfsOrder();
        } else {
            return null;
        }
    }
}


/**
 * 售后订单的model
 */
class AfsOrder extends Order {
    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 客服姓名
     */
    private String servicerName;
}

/**
 * 服务订单的model
 */
class ServicesOrder extends Order {
    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 服务内容
     */
    private String serviceContent;
}

/**
 * 商品订单model
 */
class GoodsOrder extends Order {
    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 商品名称
     */
    private String goodsName;
}

class Order {
    /**
     * 订单ID
     */
    private Integer orderId;
    /**
     * 订单金额
     */
    private Double orderFee;
    /**
     *
     */
    private Date createTime;
}

enum OrderTypeEnum {
    GOODSORDERTYPE(1,"商品订单"),
    SERVICEORDERTYPE(2,"服务订单"),
    AFTERSALEORDERTYPE(3,"售后订单");

    OrderTypeEnum(Integer key,String value) {
        this.key = key;
        this.value = value;
    }
    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
