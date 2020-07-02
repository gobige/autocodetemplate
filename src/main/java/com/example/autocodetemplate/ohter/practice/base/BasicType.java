package com.example.autocodetemplate.ohter.practice.base;

public class BasicType {
    public static void main(String[] args) {
        float a = 0.125f;
        double b = 0.125d;
        System.out.println((a - b) == 0.0);

        double c = 0.8;
        double d = 0.7;
        double e = 0.6;
        System.out.println((c - d) == (d - e));

        System.out.println(0.0 / 0.0);

        String f = null;
        switch (f) {
//            case  null:break;
            case "null":break;

        }

    }


}

class stff {
    void f(Object s) {
        System.out.println("object");
    }
}

class son extends stff {

    void f(Integer s) {
        System.out.println("Integer");
    }

    void f(Double s) {
        System.out.println("Double");
    }

    void f(String s) {
        System.out.println("String");
    }
}

class tests {
    public static void main(String[] args) {
        son son = new son();
        son.f(1);
    }
}