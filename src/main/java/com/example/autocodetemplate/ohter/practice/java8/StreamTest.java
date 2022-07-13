package com.example.autocodetemplate.ohter.practice.java8;

import com.example.autocodetemplate.ohter.practice.Apple;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 函数式接口
 * 就是只定义一个抽象方法的接口。你已经知道了Java API中的一些其他函数式接口，如我们在第2章中谈到的Comparator和Runnable。
 * <p>
 * Lambda表达式允许你直接以内联的形式为函数式接口的抽象在哪里以及如何使用方法提供实现，并把整个表达式作为函数式接口的实例
 * 函数式接口的抽象方法的签名基本上就是Lambda表达式的签名。我们将这种抽象方法叫作函数描述符
 * 但是泛型（比如Consumer<T>中的T）只能绑定到
 * 引用类型
 * Predicate  return boolean
 * Consumer   return void
 * Function   return obj
 */

@FunctionalInterface
interface bufferReaderProcessor {
    String process(BufferedReader reader) throws IOException;
}

public class StreamTest {

    /**
     * FunctionalInterface test
     * @param brp
     * @return
     * @throws IOException
     */
    public static String processFile(bufferReaderProcessor brp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("c:暂存/test.txt"))) {
            return brp.process(br);
        }
    }

    /**
     * 遍历和过滤条件
     */
    public static void foreachAndFilter() {
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(7,2,"china","apple1"));
        apples.add(new Apple(3,1,"brazil","apple3"));
        apples.add(new Apple(1,2,"china","apple11"));
        apples.add(new Apple(2,3,"china","apple2"));
        apples.add(new Apple(4,3,"china","apple4"));
        apples.add(new Apple(5,7,"brazil","apple5"));
        apples.add(new Apple(6,2,"american","apple6"));

