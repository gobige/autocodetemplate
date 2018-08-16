package com.example.autocodetemplate.ohter.practice.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 */
public class ObserverPattern {

    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer1 observer1 = new Observer1(subject);
        Observer2 observer2 = new Observer2(subject);
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.setState(10);
        subject.setState(100);
    }
}

class Observer1 extends Observer {
    Observer1(Subject subject) {
        this.subject = subject;
    }

    @Override
    void update() {
        System.out.println("观察者一号观察的对象状态改变为：" + subject.getState());
    }
}

class Observer2 extends Observer {
    Observer2(Subject subject) {
        this.subject = subject;
    }

    @Override
    void update() {
        System.out.println("观察者二号观察的对象状态改变为：" + subject.getState());
    }
}

class Subject {
    private List<Observer> observers = new ArrayList<>();

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
        notifyAllObserver();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}

/**
 * 被观察者
 */
abstract class Observer {
    protected Subject subject;
    void update(){};
}