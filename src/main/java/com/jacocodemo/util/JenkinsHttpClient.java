package com.jacocodemo.util;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

public class JenkinsHttpClient {
    private static final String CHARSET_UTF_8 = "utf-8";
    private static final String STATUS = "Status";
    private static final String CONTENTTYPE = "ContentType";
    private static final String BODY = "body";
    private JsonObject responseResult = new JsonObject();

    /**
     * 以get方式请求Jenkins api
     * @param url
     * @param headerMap
     * @param urlParams
     * @return
     */
    public JsonObject doGet(String url, Map<String, String> headerMap, Map<String, String> urlParams) {
        CloseableHttpResponse response = null;
        HttpGet httpGet = null;
        try {
            //创建HttpClient对象，HttpClients.createDefault()
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //构建url中的请求参数
            if (urlParams != null && !urlParams.isEmpty()){
                String params = toHttpGetParams(urlParams);
                url = url + "?" + params;
            }
            //基于要发送的HTTP请求类型创建HttpGet或者HttpPost实例
            httpGet = new HttpGet(url);
            //get请求，添加header
            if (headerMap != null && !headerMap.isEmpty()) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            //设置连接超时
            httpGet.setConfig(setRequestConfig());
            //发起请求
            response = httpClient.execute(httpGet);
            //获取返回结果
            return getResponse(response);
        } catch (Exception e) {
            String msg = "网络出错, 可能的原因是: 您的网络不通, 或者服务器停掉了! 请求URL >>>:" + url;
        }finally {
            //关闭连接
            closeResponse(response);
        }
        return null;
    }

    /**
     * 这里只是其中的一种场景,也就是把参数用&符号进行连接且进行URL编码
     * 根据实际情况拼接参数
     */
    private String toHttpGetParams(Map<String, String> urlParams) throws Exception {
        String res = "";
        for (Map.Entry<String, String> entry : urlParams.entrySet()) {
            res += entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), CHARSET_UTF_8) + "&";
        }
        return "".equals(res) ? "" : StringUtils.chop(res);
    }

    /**
     * 设置连接超时信息
     * @return
     */
    private RequestConfig setRequestConfig(){
        /*设置连接超时
        setConnectTimeout：设置连接超时时间，单位毫秒。
        setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
        setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
        */
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(1000)
                .setSocketTimeout(5000)
                .build();
        return  requestConfig;
    }

    /**
     * 获取Response信息,返回结果以jsonObject形式
     * @param response
     * @return
     */
    private JsonObject getResponse(CloseableHttpResponse response){
        HttpEntity entity = response.getEntity();
        try {
            this.responseResult.addProperty(STATUS,response.getStatusLine().getStatusCode());
            this.responseResult.addProperty(CONTENTTYPE,entity.getContentType().getValue());
            String result = EntityUtils.toString(entity, CHARSET_UTF_8);
            this.responseResult.addProperty(BODY, result);
            //关闭HttpEntity
            EntityUtils.consume(entity);
        } catch (IOException e) {
            System.err.println("HttpEntity关闭失败, " + e);
        }
        return this.responseResult;
    }

    /**
     * 关闭连接
     * @param response
     */
    private void closeResponse(CloseableHttpResponse response){
        if (response != null) {
            try {
                response.close();
                //不可以关闭，不然连接池就会被关闭
                //httpclient.close();
            } catch (IOException e) {
                System.err.println("httpClient关闭连接失败, " + e);
            }
        }
    }
}
