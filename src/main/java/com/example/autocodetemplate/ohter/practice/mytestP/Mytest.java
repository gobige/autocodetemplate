package com.example.autocodetemplate.ohter.practice.mytestP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Mytest {
    public static void main(String[] args) {
        String source = "Setasdf)sadf";
        Mytest mytest = new Mytest();
        try {
            source = mytest.FileInputStreamDemo("e:/getset.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        source = source.replaceAll("\\s", " ");
        mytest.getSet(source, "req.");
    }

    public void getSet(String source, String who) {
        int indexBegin = 0;
        int subBegin = 0;
        int subEnd = 0;
        int num = 0;
        while (true) {
            subBegin = source.indexOf("set", indexBegin);
            if (subBegin == -1) {
                break;
            }
            subEnd = source.indexOf(")", subBegin);
            indexBegin = subEnd;
            num++;
            System.out.println((who + source.substring(subBegin, subEnd + 1) + ";").replaceAll("\\s", ""));
        }
        System.out.println(num);
    }

    public String FileInputStreamDemo(String path) throws IOException {
        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while ((fis.read(buf)) != -1) {
            sb.append(new String(buf));
            buf = new byte[1024];//重新生成，避免和上次读取的数据重复
        }
        return sb.toString();
    }
}
