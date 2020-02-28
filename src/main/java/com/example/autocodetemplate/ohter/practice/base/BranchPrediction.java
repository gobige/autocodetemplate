package com.example.autocodetemplate.ohter.practice.base;

import org.springframework.util.StopWatch;

/**
 * 分支预测
 */
public class BranchPrediction {
        public static void main(String args[]) {
            StopWatch stopWatch = new StopWatch();

            stopWatch.start("循环由小到大");
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j <10000; j ++) {
                    for (int k = 0; k < 100000; k++) {
                    }
                }
            }

            stopWatch.stop();

            stopWatch.start("循环由大到小");
            for (int i = 0; i < 100000; i++) {
                for (int j = 0; j <10000; j ++) {
                    for (int k = 0; k < 1000; k++) {
                    }
                }
            }
            stopWatch.stop();


            System.out.println(stopWatch.prettyPrint());
        }
}
