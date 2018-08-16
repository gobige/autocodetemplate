package com.example.autocodetemplate.ohter.practice.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令模式
 */
public class CommandPattern {
    public static void main(String[] args) {
        Stock stock = new Stock("footBall",100);
        AddStock addStock = new AddStock(stock,23);
        ReduceStock reduceStock = new ReduceStock(stock,121);
        Cmd.scanf(addStock);
        Cmd.scanf(reduceStock);
        Cmd.excute();
    }

}

class Cmd {
    private static List<Commond> commonds = new ArrayList<>();

    public static void scanf(Commond commond) {
        commonds.add(commond);
    }

    public static void excute() {
        commonds.stream().forEach(commond -> commond.excute());
    }
}

class Stock {
    private String GoodName;
    private Integer num;

    Stock(String goodName, Integer num) {
        this.GoodName = goodName;
        this.num = num;
    }

    public void add(Integer num) {
        System.out.println(GoodName + " add stock[" + num + "]");
    }

    public void reduce(Integer num) {
        System.out.println(GoodName + " reduce stock[" + num + "]");
    }
}

class AddStock implements Commond {

    private Stock stock;
    private Integer num;

    AddStock(Stock stock,Integer num) {
        this.stock = stock;
        this.num = num;
    }

    @Override
    public void excute() {
        stock.add(num);
    }
}

class ReduceStock implements Commond {

    private Stock stock;
    private Integer num;

    ReduceStock(Stock stock,Integer num) {
        this.stock = stock;
        this.num = num;
    }

    @Override
    public void excute() {
        stock.reduce(num);
    }
}

interface Commond {
    void excute();
}