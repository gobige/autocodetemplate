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

        new Thread(new Runnable() {
            @Override
            public void run() {
                NIOClient nioClient = new NIOClient("192.168.1.120", 9527);
                nioClient.run();
                try {
                    Thread.sleep(1000);
                    nioClient.sendMes("hello world1111111");
                } catch (Exception e) {

                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                NIOClient nioClient = new NIOClient("192.168.1.120", 9527);
                nioClient.run();
                try {
                    Thread.sleep(1000);
                    nioClient.sendMes("hello world");
                } catch (Exception e) {

                }
            }
        }).start();

    }

}
