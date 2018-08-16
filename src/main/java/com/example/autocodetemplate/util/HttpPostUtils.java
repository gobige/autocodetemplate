package com.example.autocodetemplate.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class HttpPostUtils {
    /**
     *
     */
    private final static Logger logger = LoggerFactory.getLogger(HttpPostUtils.class);

    /**
     * 执行一个HTTP POST请求，返回请求响应的HTML
     *
     * @param url      请求的URL地址
     * @param paramMap 请求的查询参数,可以为null
     * @return 返回请求响应的HTML
     */
    public static String doPost(String url, Map<String, String> paramMap) throws UnsupportedEncodingException {
        long startTime = System.currentTimeMillis();
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient client = builder.build();
        HttpPost method = new HttpPost(url);
        method.setHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=utf-8");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (paramMap != null && paramMap.size() > 0) {
            for (String key : paramMap.keySet()) {
                params.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
        }

        method.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        int statusCode = 0;
        String result;
        try {
            HttpResponse execute = client.execute(method);
            HttpEntity entity = execute.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("::::完成对地址：【" + url + "】的Post请求,请求返回码为：【" + statusCode + "】:::所用时间为：【" + (System.currentTimeMillis() - startTime) + "】");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    public synchronized static String postData(String url, Map<String, String> params, String codePage) throws Exception {
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient httpClient = builder.build();

        final HttpPost method = new HttpPost(url);
        RequestConfig config = RequestConfig.custom().setSocketTimeout(10 * 1000).setConnectTimeout(10 * 1000).build();
        method.setConfig(config);
        method.setHeader("Content-Type", codePage);
        if (params != null) {
            method.setEntity(new UrlEncodedFormEntity(assembleRequestParams(params), codePage));
        }
        String result;
        try {
            HttpResponse execute = httpClient.execute(method);
            result = EntityUtils.toString(execute.getEntity(), codePage);
        } catch (final Exception e) {
            throw e;
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    /**
     * 组装http请求参数
     *
     * @param data
     * @return
     */
    private synchronized static List<NameValuePair> assembleRequestParams(Map<String, String> data) {
        final List<NameValuePair> nameValueList = new ArrayList<>();

        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            nameValueList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        return nameValueList;
    }
}
