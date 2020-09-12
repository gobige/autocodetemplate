package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 策略模式
 *
 * 通过定义 一组算法类，将每个算法封装起来，让他们可以互相替换。
 *
 * 一个策略接口和一组实现这个接口策略类 2策略创建由工厂类完成，封装策略创建细节 3 通过运行时动态确定策略选择
 */
public class StrategyPattern {

    public static void main(String[] args) {
        Army army1 = new Army(new OffensiveStrategy());
        Army army2 = new Army(new RetreatStrategy());

        army1.excuteStrategy();
        army2.excuteStrategy();
    }
}

class Army {
    private Strategy strategy;

    Army(Strategy strategy) {
        this.strategy = strategy;
    }

    void excuteStrategy() {
        strategy.doOperation();
    }
}

/**
 * 进攻策略
 */
class OffensiveStrategy implements Strategy {
    @Override
    public void doOperation() {
        System.out.println("执行进攻策略");
    }
}

/**
 * 撤退策略
 */
class RetreatStrategy implements Strategy {
    @Override
    public void doOperation() {
        System.out.println("执行撤退策略");
    }
}

interface Strategy {
    void doOperation();
}
