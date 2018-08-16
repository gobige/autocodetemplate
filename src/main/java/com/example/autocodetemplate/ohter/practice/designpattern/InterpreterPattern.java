package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 解释器模式
 */
public class InterpreterPattern {
    public static void main(String[] args) {
        System.out.println("hello,this is bbc news is news?:" + InterpreterPattern.setNewsWord().interpret("hello,this is bbc news"));
        System.out.println("1 hour 30 mills is time?:" + InterpreterPattern.setTimeWord().interpret("1 hour 30 mills"));

    }

    public static Explain setNewsWord() {
        Explain contextExplain = new ContextExplain("news");
        Explain contextExplain2 = new ContextExplain("report");

        return new OrExplain(contextExplain,contextExplain2);
    }

    public static Explain setTimeWord() {
        Explain contextExplain = new ContextExplain("minute");
        Explain contextExplain2 = new ContextExplain("hour");

        return new AndExplain(contextExplain,contextExplain2);
    }
}

class AndExplain implements Explain {
    private Explain orExplain1;
    private Explain orExplain2;

    AndExplain(Explain orExplain1, Explain orExplain2) {
        this.orExplain1 = orExplain1;
        this.orExplain2 = orExplain2;
    }


    @Override
    public boolean interpret(String context) {
        return orExplain1.interpret(context) && orExplain2.interpret(context);
    }
}

class OrExplain implements Explain {
    private Explain orExplain1;
    private Explain orExplain2;

    OrExplain(Explain orExplain1, Explain orExplain2) {
        this.orExplain1 = orExplain1;
        this.orExplain2 = orExplain2;
    }


    @Override
    public boolean interpret(String context) {
        return orExplain1.interpret(context) || orExplain2.interpret(context);
    }
}

class ContextExplain implements Explain {
    private  String data;

    ContextExplain(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if (context.contains(data)) {
            return true;
        }

        return false;
    }
}

interface Explain {
    boolean interpret(String context);
}