package com.example.autocodetemplate.ohter.practice.IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: 配置文件读取的几种方式</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class ConfigFileReadTest {
    public static void main(String[] args) throws IOException {
        ConfigFileReadTest configFileReadTest = new ConfigFileReadTest();

        InputStream inputStream = configFileReadTest.getByClassLoader("/test.properties");
        Properties prop = new Properties();
        prop.load(inputStream);
        String key = prop.getProperty("test.name");
         System.out.println(key);
    }

    InputStream getByClassLoader(String path) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        InputStream inputStream2 = this.getClass().getResourceAsStream(path);

        return inputStream;
    }

    Properties getPropertiesFromInputStream(InputStream inputStream) throws IOException {
        Properties prop = new Properties();
        prop.load(inputStream);

        return prop;
    }
}
