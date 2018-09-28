package com.example.autocodetemplate.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateTemplateWebControllerTest {

    /**
     * getForEntity方法的返回值是一个ResponseEntity<T>，
     * ResponseEntity<T>是Spring对HTTP请求响应的封装，包括了几个重要的元素，如响应码、contentType、contentLength、响应消息体等
     * getForEntity的第一个参数为我要调用的服务的地址，这里我调用了服务提供者提供的/hello接口，注意这里是通过服务名调用而不是服务地址，如果写成服务地址就没法实现客户端负载均衡了。
     *  getForEntity第二个参数String.class表示我希望返回的body类型是String
     *
     *   restTemplate.getForEntity("http://HELLO-SERVICE/sayhello?name={name}", String.class, map)
     *    也可以前面使用name={name}这种形式，最后一个参数是一个map，map的key即为前边占位符的名字，map的value为参数值
     * @throws Exception
     */

    /**
     * getForObject函数实际上是对getForEntity函数的进一步封装，如果你只关注返回的消息体的内容
     * <p>
     * restTemplate.postForEntity("http://HELLO-SERVICE/getbook2", book, Book.class);
     *
     * @throws Exception
     */
    @Test
    public void getTempletTest() throws Exception {
        RestTemplate rest = new RestTemplate();
        com.example.autocodetemplate.filter.GenerateTempFilter tempFilter = new com.example.autocodetemplate.filter.GenerateTempFilter();
        tempFilter.setTableName("oss_department");

        ResponseEntity responseEntity = rest.postForEntity("http://localhost:8080/yates/template/getTemp.json", tempFilter, String.class);

        responseEntity.getBody();
    }
}
