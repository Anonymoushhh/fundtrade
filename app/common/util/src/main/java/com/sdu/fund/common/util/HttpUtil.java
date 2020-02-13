package com.sdu.fund.common.util;

import com.sdu.fund.common.enums.RequestMethodEnum;
import com.sdu.fund.common.exception.HttpException;
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

import java.io.IOException;
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

    public static String send(String url, RequestMethodEnum requestMethodEnum) throws HttpException {
        String result = null;
        try {
            switch (requestMethodEnum) {
                case GET:
                    result = sendGet(url);
                    if(result!=null){
                        return result;
                    }else{
                        throw new HttpException("请求无结果！");
                    }
                case POST:
                    result = sendPost(url);
                    if(result!=null){
                        return result;
                    }else{
                        throw new HttpException("请求无结果！");
                    }
                default:
                    throw new HttpException("未知请求方法！");
            }
        } catch (Exception e) {
            throw new HttpException(e.getMessage());
        }
    }

    public static String send(String url, RequestMethodEnum requestMethodEnum, Map<String, String> map) throws HttpException {
        String result = null;
        try {
            switch (requestMethodEnum) {
                case GET:
                    result = sendGet(url);
                    if(result!=null){
                        return result;
                    }else{
                        throw new HttpException("请求无结果！");
                    }
                case POST:
                    result = sendPost(url,map);
                    if(result!=null){
                        return result;
                    }else{
                        throw new HttpException("请求无结果！");
                    }
                default:
                    throw new HttpException("Unexpected value: " + requestMethodEnum);
            }
        } catch (Exception e) {
            throw new HttpException(e.getMessage());
        }
    }

    /**
     * 发送HttpGet请求
     *
     * @param url
     * @return
     */
    private static String sendGet(String url) throws HttpException {

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
            throw new HttpException(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new HttpException(e.getMessage());
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
    private static String sendPost(String url, Map<String, String> map) throws HttpException {
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
            throw new HttpException(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new HttpException(e.getMessage());
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
    private static String sendPost(String url) throws HttpException {
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
            throw new HttpException(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new HttpException(e.getMessage());
            }
        }
        return result;

    }
}
