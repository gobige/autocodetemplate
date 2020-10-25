package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 桥接（Bridge）是用于把【抽象化】与【实现化解耦】，使得二者可以独立变化。
 * 这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
 * 这种模式涉及到一个作为桥接的接口，使得实体类的功能独立于接口实现类。这两种类型的类可被结构化改变而互不影响。
 *
 * 一个类存在两个（多个）独立变化的维度，通过组合方式，让多个维度可独立扩展
 */
public class BrigdePattern {
    public static void main(String[] args) {
        xiaoMian xm = new xiaoMian(new hotStyle(), new redColor());
        System.out.println(xm.eat());
        laMian lm = new laMian(new lightStyle(), new greenColor());
        System.out.println(lm.eat());
    }

}

interface style {
    String getName();
}

class hotStyle implements style {
    public String getName() {
        return "hot style";
    }
}

class lightStyle implements style {
    public String getName() {
        return "light style";
    }
}

interface color {
    String getColor();
}

class greenColor implements color {
    public String getColor() {
        return "green";
    }
}

class redColor implements color {
    public String getColor() {
        return "red";
    }
}

abstract class nodle {
    private style style;
    private color color;

    nodle(style style, color color) {
        this.color = color;
        this.style = style;
    }

    public String getStyle() {
        return style.getName();
    }

    public void setStyle(style style) {
        this.style = style;
    }

    public String getColor() {
        return color.getColor();
    }

    abstract String getName();

    abstract String eat();
}

class xiaoMian extends nodle {

    xiaoMian(style style, color color) {
        super(style, color);
    }

    public String getName() {
        return "xiaomian";
    }

    public String eat() {
        return "eat color：" + this.getColor() + "style:" + this.getStyle() + getName();
    }
}


class laMian extends nodle {
    laMian(style style, color color) {
        super(style, color);
    }

    public String getName() {
        return "laMian";
    }

    public String eat() {
        return "eat color：" + this.getColor() + "style:" + this.getStyle() + getName();
    }
}