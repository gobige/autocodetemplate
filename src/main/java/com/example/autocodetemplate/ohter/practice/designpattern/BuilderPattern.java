package com.example.autocodetemplate.ohter.practice.designpattern;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 *
 * 作用：如果一个类有很多属性，为了避免构造函数过长，可以通过set配合的方式解决，如果分为必填和可选属性，那么还存在一些校验逻辑，以及属性之间依赖关系，一旦建立则不能改变的场景
 */
public class BuilderPattern {
    public static void main(String[] args) {
        Meal chickenMeal = MealBuilder.buildChickenMeal();
        chickenMeal.showItems();
        Meal vegeMeal = MealBuilder.buildVegeMeal();
        vegeMeal.showItems();
    }
}

class MealBuilder {

    public static Meal buildChickenMeal() {
        Meal meal = new Meal();
        pepsi pepsi = new pepsi();
        chickenBurger chickenBurger = new chickenBurger();
        meal.addItem(pepsi);
        meal.addItem(chickenBurger);

        return meal;
    }

    public static Meal buildVegeMeal() {
        Meal meal = new Meal();
        Coke coke = new Coke();
        VegBurger vegBurger = new VegBurger();
        meal.addItem(coke);
        meal.addItem(vegBurger);

        return meal;
    }
}

class Meal {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public Double getCost() {
        BigDecimal cost = new BigDecimal("0.00");

        for (Item item : items) {
            cost = cost.add(new BigDecimal(item.price()== null?"0.00":item.price().toString()));
        }

        return cost.doubleValue();
    }

    public void showItems () {
        for (Item item : items) {
            System.out.println(item.toString());
        }
    }
}

class Coke extends ColdDrink {
    @Override
    public String name() {
        return "coke";
    }

    @Override
    public Double price() {
        return 3.50;
    }

    @Override
    public String toString() {
        return "name-" + name() +" " + "price-" + price();
    }
}


class pepsi extends ColdDrink {
    @Override
    public String name() {
        return "pepsi";
    }

    @Override
    public Double price() {
        return 3.00;
    }
    @Override
    public String toString() {
        return "name-" + name() +" " + "price-" + price();
    }
}

class chickenBurger extends Burger {
    @Override
    public String name() {
        return "chicken burger";
    }

    @Override
    public Double price() {
        return 11.2;
    }
    @Override
    public String toString() {
        return "name-" + name() +" " + "price-" + price();
    }
}

class VegBurger extends Burger {
    @Override
    public String name() {
        return "vegBurger";
    }

    @Override
    public Double price() {
        return 5.5;
    }
    @Override
    public String toString() {
        return "name-" + name() +" " + "price-" + price();
    }
}

/**
 * 冷饮
 */
abstract class ColdDrink implements Item {
    @Override
    public Packing pack() {
        return new Bottle();
    }
}

/**
 * 汉堡
 */
abstract class Burger implements Item{
    @Override
    public Packing pack() {
        return new Wrapper();
    }
}

/**
 * 组件接口
 */
interface Item{
    String name();
    Double price();
    Packing pack();
}

/**
 * 盒装
 */
class Wrapper implements Packing {
    @Override
    public String pack() {
        return "wrapper";
    }
}

/**
 * 瓶装
 */
class Bottle implements Packing {
    @Override
    public String pack() {
        return "bottle";
    }
}

/**
 * 包装
 */
interface Packing {
    String pack();
}


