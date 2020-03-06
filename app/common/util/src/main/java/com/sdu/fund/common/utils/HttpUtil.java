package com.sdu.fund.common.utils;

import com.sdu.fund.common.enums.RequestMethodEnum;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @program: fundproduct
 * @description: http工具类,请求失败抛异常，上层处理
 * @author: anonymous
 * @create: 2019-11-23 22:10
 **/
public class HttpUtil {

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String send(String url, RequestMethodEnum requestMethodEnum) throws Exception {
        String result = null;
        try {
            switch (requestMethodEnum) {
                case GET:
                    result = sendGet(url);
                    if(result!=null){
                        return result;
                    }else{
                        throw new Exception("请求无结果！");
                    }
                case POST:
                    result = sendPost(url);
                    if(result!=null){
                        return result;
                    }else{
                        throw new Exception("请求无结果！");
                    }
                default:
                    throw new Exception("未知请求方法！");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static String send(String url, RequestMethodEnum requestMethodEnum, Map<String, String> map) throws Exception {
        String result = null;
        try {
            switch (requestMethodEnum) {
                case GET:
                    result = sendGet(url);
                    if(result!=null){
                        return result;
                    }else{
                        throw new Exception("请求无结果！");
                    }
                case POST:
                    result = sendPost(url,map);
                    if(result!=null){
                        return result;
                    }else{
                        throw new Exception("请求无结果！");
                    }
                default:
                    throw new Exception("Unexpected value: " + requestMethodEnum);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 发送HttpGet请求
     *
     * @param url
     * @return
     */
    private static String sendGet(String url) throws Exception {

        HttpGet httpget = new HttpGet(url);
        httpget.addHeader("Content-Type", "application/json; charset=utf-8");
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpclient.execute(httpget);
            if (response != null&&response.getStatusLine().getStatusCode()==200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new Exception(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为map
     *
     * @param url
     * @param map
     * @return
     */
    private static String sendPost(String url, Map<String, String> map) throws Exception {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpclient.execute(httppost);
            if (response != null&&response.getStatusLine().getStatusCode()==200) {
                HttpEntity entity1 = response.getEntity();
                if (entity1 != null) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new Exception(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 发送不带参数的HttpPost请求
     *
     * @param url
     * @return
     */
    private static String sendPost(String url) throws Exception {
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpclient.execute(httppost);
            if (response != null&&response.getStatusLine().getStatusCode()==200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new Exception(e.getMessage());
            }
        }
        return result;

    }

    public static String sendHTTPs(String url) throws Exception{
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        HttpURLConnection conn;
        try {
            trustAllHosts();
            URL realUrl = new URL(url);
            // 通过请求地址判断请求类型(http或者是https)
            if (realUrl.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) realUrl.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                conn = https;
            } else {
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置超时时间
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(2000);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            // out.print(a);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {// 使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                throw new Exception(ex.getMessage());
            }
        }
        return result;
    }

    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        } };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}
