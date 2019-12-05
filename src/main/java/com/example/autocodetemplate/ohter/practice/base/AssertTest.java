package com.example.autocodetemplate.ohter.practice.base;

import org.springframework.util.Assert;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class AssertTest {
    public static void main(String[] args) {
        String url = "https://www.baidu.com";
        Assert.hasLength(url, "URL must not empty'");

        // 断言判断是否符合规则，不符合则抛出  IllegalArgumentException  异常
        Assert.isTrue(url.startsWith("http2"), "URL must start with 'jdbc'");

        System.out.println("go on ");
    }
}
