package com.example.autocodetemplate.ohter.practice.base;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class OverLoadAndRewriteTest {

}

// Run with: java -XX:CompileCommand='dontinline,*. 出境' 乘客
abstract class 乘客 {
    abstract void 出境();

    public static void main(String[] args) {
        乘客 a = new 中国人();
        乘客 b = new 外国人();
        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            乘客 c = (i < 1_000_000_000) ? a : b;
            c.出境();
        }
    }
}
class 中国人 extends 乘客 { @Override void 出境 () {} }
class 外国人 extends 乘客 { @Override void 出境 () {} }
