package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 迭代器模式
 *
 * 封装集合内部复杂数据结构，不需要了解如何遍历，直接使用迭代器即可，2将集合遍历操作拆分，职责更单一 3使用新遍历算法跟方便，符合开闭原则
 *
 * 获取迭代快照，通过浅clone，2通过元素标记，逻辑删除
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
