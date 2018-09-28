package com.example.autocodetemplate.ohter.practice.base;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class FileTest {


}

/**
 * 列出文件
 */
class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String [] list;

        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile(args[0]);

                @Override
                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });
        }

        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);

        for (String s : list) {
            System.out.println(s);
        }
    }
}

/**
 * 读取字符文件
 */
class BufferedInputFile {
    public static String read(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        StringBuilder stringBuilder = new StringBuilder();

        String s;
        while ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s);
        }
        bufferedReader.close();

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception{

        System.out.println(read("README.md"));
    }
}

/**
 * 从内存读取
 */
class MemoryInput {
    public static void main(String[] args) throws Exception{
        StringReader stringReader = new StringReader(BufferedInputFile.read("README.md"));

        int c;
        while ((c = stringReader.read()) != -1) {
            System.out.println((char)c);
        }
    }
}

/**
 * 格式化内存输入
 */
class FormatMemoryInput {
    public static void main(String[] args) throws Exception{
        DataInputStream dataInputStream  = new DataInputStream(new BufferedInputStream(new FileInputStream("README.md")));

        while (dataInputStream.available() != 0) {
            System.out.println((char) dataInputStream.read());
        }
    }
}