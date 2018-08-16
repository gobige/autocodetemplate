package com.example.autocodetemplate.ohter.practice.network;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Net {
    public static void main(String[] args) throws IOException {

        // InetAddress address = InetAddress.getByName("www.baidu.com");
        // SecurityManager manager = new SecurityManager();
        // manager.checkConnect("119.23.28.184",-1);
        // //System.out.println(address.getLocalHost());
//		URLencodeTest();
//		httpGetTest("acfun");
        testDeleteMethod();
    }

    public static void URLencodeTest() throws UnsupportedEncodingException {
        String url = "This is kongge";
        String url1 = "this_is_xiahuaxian";
        String url2 = "this-is-lianzifu";
        String url3 = "this.is.dianhao";
        String url4 = "this*is*xinhao";
        String url5 = "this!is!xinhao";
        System.out.println(URLEncoder.encode(url, "Utf-8"));
        System.out.println(URLEncoder.encode(url1, "Utf-8"));
        System.out.println(URLEncoder.encode(url2, "Utf-8"));
        System.out.println(URLEncoder.encode(url3, "Utf-8"));
        System.out.println(URLEncoder.encode(url4, "Utf-8"));
        System.out.println(URLEncoder.encode(url5, "Utf-8"));
    }

    public static void httpGetTest(String val) throws MalformedURLException, UnsupportedEncodingException {
        queryString qeruy = new queryString();
        qeruy.add("wd", val);
        URL reqUrl = new URL("https://www.baidu.com/s?" + qeruy.toString());
        try (InputStream is = new BufferedInputStream(reqUrl.openStream())) {
            InputStreamReader reader = new InputStreamReader(is);

            int i;
            while ((i = reader.read()) != -1) {
                System.out.print((char) i);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void testDeleteMethod() throws IOException {
        URL url = new URL("http://localhost:8580/test_web/index.jsp");

        HttpURLConnection htc = (HttpURLConnection) url.openConnection();

        htc.setRequestMethod("DELETE");
        System.out.println(htc.getRequestMethod());
        InputStream bis = new BufferedInputStream(htc.getInputStream());
        InputStreamReader reader = new InputStreamReader(bis, "utf-8");
        int c;
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
    }
}

class queryString {
    private StringBuilder queryStr = new StringBuilder();

    private void querystring() {
    }

    public synchronized void add(String name, String val) throws UnsupportedEncodingException {
        queryStr.append("&");
        encode(name, val);
    }

    private synchronized void encode(String name, String val) throws UnsupportedEncodingException {
        queryStr.append(URLEncoder.encode(name, "utf-8"));
        queryStr.append("=");
        queryStr.append(URLEncoder.encode(val, "utf-8"));
    }

    @Override
    public String toString() {
        return queryStr.toString();
    }
}