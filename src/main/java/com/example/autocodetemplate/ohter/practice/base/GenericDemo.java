package com.example.autocodetemplate.ohter.practice.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型 demo
 */
public class GenericDemo {
    public static void main(String[] args) {
        List<Object> objs = new ArrayList<>();
        List<String> strs = new ArrayList<>();
        GenericDemo.convert(objs);
        GenericDemo.convert(strs);

    }

    public static void convert(List<?> str) {
        str.stream().forEach(s -> System.out.println(s));
    }
}

/**
 * 泛型接口
 * @param <T>
 */
interface generator<T> {
    T next();
}

/**
 * 斐波那契数
 */
class Fibonacci implements generator<Integer>{
    private int count = 0;
    @Override
    public Integer next() {
        return fib(count++);
    }

    Integer fib(int num) {
        if (num < 2) {
            return 1;
        } else {
            return fib(num - 1) + fib(num - 2);
        }
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 19; i++) {
            System.out.print(fibonacci.next() + ",");
        }
    }
}

/**
 * 泛型类
 *
 * @param <T>
 */
class genericObj<T> {
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}

/**
 * 泛型方法
 */
class genericMethod {

    public <T> void show(T t) {
        System.out.println(t);
    }

    /**
     * 可变参数
     * @param args
     * @param <T>
     * @return
     */
    public static <T> List<T> makeList(T... args) {
        List<T> results = new ArrayList<>();
        for (T t: args) {
            results.add(t);
        }

        return results;
    }
}

/**
 * 泛型子类继承
 */
interface inter<T> {
    public abstract void show(T t);
}


/**
 * 子类明确泛型类型
 */
class sureTypeClass implements inter<String> {
    @Override
    public void show(String s) {
        System.out.println(s);
    }
}

/**
 * 子类不明确泛型类型
 */
class notSureTypeClass<T> implements inter<T> {
    @Override
    public void show(T t) {
        System.out.println(t);
    }
}

class building{}
class house extends building{}

/**
 * 擦除补偿 补偿运行时由于类型擦除所丧失的某些操作能力
 * @param <T>
 */
class GenericCompenate<T> {
    Class<T> kind;

    GenericCompenate(Class<T> kind) {
        this.kind = kind;
    }

    public boolean instance(Object obj) {
        return kind.isInstance(obj);
    }

    public static void main(String[] args) {
        GenericCompenate<building> b = new GenericCompenate<building>(building.class);
        System.out.println(b.instance(new building()));
        System.out.println(b.instance(new house()));
        GenericCompenate<house> h = new GenericCompenate<house>(house.class);
        System.out.println(h.instance(new building()));
        System.out.println(h.instance(new house()));

        List<? super test2> list = new ArrayList<>();
        list.add(new test3());


    }

    public static <T extends Comparable<? super T>> void printGen(List<T> list, T val) {
        for (T t : list) {
            if (t.compareTo(val) > 0) {
                System.out.println();
            }
        }
    }
}

class test {
}

class test2 extends test {

}
class test3 extends test2 {

}