//        apples.add(null); 使用时要做  非空判断，不然会报错
        apples.stream().forEach(bo -> {
            // 非空判断，不然会报错
            if (Optional.ofNullable(bo).isPresent()) {
                System.out.println(bo.getCountry());
            }
        });


        // 过滤 1 Stream流合可以对空集合进行操作，2但是不能对空对象进行操作，3而且通过collect()方法得到的是一个新的集合
        apples = apples.stream().filter(item -> item != null && item.getWight() > 3).collect(Collectors.toList());

        // 过滤 查询某个属性值符合某条件的元素
        apples = apples.stream().filter(apple -> apple.getWight() > 1).collect(Collectors.toList());

        // 任意符合条件
        Optional<Apple> anywightThanThree = apples.stream().filter(a -> a.getWight() > 3).findAny();

        // 第一个符合条件
        Optional<Apple> firstwightThanThree = apples.stream().filter(a -> a.getWight() > 3).findFirst();

        //循环集合内元素并做处理
        apples.stream().forEach(System.out::println);
    }

    /**
     * 集合转换
     */
    public static void listConvert() {
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(7,2,"china","apple1"));
        apples.add(new Apple(3,1,"brazil","apple3"));
        apples.add(new Apple(1,2,"china","apple11"));
        apples.add(new Apple(2,3,"china","apple2"));
        apples.add(new Apple(4,3,"china","apple4"));
        apples.add(new Apple(5,7,"brazil","apple5"));
        apples.add(new Apple(6,2,"american","apple6"));

        // List获取指定**属性**组成新list
        List<String> names = apples.stream().map(Apple::getName).collect(Collectors.toList());

        // List获取指定**属性**组成新set
        Set<String> nameSets = apples.stream().map(Apple::getCountry).collect(Collectors.toSet());

        // List获取指定**属性或对象**组成新map(tomap操作中key值不能重复，value值不能为null，否则会报错)
        Map<String, Object> map = apples.stream().collect(Collectors.toMap(Apple::getName, Function.identity()));
        if (map.isEmpty()) {
            System.out.println("wwwwwwwwwwwwwwww");
        }
        map.containsKey("asdf");

        //1.针对重复key的  覆盖之前的value  不加(k,v)->v)遇到重复key时会报错
        //        apples.add(new Apple(6,2,"american","apple6"));
        apples.stream().collect(Collectors.toMap(Apple::getSeqNo, Apple::getName,(k,v)->v));

        //2.value为空,直接存放  不调用map.merge。同样适用于1(key重复的情况)
        apples.stream().collect(Collector.of(HashMap::new, (m, per)->m.put(per.getSeqNo(),per.getName()), (k, v)->v, Collector.Characteristics.IDENTITY_FINISH));

        // 重组 修改元素内容
        apples.stream().peek(e -> e.setDesc(e.getCountry() + "-" + e.getName())).collect(Collectors.toList());
        System.out.println(apples);

        // 重组 拆分 flatMap  将元素拍平拍扁 ，将拍扁的元素重新组成Stream
        Stream.of("a-b-c-d","e-f-i-g-h")
                .flatMap(e->Stream.of(e.split("-")))
                .forEach(e->System.out.println(e));
    }

    public static void sort() {
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(1,2,"china","apple1"));
        apples.add(new Apple(4,3,"china","apple4"));
        apples.add(new Apple(5,2,"brazil","apple5"));
        apples.add(new Apple(3,3,"american","apple2"));

        // 根据某个属性值对集合排序 由小到大
        apples = apples.stream().sorted(Comparator.comparing(Apple::getSeqNo)).collect(Collectors.toList());

        //   由大到小
        apples.sort(Comparator.comparing(Apple::getWight).reversed());

        //   先按某属性排序，再按另外某属性再次排序
        apples.sort(Comparator.comparing(Apple::getWight).reversed().thenComparing(Apple::getCountry));
    }

    public static void groupby() {
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(7,2,"china","apple1"));
        apples.add(new Apple(3,1,"brazil","apple3"));
        apples.add(new Apple(1,2,"china","apple11"));
        apples.add(new Apple(2,3,"china","apple2"));
        apples.add(new Apple(4,3,"china","apple4"));
        apples.add(new Apple(5,7,"brazil","apple5"));
        apples.add(new Apple(6,2,"american","apple6"));


        // 根据国家 获取 国家 wight总量
        Map<String, Integer> totalWerghtGroupByProvince = apples.stream().collect(Collectors.groupingBy(Apple::getCountry, Collectors.summingInt(Apple::getWight)));

        // 根据country分组 求最大weight的apple  Collectors.reducing将集合缩减为一个数
        Apple identity = new Apple(1, 1, "xx", "xxx");
        Map<String, Apple> collect =  apples.stream().collect(Collectors.groupingBy(Apple::getCountry, Collectors.reducing(identity, BinaryOperator.maxBy(Comparator.comparing(Apple::getWight)))));

        // 根据weight范围  分组list
        Map<String, List<Apple>> collect2 = apples.stream().collect(Collectors.groupingBy(o -> {
            if (o.getWight() <= 3) {
                return "0~3";
            } else if (o.getWight() <= 6) {
                return "3~6";
            } else {
                return "7~";
            }
        }));
    }

    public static void calculate() {
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(1,2,"china","apple1"));
        apples.add(new Apple(4,3,"china","apple4"));
        apples.add(new Apple(5,2,"brazil","apple5"));
        apples.add(new Apple(3,3,"american","apple2"));


        List<Apple> apples2 = new ArrayList<Apple>();
        apples2.add(new Apple(1,2,"china","apple1"));
        apples2.add(new Apple(4,3,"china","apple4"));

        // List中指定**属性**求和
        Integer sum = apples.stream().collect(Collectors.summingInt(Apple::getWight));

        //  求 交集
        List<Apple> Intersections = apples.stream().filter(item -> apples2.contains(item)).collect(Collectors.toList());

        // 求 差集
        List<Apple> Differs = apples.stream().filter(item -> !apples2.contains(item)).collect(Collectors.toList());

        // 求去重并集
        apples.addAll(apples2);
        apples.stream().distinct().collect(Collectors.toList());

        // 获取第一个元素(集合元素为0会报错)
        apples.stream().findFirst().get();

        // List中指定**属性的值**最小元素,得到的对象时列表中对象，非新建对象
        Apple minAppple = apples.stream().min(Comparator.comparing(Apple::getWight)).get();

        // 计算 先后顺序
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * 2;
        System.out.println(f.andThen(f2).apply(1)); //(T t) -> after.apply(apply(t));
        System.out.println(f.compose(f2).apply(1));  //(V v) -> apply(before.apply(v));
    }

    public static void main(String[] args) {
        listConvert();

    }

}
