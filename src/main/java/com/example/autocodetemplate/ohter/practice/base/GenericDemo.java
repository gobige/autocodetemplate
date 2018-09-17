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

    public boolean f(Object obj) {
        return kind.isInstance(obj);
    }

    public static void main(String[] args) {
        GenericCompenate<building> b = new GenericCompenate<building>(building.class);
        System.out.println(b.f(new building()));
        System.out.println(b.f(new house()));
        GenericCompenate<house> h = new GenericCompenate<house>(house.class);
        System.out.println(h.f(new building()));
        System.out.println(h.f(new house()));

        List<? super test2> list = new ArrayList<>();
        list.add(new test3());


    }
}

class test {
}

class test2 extends test {

}
class test3 extends test2 {

}