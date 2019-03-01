package com.example.autocodetemplate.ohter.practice.designpattern;

import java.util.Hashtable;

/**
 * 原型模式
 */
public class PrototypePattern {
    public static void main(String[] args) {
        BallCache.loadCache();
        try {
            Ball testCloneBall = BallCache.getBall("footBall");
            System.out.println(testCloneBall);
        }catch (Exception e){
            e.getMessage();
        }
    }
}

class BallCache {
    private static Hashtable<String,Ball> hashtable = new Hashtable();

    public static Ball getBall(String ballName) throws Exception{
        Ball cloneBall = (Ball) hashtable.get(ballName).clone();

        System.out.println(cloneBall == hashtable.get(ballName));
        return cloneBall;
    }

    public static void loadCache() {
        hashtable.put("footBall",new FootBall());
        hashtable.put("basketBall",new FootBall());
    }

}

class FootBall extends Ball {
    FootBall() {
        this.wight = 1.1;
        this.color = "white";
    }
}

class BasketBall extends Ball {
    BasketBall() {
        this.wight = 2.1;
        this.color = "red";
    }
}

abstract class Ball implements Cloneable {
    protected String name;
    protected Double wight;
    protected String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWight() {
        return wight;
    }

    public void setWight(Double wight) {
        this.wight = wight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}