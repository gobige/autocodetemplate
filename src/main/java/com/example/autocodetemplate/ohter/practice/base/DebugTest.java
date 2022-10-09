package com.example.autocodetemplate.ohter.practice.base;

import java.math.BigDecimal;

public class DebugTest {
    public static void main(String[] args) {

        BigDecimal bigDecimal = BigDecimal.valueOf(1000);
        BigDecimal multiplyVal =  bigDecimal.multiply(BigDecimal.valueOf(10));

        System.out.println("debug start");
        System.out.println("Show Execution Point (Alt + F10)：如果你的光标在其它行或其它页面，点击这个按钮可跳转到当前代码执行的行");
        System.out.println("Step Over (F8)：步过，一行一行地往下走，如果这一行上有方法不会进入方法");
        System.out.println("Step Into (F7)：步入，如果当前行有方法，可以进入方法内部");
        System.out.println("一行代码里有好几个方法，怎么只选择某一个方法进入；按Shift + F7，会自动定位到当前断点行，并列出需要进入的方法");
        System.out.println("Force Step Into (Alt + Shift + F7)：强制步入，能进入任何方法");
        System.out.println("Step Out (Shift + F8)：步出，从步入的方法内退出到方法调用处");
        System.out.println("Drop Frame (默认无)：回退断点，后面章节详细说明。");
        System.out.println("Run to Cursor (Alt + F9)：运行到光标处，你可以将光标定位到你需要查看的那一行，然后使用这个功能，代码会运行至光标行，而不需要打断点");
        System.out.println("Evaluate Expression (Alt + F8)：计算表达式；选中某个表达式再Alt + F8，弹出计算表达式的窗口");
        System.out.println("设置变量，在计算表达式的框里，可以改变变量的值，这样有时候就能很方便我们去调试各种值的情况了不是");
        System.out.println("Update 'tech' application (Ctrl + F5)：更新程序，一般在你的代码有改动后可执行这个功能");
        System.out.println("Resume Program (F9)：恢复程序， 运行到下一个断点");
        System.out.println("View Breakpoints (Ctrl + Shift + F8)：查看所有断点");
        System.out.println("Mute Breakpoints：哑的断点，选择这个后，所有断点变为灰色，断点失效");
        System.out.println("在Watches里，点击New Watch，输入需要查看的变量。或者可以从Variables里拖到Watche里查看");
        System.out.println("断点上右键直接设置当前断点的条件");
        System.out.println("IDEA在Debug时默认阻塞级别是ALL，会阻塞其它线程，只有在当前调试线程走完时才会走其它线程。可以在View Breakpoints里选择Thread，然后点击Make Default设置为默认选项");
        System.out.println("回退的方式有两种，一种是Drop Frame按钮(图8.2)，按调用的方法逐步回退，包括三方类库的其它方法");

        System.out.println("Force Return，弹出Return Value的窗口，我这个方法的返回类型为Map，所以，我这里直接返回 results，来强制返回，从而不再进行后续的流程。或者你可以new HashMap<>()");
        System.out.println("testMethod1() force return---" + testMethod1());;
        System.out.println("testMethod2() return---" + testMethod2());;
    }

    public static int testMethod1() {
        return 1;
    }
    public static String testMethod2() {
        return "1";
    }
}
