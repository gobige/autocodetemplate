package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 策略模式
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
