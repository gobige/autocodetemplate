package com.example.autocodetemplate.ohter.practice.mytestP;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class BigHappy {

    public static void main(String[] args) throws Exception {
        File file = new File("d:/bighappy.txt");
        String twoColorStr = TwoColorBall.getTwoColorHistory(file);

        String[] phases = twoColorStr.split("\n");

        int[] redBall = new int[35];
        int[] blueBall = new int[12];
        String[] phaseArray = new String[7];
        for (String phase : phases) {
            phaseArray = phase.split(",");
            for (int i = 0; i < phaseArray.length; i++) {
                if (i > 4) {
                    blueBall[Integer.valueOf(phaseArray[i]) - 1]++;
                } else {
                    redBall[Integer.valueOf(phaseArray[i]) - 1]++;
                }
            }
        }

        for (int i = 0; i < 35; i++) {
            System.out.print(i + 1 + ":" + redBall[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 12; i++) {
            System.out.print(i + 1 + ":" + blueBall[i] + " ");
        }
        System.out.println();

        int redMaxNum = -1;
        int redMax = 0;
        Map<Integer, Integer> redballmap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> blueballmap = new HashMap<Integer, Integer>();
        // 修改
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < redBall.length; j++) {
                if (redBall[j] > redMax) {
                    redMax = redBall[j];
                    redMaxNum = j;
                }
            }
            redballmap.put(redMaxNum + 1, redBall[redMaxNum]);
            redBall[redMaxNum] = 0;
            redMax = 0;
        }
        Iterator<Entry<Integer, Integer>> redIte = redballmap.entrySet().iterator();
        while (redIte.hasNext()) {
            Entry<Integer, Integer> entry = redIte.next();

            System.out.println("red:" + entry.getKey() + ":" + entry.getValue() + " ");
        }

        int blueMaxNum = -1;
        int blueMax = 0;
        // 修改
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < blueBall.length; j++) {
                if (blueBall[j] > blueMax) {
                    blueMax = blueBall[j];
                    blueMaxNum = j;
                }
            }
            blueballmap.put(blueMaxNum + 1, blueBall[blueMaxNum]);
            blueBall[blueMaxNum] = 0;
            blueMax = 0;

        }

        Iterator<Entry<Integer, Integer>> blueIte = blueballmap.entrySet().iterator();
        while (blueIte.hasNext()) {
            Entry<Integer, Integer> entry = blueIte.next();

            System.out.println("blue:" + entry.getKey() + ":" + entry.getValue() + " ");
        }
    }

}

