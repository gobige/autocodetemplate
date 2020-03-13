package com.example.autocodetemplate.ohter.practice.base.file;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

public class FileTest {

    public static void main(String[] args) throws Exception{

        loadPropertyies();
    }

    /**
     * 文件流转propertys对象
     * @return
     */
    public static Properties loadPropertyies() {
        Properties properties = new Properties();

        File file = new File("src\\main\\resources\\application.properties");

        try {
            InputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
        } catch (Exception e) {

        }

        return properties;
    }

    public static void listFile(String[] args) {
        File path = new File(".");
        String [] list;

        if (args.length == 0) {
            // 列出该目录下所有文件
            list = path.list();
        } else {
            list = path.list(new FilenameFilter() {
                // 过滤，匹配指定文件名文件
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


    /**
     * 读取字符文件
     */
    public static String BufferedInputFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        StringBuilder stringBuilder = new StringBuilder();

        String s;
        while ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s);
        }
        bufferedReader.close();

        return stringBuilder.toString();
    }


    /**
     * 从内存读取
     */
    public static void MemoryInput(String[] args) throws Exception{
        StringReader stringReader = new StringReader(BufferedInputFile("README.md"));

        int c;
        while ((c = stringReader.read()) != -1) {
            System.out.println((char)c);
        }
    }

    /**
     * 格式化内存输入
     */
    public static void FormatMemoryInput() throws Exception{
        DataInputStream dataInputStream  = new DataInputStream(new BufferedInputStream(new FileInputStream("README.md")));

        while (dataInputStream.available() != 0) {
            System.out.println((char) dataInputStream.read());
        }
    }
}
