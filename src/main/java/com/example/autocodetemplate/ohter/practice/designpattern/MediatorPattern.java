package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 中介者模式(Mediator)的定义
 * 用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
 * 中介者模式(Mediator)的适用性
 * 1.一组对象以定义良好但是复杂的方式进行通信，产生的相互依赖关系结构混乱且难以理解。
 * 2.一个对象引用其他很多对象并且直接与这些对象通信,导致难以复用该对象。
 * 3.想定制一个分布在多个类中的行为，但又不想生成太多的子类。
 */
public class MediatorPattern {

    public static void main(String[] args) {
        concreteMediator cm = new concreteMediator();
        ColleagueA a = new ColleagueA("tom", cm);
        ColleagueB b = new ColleagueB("jerry", cm);
        cm.setA(a);
        cm.setB(b);
        a.contact("i have something to b");
        b.contact("i busy!! don't intterupt me");
    }
}

abstract class Mediator {
    public abstract void contact(String content, Colleague coll);
}

class concreteMediator extends Mediator {
    ColleagueA a;
    ColleagueB b;

    public ColleagueA getA() {
        return a;
    }

    public void setA(ColleagueA a) {
        this.a = a;
    }

    public ColleagueB getB() {
        return b;
    }

    public void setB(ColleagueB b) {
        this.b = b;
    }

    @Override
    public void contact(String content, Colleague coll) {
        if (coll == a) {
            b.getMes(content);
        } else {
            a.getMes(content);
        }
    }
}


class ColleagueA extends Colleague {
    public ColleagueA(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void getMes(String mes) {
        System.out.println("collA " + name + "get mes " + mes);
    }

    public void contact(String mes) {
        mediator.contact(mes, this);
    }
}

class ColleagueB extends Colleague {
    public ColleagueB(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void getMes(String mes) {
        System.out.println("collB " + name + "get mes " + mes);
    }

    public void contact(String mes) {
        mediator.contact(mes, this);
    }
}

class Colleague {
    protected String name;
    protected Mediator mediator;

    public Colleague(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}