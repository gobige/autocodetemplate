package com.example.autocodetemplate.ohter.practice.network;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class clientTest {

    public static void main(String[] args) throws Exception{
        NIOClient nioClient = new NIOClient("192.168.1.120", 9527);
        nioClient.run();
//
//        Thread.sleep(4000);
//        nioClient.sendMes("hello world");
//        Thread.sleep(4000);
//        nioClient.sendMes("hello world second");

    }

}
