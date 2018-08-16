package com.example.autocodetemplate.ohter.practice.designpattern;

import com.example.autocodetemplate.domain.Apple;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式
 */
public class FilterPattern {

}

/**
 * 标准接口
 */
interface Criteria {
    List<Apple> MaterialCriteria(List<Apple> apples);
}

/**
 * 能做饮料的苹果标准
 */
class AppleDrink implements Criteria {
    @Override
    public List<Apple> MaterialCriteria(List<Apple> apples) {
        List<Apple> appleDrinks = new ArrayList<>();
        if (!CollectionUtils.isEmpty(apples)) {
            for (Apple apple : apples) {
                if ("green".equals(apple.getColor()) && 2 > apple.getWight()) {
                    appleDrinks.add(apple);
                }
            }
        }

        return appleDrinks;
    }
}

/**
 * 能做披萨的苹果标准
 */
class ApplePizza implements Criteria {
    @Override
    public List<Apple> MaterialCriteria(List<Apple> apples) {
        List<Apple> applePizzas = new ArrayList<>();
        if (!CollectionUtils.isEmpty(apples)) {
            for (Apple apple : apples) {
                if ("Brazil".equals(apple.getCountry())) {
                    applePizzas.add(apple);
                }
            }
        }

        return applePizzas;
    }
}

/**
 * 符合两个标准的苹果
 */
class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria oherCriteria;

    AndCriteria(Criteria criteria,Criteria oherCriteria) {
        this.criteria = criteria;
        this.oherCriteria = oherCriteria;
    }

    @Override
    public List<Apple> MaterialCriteria(List<Apple> apples) {
        List<Apple> criteriaApples = criteria.MaterialCriteria(apples);
        List<Apple> andApples = oherCriteria.MaterialCriteria(criteriaApples);

        return andApples;
    }
}

/**
 * 两个标准都不符合的苹果
 */
class OrCriteria implements Criteria {
    private Criteria criteria;
    private Criteria oherCriteria;

    OrCriteria(Criteria criteria,Criteria oherCriteria) {
        this.criteria = criteria;
        this.oherCriteria = oherCriteria;
    }

    @Override
    public List<Apple> MaterialCriteria(List<Apple> apples) {
        List<Apple> criteriaApples = criteria.MaterialCriteria(apples);
        List<Apple> otherCriterApples = oherCriteria.MaterialCriteria(apples);
        List<Apple> orApples = new ArrayList<>();

        for (Apple apple : criteriaApples) {
            if (!otherCriterApples.contains(apple)) {
                orApples.add(apple);
            }
        }
        return orApples;
    }
}