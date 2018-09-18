package com.icekredit.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * http统一方法
 * Created by 徐华 on 2016/3/14.
 */
public class HttpHandler {

    /**
     * 银行卡获取卡名称 开卡行
     *
     * @param num
     * @return
     */
    public static String getCardType(String num) throws IOException {
        return get("http://120.55.65.90:8007/bankCard?cardNum=" + num);
    }

    /**
     * https get方法
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        if (!MyString.isEmpty(url)) {
            HttpClient httpClient;
            if (url.startsWith("https")) {
                httpClient = getHttpsClient();
            } else {
                httpClient = getHttpClient();
            }
            return get(httpClient, url);
        } else {
            return "url is null";
        }
    }

    /**
     * https post方法
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params) throws IOException {
        if (MyString.isEmpty(url)) {
            return "url is null";
        } else {
            HttpClient httpClient;
            if (url.startsWith("https")) {
                httpClient = getHttpsClient();
            } else {
                httpClient = getHttpClient();
            }

            List paras = params.entrySet()
                    .stream()
                    .map(x -> new BasicNameValuePair(x.getKey(), x.getValue())).collect(Collectors.toList());
            UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(paras, "UTF-8");
            return post(httpClient, url, entity1);
        }
    }

    public static String jsonPost(String url, JSONObject jsonObject) throws IOException {
        if (url == null) {
            return "url is null";
        } else {
            HttpClient httpClient;
            if (url.startsWith("https")) {
                httpClient = getHttpsClient();
            } else {
                httpClient = getHttpClient();
            }
            HttpPost httpPost = new HttpPost(url);

            StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            String content = "";
            try {

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

                content = EntityUtils.toString(httpEntity, "utf-8");
                EntityUtils.consume(httpEntity);
            } finally {
                httpPost.abort();
            }
            return content;
        }
    }

    /**
     * https post方法
     *
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String post(String url, String json) throws IOException {
        if (url != null) {
            HttpClient httpClient;
            if (url.startsWith("https")) {
                httpClient = getHttpsClient();
            } else {
                httpClient = getHttpClient();
            }

            StringEntity entity = new StringEntity(json, "UTF-8");
            return post(httpClient, url, entity);
        } else {
            return "url is null";
        }
    }

    /**
     * https get方法
     *
     * @param httpClient
     * @param url
     * @return
     * @throws IOException
     */
    private static String get(HttpClient httpClient, String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);

        HttpEntity httpEntity = httpResponse.getEntity();

        String response = EntityUtils.toString(httpEntity, "utf-8");
        EntityUtils.consume(httpEntity);
        return response;

    }

    /**
     * https post方法
     *
     * @param httpClient
     * @param url
     * @param entity
     * @return
     * @throws IOException
     */
    private static String post(HttpClient httpClient, String url, HttpEntity entity) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        /*RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).build();
        httpPost.setConfig(requestConfig);*/
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();

        String response = EntityUtils.toString(httpEntity, "utf-8");
        EntityUtils.consume(httpEntity);
        return response;
    }


    private static HttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    private static HttpClient getHttpsClient() {
        SSLContext context = null;
        try {
            context = (new SSLContextBuilder()).loadTrustMaterial(null, (x509Certificates, s) -> true).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
           
        }
        return HttpClients.custom().setSslcontext(context).build();
    }

    public static void main(String args[]){
        Map<String, String> m = new HashMap<String, String>();
        m.put("merchant_name","dddddd");
        m.put("merchant_pwd","dddddddddd");
        JSONObject returnObj = null;
        long startTime = 0;
        long endTime = 0;
        try {
            System.out.println("开始");
            startTime = System.currentTimeMillis();   //获取开始时间
            returnObj = JSONObject.parseObject(HttpHandler.post("http://10.168.3.4:8080",m));


        } catch (IOException e) {
            endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("异常");

        }
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
}
