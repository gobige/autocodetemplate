package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 装饰者模式
 *
 * 装饰器类附加的是跟原始类相关的【增强】功能 2装饰器类和原始类继承同样父类，这样可对原始类嵌套多个装饰器类
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Person england = new EnglandMan();
        england.speak();
        Person china = new Chinese();
        china.speak();
        Person superman = new FlyPersonDecorator(new Chinese());
        superman.speak();
    }
}

interface Person {
    void speak();
}

class FlyPersonDecorator extends PersonDecorator {
    FlyPersonDecorator(Person decoratePerson) {
        super(decoratePerson);
    }

    @Override
    public void speak() {
        super.speak();
        setWing();
    }

    private void setWing() {
        System.out.println("i can fly");
    }
}

abstract class PersonDecorator implements Person {
    protected Person decoratePerson;

    PersonDecorator(Person decoratePerson) {
        this.decoratePerson = decoratePerson;
    }

    @Override
    public void speak() {
        decoratePerson.speak();
    }
}

class EnglandMan implements Person {

    @Override
    public void speak() {
        System.out.println("i will speak english");
    }
}

class Chinese implements Person{
    @Override
    public void speak() {
        System.out.println("i will speak chinese");
    }
}