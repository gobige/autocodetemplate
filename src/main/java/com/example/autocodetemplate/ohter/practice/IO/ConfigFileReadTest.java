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
    public static void main(String[] args) {
        ConfigFileReadTest configFileReadTest = new ConfigFileReadTest();

        configFileReadTest.getByClassLoader("test.properties");
    }

    InputStream getByClassLoader(String path) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);

        return inputStream;
    }

    Properties getPropertiesFromInputStream(InputStream inputStream) throws IOException {
        Properties prop = new Properties();
        prop.load(inputStream);

        return prop;
    }
}
