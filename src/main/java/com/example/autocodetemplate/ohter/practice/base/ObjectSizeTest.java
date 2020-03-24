package com.example.autocodetemplate.ohter.practice.base;

import java.util.ArrayList;
import java.util.List;

public class ObjectSizeTest {
    public static void main(String[] args) {
        final List<AAAAA> aaa = new ArrayList<>(100000);
        final List<BBBBB> bbb = new ArrayList<>(100000);
        final List<CCCCC> ccc = new ArrayList<>(100000);
        final List<DDDDD> ddd = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            aaa.add(new AAAAA());
            bbb.add(new BBBBB());
            ccc.add(new CCCCC());
            ddd.add(new DDDDD());
        }
    }
}

class AAAAA {}

class BBBBB {
    int a = 1;
}

class CCCCC {
    long a = 1L;
}

class DDDDD {
    String s = "hello";
}
