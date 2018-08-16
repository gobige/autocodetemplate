package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 装饰者模式
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