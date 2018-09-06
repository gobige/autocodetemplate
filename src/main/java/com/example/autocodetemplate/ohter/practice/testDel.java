package com.example.autocodetemplate.ohter.practice;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class testDel {
    public static void main(String[] args) {
//      List<Integer> s = new ArrayList<>();
//        s.add(1);
//        s.add(2);
//        s.add(3);
//        s.add(4);
//        s.add(5);
//        s.add(6);
//        Optional sum = s.parallelStream().reduce((a,b) -> a + b);
//
//        if (sum.isPresent()) {
//            System.out.println("list sum is " + sum.get());
//        }
//
//        Integer sum2 = s.parallelStream().reduce(0, (a,b) -> a+b);
//        System.out.println("list sum is " + sum2);
//
//        Optional sum3 = s.parallelStream().unordered().reduce((a, b) -> a * b);
//        System.out.println("list sum is " + sum3.get());
//
//         Integer sum4 = s.parallelStream().reduce(1, (a,b) -> a*b);
//        System.out.println("list sum is " + sum4);
//
//    testClass1 testClass1 = new testClass1();
////    testClass1.setName("namesss");
////    testClass1.setAge("111");
////    testClass1.setIsTure("true");
//        property property = new property();
//        property.setNum(1234213);
//        testClass1.setProperty(property);
//    testClass2 testClass2 = new testClass2();
//
//        BeanUtils.copyProperties(testClass1,testClass2);
//        System.out.println(testClass2.toString());

        try {
            throw new MyException("hahah exception");
        } catch (MyException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}

class MyException extends Exception {
    MyException(String str) {
        System.out.println("excption" + str);
    }
}

class testClass1 {
    private String name = "namesss";
    private String age= "111";
    private String isTure = "true";
    private property property;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIsTure() {
        return isTure;
    }

    public void setIsTure(String isTure) {
        this.isTure = isTure;
    }

    public com.example.autocodetemplate.ohter.practice.property getProperty() {
        return property;
    }

    public void setProperty(com.example.autocodetemplate.ohter.practice.property property) {
        this.property = property;
    }
}

class testClass2 {
    private String name;
    private Integer age;
    private Boolean isTure;
    private property property;

    public com.example.autocodetemplate.ohter.practice.property getProperty() {
        return property;
    }

    public void setProperty(com.example.autocodetemplate.ohter.practice.property property) {
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getTure() {
        return isTure;
    }

    public void setTure(Boolean ture) {
        isTure = ture;
    }
}

class property {
private     Integer num = 123;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}