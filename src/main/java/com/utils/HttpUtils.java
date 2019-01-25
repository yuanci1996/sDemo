/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liweibing
 */
public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String getUrl(String str) throws Exception {
        URL url = new URL(str);
        String res = "";
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("contentType", "UTF-8");
            conn.setConnectTimeout(1000 * 3);
            conn.connect();
            conn.setReadTimeout(1000 * 3);


            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line;
            }
            in.close();
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    /**
     * post提交数据
     */

    public static String sendPostRequestByForm(String path, String params) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");// 提交模式
        // conn.setConnectTimeout(10000);//连接超时 单位毫秒
        // conn.setReadTimeout(2000);//读取超时 单位毫秒
        conn.setDoOutput(true);// 是否输入参数
        byte[] bypes = params.toString().getBytes();
        conn.getOutputStream().write(bypes);// 输入参数
        String res = "";
        try {
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line;
            }
            in.close();
        } catch (Exception e) {
            throw e;
        }
        return res;
    }


    public static String postParam(String url, HashMap<String, Object> parametersMap) {

        try {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> e : parametersMap.entrySet()) {
                nvps.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
            }
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != 200) {
                // throw new RuntimeException("请求" + url + "失败:"+ response.getStatusLine().getStatusCode());
                logger.error("Failed url {},http status {}", url, response.getStatusLine().getStatusCode());
                return null;
            }
            String responseData = EntityUtils.toString(response.getEntity()).trim();
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String httpPostJson(String url, String json) {
        //post请求返回结果
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            //解决中文乱码问题
            StringEntity entity = new StringEntity(json, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.error("Failed url {},http status {},body {}", url, response.getStatusLine().getStatusCode(),json);
                return null;
            }
            String responseData = EntityUtils.toString(response.getEntity(), "UTF-8");
            return responseData;
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }
        return null;
    }


}
