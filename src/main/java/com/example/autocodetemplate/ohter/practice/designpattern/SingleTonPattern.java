package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 单例模式
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