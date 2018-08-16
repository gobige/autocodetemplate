package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 迭代器模式
 */
public class IteratorPattern {
    public static void main(String[] args) {
        MyContainer container = new MyContainer();
        container.add("str1");
        container.add("str7");
        container.add("str5");
        container.add("str2");
        container.add("str3");

        MyIterator iterator = container.getIterator();

        while (iterator.hashNext()) {
            System.out.println(iterator.next());
        }
    }
}

class MyContainer implements Container {
    private String[] strs = new String[10];
    private int size = 0;

    @Override
    public void add(Object object) {
        strs[size++] = (String) object;
    }

    @Override
    public MyIterator getIterator() {
        return new Iterator();
    }

    private class Iterator implements MyIterator {
        int index = 0;
        @Override
        public boolean hashNext() {
            if (index < strs.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (hashNext()) {
                return strs[index++];
            }

            return null;
        }
    }
}

interface Container {
    MyIterator getIterator();
    void add(Object obj);
}

interface MyIterator {
    boolean hashNext();
    Object next();
}
