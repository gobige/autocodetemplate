package com.example.autocodetemplate.ohter.practice.base;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class EnumTest {
    public static void main(String[] args) {
        /**
         * enumset 使用
         */
        EnumSet<alertPoints> points = EnumSet.noneOf(alertPoints.class);
        points.add(alertPoints.START1);
        System.out.println(points);
        points.addAll(EnumSet.of(alertPoints.START1, alertPoints.START2));
        System.out.println(points);
        points = EnumSet.allOf(alertPoints.class);
        points.removeAll(EnumSet.of(alertPoints.START1,alertPoints.START2));
        System.out.println(points);
        points.removeAll(EnumSet.range(alertPoints.START1, alertPoints.OFFICE1));
        System.out.println(points);
        points = EnumSet.complementOf(points);
        System.out.println(points);
        /**
         * enumMap 使用
         */
        EnumMap<alertPoints, Command> enumMaps = new EnumMap<alertPoints, Command>(alertPoints.class);
        enumMaps.put(alertPoints.START1, new Command() {
            @Override
            public void action() {
                System.out.println("this is start,");
            }
        });
        enumMaps.put(alertPoints.OFFICE1, new Command() {
            @Override
            public void action() {
                System.out.println("this is OFFICE1,");
            }
        });
        enumMaps.put(alertPoints.END, new Command() {
            @Override
            public void action() {
                System.out.println("this is END,");
            }
        });
        for (Map.Entry<alertPoints,Command> entry : enumMaps.entrySet()) {
            entry.getValue().action();
        }

    }
}

/**
 * 嵌套enum
 */
enum worldFood {
    chineseFood(Food.ChineseFood.class),
    americanFood(Food.AmericanFood.class),
    franceFood(Food.FranceFood.class);

    private Food[] values;
    private worldFood(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public interface Food {
        enum ChineseFood implements Food {
            MAPODOUFU,SHUIZHUROUPIAN,HUIGUOROU;
        }
        enum AmericanFood implements Food {
            SALAD,MIANBAO,TOMATO;
        }
        enum FranceFood implements Food {
            CHANGGUN,SONGLU,LAFEI;
        }
    }
//
//    public Food randomSelect() {
//        return Enums.random(values);
//    }
}

enum alertPoints {
    START1,START2,LOBBY,OFFICE1,OFFICE2,END;
}


interface Command {
    void action();
}

/**
 * 为enum编写实例方法
 */
enum person {
    POLICE{
        String getInfo() {

            return "im is police";
        }},
    COOKIER{
        String getInfo() {

            return "im is COOKIER";
        }},
    DOCTOR{
        String getInfo() {

            return "im is DOCTOR";
        }},
}