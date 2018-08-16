package com.example.autocodetemplate.ohter.practice.mytestP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TwoColorBall {
    public static void main(String[] args) throws Exception {
        File file = new File("d:/twocolorhistory.txt");
        String twoColorStr = getTwoColorHistory(file);

        String[] phases = twoColorStr.split("\n");

        int[] redBall = new int[33];
        int[] blueBall = new int[16];
        String[] phaseArray = new String[7];
        for (String phase : phases) {
            phaseArray = phase.split(",");
            for (int i = 0; i < phaseArray.length; i++) {
                if (i == 6) {
                    blueBall[Integer.valueOf(phaseArray[i]) - 1]++;
                } else {
                    redBall[Integer.valueOf(phaseArray[i]) - 1]++;
                }
            }
        }

        for (int i = 0; i < 33; i++) {
            System.out.print(i + 1 + ":" + redBall[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 16; i++) {
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
        for (int i = 0; i < 3; i++) {
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

    public static String getTwoColorHistory(File file) throws Exception {
        FileReader fileReader = new FileReader(file);
        StringBuffer twocolorhistory = new StringBuffer();
        BufferedReader bufferedInputStream = new BufferedReader(fileReader);
        String str = "";
        while ((str = bufferedInputStream.readLine()) != null) {
            twocolorhistory.append(str).append("\n");
        }
        fileReader.close();
        return twocolorhistory.toString();
    }
}

enum twoColorBlueBall {
    one(0), two(0), three(0), four(0), five(0), six(0), seven(0), eight(0), nine(0), ten(0), eleven(0), twelve(
            0), thirteen(0), fourteen(0), fifteen(0), sixteen(0);

    private final int value;

    private twoColorBlueBall(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

enum twoColorRedBall {
    one(0), two(0), three(0), four(0), five(0), six(0), seven(0), eight(0), nine(0), ten(0), eleven(0), twelve(
            0), thirteen(0), fourteen(0), fifteen(0), sixteen(0), seventeen(0), eighteen(0), nineteen(0), twenty(
            0), twentyone(0), twentytwo(0), twentythree(0), twentyfour(0), twentyfive(0), twentysix(
            0), twentyseven(0), twentyeight(
            0), twentynine(0), thirty(0), thirtyone(0), thirtytwo(0), thirtythree(0);

    private final int value;

    private twoColorRedBall(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
