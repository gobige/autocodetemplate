package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 门面模式
 *
 * 对接口做整合，解决多借口调用问题
 *
 * 1）为一个复杂子系统提供一个简单接口。
 * 2）提高子系统的独立性。
 * 3）在层次化结构中，可以使用Facade模式定义系统中每一层的入口
 *
 *
 */
public class Facade {
    registerClass rc = new registerClass();
    MathClass mc = new MathClass();
    notifyClass nc = new notifyClass();

    public void registerWeibo() {
        rc.register();
        mc.matchFriend();
        nc.notifyUser();
    }
}

class registerClass {
    public String register() {
        return "注册账号";
    }
}

class MathClass {
    public String matchFriend() {
        return "匹配朋友";
    }
}

class notifyClass {
    public String notifyUser() {
        return "短信通知";
    }
}