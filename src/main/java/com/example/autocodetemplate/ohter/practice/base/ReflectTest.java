package com.example.autocodetemplate.ohter.practice.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * 反射：
 * 作用：为了设计更为通用和灵活架构，为了保证通用性，可以配置加载不用的类，这时要用到反射。 2动态代理,AOP拦截 3注解，通过反射获取注解并执行对应行为
 * 优点：运行时动态获取类和对象中内容，极大提高系统灵活性和扩展性
 * 缺点：会有一定性能损耗，JVM无法对这些代码进行优化；破坏类的封装性
 */
public class ReflectTest {

    @Value("sms.resource.config")
    private static String smsResource;

    public boolean sendSms() throws Exception {
        Sms smsTool = (Sms) Class.forName(smsResource).newInstance();
        return smsTool.sendMessage("im yates!");
    }

}

interface Sms {
    boolean sendMessage(String message);
}

@Slf4j
class AliyunSms implements Sms {
    @Override
    public boolean sendMessage(String message) {
        log.info("use aliyunSms send message:{}!", message);
        return false;
    }
}

@Slf4j
class MobSms implements Sms {
    @Override
    public boolean sendMessage(String message) {
        log.info("use mobSms send message:{}!", message);
        return false;
    }
}