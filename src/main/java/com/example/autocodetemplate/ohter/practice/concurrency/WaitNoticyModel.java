package com.example.autocodetemplate.ohter.practice.concurrency;

import java.util.concurrent.TimeUnit;

public class WaitNoticyModel {
    static Object lock = new Object();
    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new wait(), "thread wait thread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);

        Thread notifyThread = new Thread(new notify(), "thread notify thread");
        notifyThread.start();
    }

}

class wait implements Runnable {

    @Override
    public void run() {
        synchronized (WaitNoticyModel.lock) {
            while (WaitNoticyModel.flag) {
                try {
                    System.out.println(Thread.currentThread() + "flag is true ,waiting!");
                    WaitNoticyModel.lock.wait();
                    System.out.println(Thread.currentThread() + "waiting complete ,go on!");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread() + "continue working!");
        }
    }

}

class notify implements Runnable {

    @Override
    public void run() {
        synchronized (WaitNoticyModel.lock) {
            try {
                TimeUnit.SECONDS.sleep(1);

                System.out.println(Thread.currentThread() + "hold lock,thread is notify!");
                TimeUnit.SECONDS.sleep(1);
                WaitNoticyModel.lock.notifyAll();
                TimeUnit.SECONDS.sleep(1);
                WaitNoticyModel.flag = false;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        synchronized (WaitNoticyModel.lock) {
            System.out.println(Thread.currentThread() + "hold lock again");
        }
    }

}