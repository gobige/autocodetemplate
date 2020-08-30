package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 模板模式
 *
 * 在一个方法中定义一个算法（业务逻辑）骨架，将某些步骤推迟到子类中实现。起到复用和扩展的目的
 */
public class TemplatePattern {
    public static void main(String[] args) {
        cooking tofuOfMapo = new TofuOfMapo();
        tofuOfMapo.cookingFood();

        cooking hotChicken = new HotChicken();
        hotChicken.cookingFood();
    }
}

class TofuOfMapo extends cooking{
    @Override
    void prePareCokie() {
        System.out.println("切豆腐");
        System.out.println("宽油");
    }

    @Override
    void cooking() {
        System.out.println("烧酱汁");
        System.out.println("放豆腐");
    }

    @Override
    void cookied() {
        System.out.println("起锅，装盘");
    }
}

class HotChicken extends cooking{
    @Override
    void prePareCokie() {
        System.out.println("杀鸡，放血，拔毛");
        System.out.println("刀工");
    }

    @Override
    void cooking() {
        System.out.println("宽油");
        System.out.println("烧制");
    }

    @Override
    void cookied() {
        System.out.println("起锅，装盘");
    }
}

abstract class cooking {
    abstract void prePareCokie();

    abstract void cooking();

    abstract  void cookied();

    public final void cookingFood() {
        prePareCokie();
        cooking();
        cookied();
    }
}
