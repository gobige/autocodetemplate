package com.example.autocodetemplate.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class HttpGetUtils {

    /**
     *
     */
    private final static Logger logger = LoggerFactory.getLogger(HttpGetUtils.class);

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static JSONObject httpGet(String url) {
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient httpClient = httpClientBuilder.build();
            //发送get请求
            URL realUrl = new URL(url);
            URI uri = new URI(realUrl.getProtocol(), realUrl.getHost(), realUrl.getPath(), realUrl.getQuery(), null);
            HttpGet request = new HttpGet(uri);
            HttpResponse response = httpClient.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.parseObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        } catch (URISyntaxException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }


}
