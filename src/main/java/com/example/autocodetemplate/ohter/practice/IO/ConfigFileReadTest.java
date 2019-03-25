package com.example.autocodetemplate.ohter.practice.IO;

import org.apache.catalina.core.ApplicationContext;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

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

        InputStream inputStream = configFileReadTest.getByClassLoader("/application.properties");
        InputStream fileInputStream = configFileReadTest.getByFile("C:\\Users\\爱车小屋\\Documents\\GitHub\\autocodetemplate\\src\\main\\resources\\application.properties");

        Properties prop = configFileReadTest.getPropertiesFromInputStream(fileInputStream);
        String propkey = prop.getProperty("com.yates.thirdparty.juhe.host");

        // 流只能被导入加载一次 和上面处理方式类似  只是 把properties 封装成hashmap结构
        ResourceBundle resource = new PropertyResourceBundle(fileInputStream);
        String resourcekey = resource.getString("com.yates.thirdparty.juhe.host");

        ResourceBundle resourceBundle = configFileReadTest.getResourceBundle("application");
        String Bundlekey = resourceBundle.getString("com.yates.thirdparty.juhe.host");

//        InputStream urlInputStream = configFileReadTest.getByUrl("/test.properties");

        System.out.println(Bundlekey);
    }

//    InputStream getByUrl(String filePath) throws IOException {
//        ServletContext servletContext = new ApplicationContext();
//        InputStream in = context.getResourceAsStream(filePath);
//        return in;
//    }

    InputStream getByUrl(String filePath) throws IOException {
        URL url = new URL(filePath);

        return url.openStream();
    }

    /**
     * 通过java提供的文件流方式
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    InputStream getByFile(String filePath) throws FileNotFoundException {
        InputStream inStream = new FileInputStream(new File(filePath));

        return inStream;
    }

    /**
     * 通过类加载器读取配置文件
     * @param path
     * @return
     */
    InputStream getByClassLoader(String path) {
        InputStream inputStream2 = this.getClass().getResourceAsStream(path);

        // TODO 获取流为null，但是classLoader又不为null（appclassloader），获取URL为null
        InputStream inputStream3 = ClassLoader.getSystemResourceAsStream(path);

        // TODO 获取流为null，但是classLoader又不为null， 与上面方法一样都是通过类加载器，获取文件路由
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);

        return inputStream2;
    }

    /**
     * 通过properties加载流文件
     * @param inputStream
     * @return
     * @throws IOException
     */
    Properties getPropertiesFromInputStream(InputStream inputStream) throws IOException {
        Properties prop = new Properties();
        prop.load(inputStream);

        return prop;
    }

    ResourceBundle getResourceBundle(String path) {
        ResourceBundle resource = ResourceBundle.getBundle(path);

        return resource;
    }
}
