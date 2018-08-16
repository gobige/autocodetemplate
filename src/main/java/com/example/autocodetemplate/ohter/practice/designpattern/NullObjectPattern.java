package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 空对象模式
 */
public class NullObjectPattern {

    public static void main(String[] args) {
        NullObjectPattern nullObjectPattern = new NullObjectPattern();

        Factory factory = nullObjectPattern.new Factory();
        System.out.println(factory.getSubject("haha").getName());
        System.out.println(factory.getSubject("PE").getName());
    }

     class Factory {
        public final String[] names = {"PE","MATH","LITERATURE","POLITICAL"};

        public Subject getSubject(String subject) {
            for (String name : names) {
                if (name.equals(subject)) {
                    return new SecondarySubject(subject);
                }
            }

            return new NullSubject();
        }
    }


    class NullSubject extends Subject {
        @Override
        boolean isNull() {
            return true;
        }

        @Override
        String getName() {
            return "not avilable subject";
        }
    }

    class SecondarySubject extends Subject {
        SecondarySubject( String name) {
            this.name = name;
        }

        @Override
        boolean isNull() {
            return false;
        }

        @Override
        String getName() {
            return name;
        }
    }

    abstract class Subject {
        String name;
        abstract boolean isNull();
        abstract String getName();
    }
}

