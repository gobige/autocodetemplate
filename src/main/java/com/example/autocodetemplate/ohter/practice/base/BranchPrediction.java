package com.example.autocodetemplate.ohter.practice.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * 分支预测
 */
@Slf4j
public class BranchPrediction {
    private static final int cycleCount = 100000000;

    public static void main(String args[]) {

        branchIfTest(false);
    }

    private static void branchPreFor() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("循环由大到小");
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j <1000; j ++) {
                for (int k = 0; k < 100; k++) {
                }
            }
        }
        stopWatch.stop();

        stopWatch.start("循环由小到大");
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <1000; j ++) {
                for (int k = 0; k < 10000; k++) {
                }
            }
        }

        stopWatch.stop();


        System.out.println(stopWatch.prettyPrint());
    }

    private static void branchIfTest(boolean branchIf) {
        StopWatch stopWatch = new StopWatch();

        // 预热
        for (int i = 0; i < cycleCount; i++) {}

        stopWatch.start("branchIf success");
        for (int i = 0; i < cycleCount; i++) {
            if (!branchIf) {
            }else {
            }
        }
        stopWatch.stop();

        stopWatch.start("branchIf failure");
        for (int i = 0; i < cycleCount; i++) {
            if (branchIf) {
            }else {
            }
        }
        stopWatch.stop();


        System.out.println(stopWatch.prettyPrint());
    }
}
