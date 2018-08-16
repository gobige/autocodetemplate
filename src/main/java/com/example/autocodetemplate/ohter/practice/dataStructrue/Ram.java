package com.example.autocodetemplate.ohter.practice.dataStructrue;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class Ram {

    /**
     * 软引用
     */
    public void reference() {
        SoftReference<Object> soft = new SoftReference<Object>(new Object());

    }

    /**
     * verbose:gc -Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
     */
    public void heapOOM() {
        List<Object> list = new ArrayList<Object>();

        while (true) {
            list.add(new Object());
        }
    }
}
