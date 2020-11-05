package org.spring.springboot.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http接口请求工具类，包括get，post请求方式
 */
public class HttpClientUtil {

    private static final String ACCEPT = "application/json";
    private static final String CONTENT_TYPE = "application/json; charset=utf-8";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * get 方法
     *
     * @param url        请求url
     * @param headersMap 头部信息封装
     * @return 接口返回
     */
    public static String get(String url, Map<String, String> headersMap) {
        String strResult = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            //设置连接超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).build();
            PoolingHttpClientConnectionManager conMgr = new PoolingHttpClientConnectionManager();
            //设置整个连接池最大连接数 根据自己的场景决定
            conMgr.setMaxTotal(200);
            //是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。
            //设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)，路由是对maxTotal的细分。
            //（目前只有一个路由，因此让他等于最大值）
            conMgr.setDefaultMaxPerRoute(conMgr.getMaxTotal());
            if (headersMap != null) {
                for (Map.Entry<String, String> header : headersMap.entrySet()) {
                    request.setHeader(header.getKey(), header.getValue());
                }
            }
            request.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(request);
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                strResult = EntityUtils.toString(response.getEntity(), "utf-8");
                LOGGER.info(url + "请求成功，返回：" + strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else if(response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT){
                LOGGER.info(url + "请求成功，返回状态码:" + response.getStatusLine().getStatusCode());
            } else {
                //写入日志
                strResult = EntityUtils.toString(response.getEntity(), "utf-8");
                LOGGER.info(url + "请求成功，返回：" + strResult);
                String errorMessage = "请求提交失败,状态码:" + response.getStatusLine().getStatusCode() + "错误信息：" + response.getEntity().toString();
                //失败原因
                LOGGER.info(url + errorMessage);
            }
        } catch (Exception e) {
            //写入日志
            String errorMessage = "请求提交失败,异常信息:" + url + " e.getMessage:" + e.getMessage();
            //失败原因
            LOGGER.info(url + errorMessage);
            e.printStackTrace();
        } finally {
            try {
                // 关闭HTTP连接
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return strResult;
    }
    /**
     * post方法
     *
     * @param actionUrl        请求url
     * @param params 参数
     * @param authorization  authorization
     * @return 接口返回
     */
    public static String sendPost(String actionUrl, Map<String, String> params, String authorization) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(actionUrl);
        if(authorization != null){
            httppost.addHeader("Authorization", "Bearer " + authorization);
        }

        List<NameValuePair> formparams;
        UrlEncodedFormEntity uefEntity;
        CloseableHttpResponse response = null;
        try {
            if(params != null){
                formparams = convertMapToNameValuePair(params);
                uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
                uefEntity.setChunked(true);
                httppost.setEntity(uefEntity);
            }

            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            // 关闭连接,释放资源
            try {
                response.close();
                httpclient.close();
            } catch (IOException e) {
                return e.getMessage();
            }
        }
        return "";
    }
    /**
     * post方法
     *
     * @param url        请求url
     * @param headersMap 头部信息封装
     * @param paramsMap  参数封装
     * @return 接口返回
     */
    public static String post(String url, Map<String, String> headersMap, Map<String, String> paramsMap) {
        String strResult = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            if (paramsMap != null) {
                // 用于存放请求参数
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                    formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "utf-8");
                request.setEntity(entity);
            }
            //设置连接超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000).setSocketTimeout(30000).build();
            PoolingHttpClientConnectionManager conMgr = new PoolingHttpClientConnectionManager();
            //设置整个连接池最大连接数 根据自己的场景决定
            conMgr.setMaxTotal(200);
            //是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。
            //设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)，路由是对maxTotal的细分。
            //（目前只有一个路由，因此让他等于最大值）
            conMgr.setDefaultMaxPerRoute(conMgr.getMaxTotal());
            if (headersMap != null) {
                for (Map.Entry<String, String> header : headersMap.entrySet()
                ) {
                    request.setHeader(header.getKey(), header.getValue());
                }
            }
            request.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(request);
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                strResult = EntityUtils.toString(response.getEntity(), "utf-8");
                LOGGER.info(url + "请求成功，返回：" + strResult);
                url = URLDecoder.decode(url, "UTF-8");
            }else if(response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT){
                LOGGER.info(url + "请求成功，返回状态码:" + response.getStatusLine().getStatusCode());
            } else {
                //写入日志
                String errorMessage = "请求提交失败,状态码:" + response.getStatusLine().getStatusCode() + "错误信息：" + response.getEntity().toString();
                LOGGER.info(url + errorMessage);
            }
        } catch (Exception e) {
            //写入日志
            String errorMessage = "请求提交失败,异常信息:" + url + e.getMessage();
            //失败原因
            LOGGER.info(url + errorMessage);
            e.printStackTrace();
        } finally {
            try {
                // 关闭HTTP连接
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return strResult;
    }


    public static String jsonPost(String url, Map<String, String> headersMap, String jsonParam) {
        String strResult = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            if (jsonParam != null) {
                LOGGER.info("postJson参数:{}", jsonParam);
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                request.setEntity(entity);
            }
            //设置连接超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000).setSocketTimeout(3000).build();
            PoolingHttpClientConnectionManager conMgr = new PoolingHttpClientConnectionManager();
            //设置整个连接池最大连接数 根据自己的场景决定
            conMgr.setMaxTotal(200);
            //是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。
            //设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)，路由是对maxTotal的细分。
            //（目前只有一个路由，因此让他等于最大值）
            conMgr.setDefaultMaxPerRoute(conMgr.getMaxTotal());
            if (headersMap != null) {
                for (Map.Entry<String, String> header : headersMap.entrySet()) {
                    request.setHeader(header.getKey(), header.getValue());
                }
            }
            request.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(request);
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                strResult = EntityUtils.toString(response.getEntity(), "utf-8");
                LOGGER.info(url + "请求成功，返回：" + strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                //写入日志
                String errorMessage = "请求提交失败,状态码:" + response.getStatusLine().getStatusCode() + "错误信息：" + EntityUtils.toString(response.getEntity(), "utf-8");
                LOGGER.info(url + errorMessage);
            }
        } catch (Exception e) {
            //写入日志
            String errorMessage = "请求提交失败,异常信息:" + url + e.getMessage();
            //失败原因
            LOGGER.info(url + errorMessage);
        } finally {
            try {
                // 关闭HTTP连接
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return strResult;
    }

    /**
     * 海尔应用市场接口调用工具类
     *
     * @param url       地址
     * @param jsonParam body
     * @return 数据
     */
    public static String post(String url, String jsonParam) {
        String strResult = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            if (jsonParam != null) {
                LOGGER.info("postJson参数:{}", jsonParam);
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                request.setEntity(entity);
            }
            //设置连接超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(10000).build();
            PoolingHttpClientConnectionManager conMgr = new PoolingHttpClientConnectionManager();
            //设置整个连接池最大连接数 根据自己的场景决定
            conMgr.setMaxTotal(200);
            //是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。
            //设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)，路由是对maxTotal的细分。
            //（目前只有一个路由，因此让他等于最大值）
            conMgr.setDefaultMaxPerRoute(conMgr.getMaxTotal());
            //头部信息
            request.setHeader("Content-type", CONTENT_TYPE);
            request.setHeader("Accept", ACCEPT);
            request.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(request);
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                strResult = EntityUtils.toString(response.getEntity(), "utf-8");
                LOGGER.info(url + "请求成功，返回：\n" + strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else if(response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT){
                LOGGER.info(url + "请求成功，返回状态码:" + response.getStatusLine().getStatusCode());
            } else {
                //写入日志
                String errorMessage = "请求提交失败,状态码:" + response.getStatusLine().getStatusCode() + "错误信息：" + EntityUtils.toString(response.getEntity(), "utf-8");
                LOGGER.info("haierPost_fail--" + url + "," + jsonParam + "\n" + errorMessage);
            }
        } catch (Exception e) {
            //写入日志
            String errorMessage = "请求提交失败,异常信息:" + url + e.getMessage();
            //失败原因
            LOGGER.info("haierPost_fail--" + url + "," + jsonParam + "\n" + errorMessage);
        } finally {
            try {
                // 关闭HTTP连接
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return strResult;
    }
    /**
     * 将Map类型的参数转换成HttpRequest请求参数
     *
     * @param params
     *            map类型的参数
     * @return
     */
    private static List<NameValuePair> convertMapToNameValuePair(Map<String, String> params) {
        List<NameValuePair> nvls = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> param : params.entrySet()) {
            nvls.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        return nvls;
    }

    public static String getUserIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isNotBlank(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Real-IP");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getRemoteAddr();
        return ip;
    }
    /**
     * 仅仅判断ip是否有效
     * @param ip
     * @return
     */
    private static boolean isIpValid(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        String[] split = ip.split("\\.");
        if (split.length != 4) {
            return false;
        }
        try {
            long first = Long.valueOf(split[0]);
            long second = Long.valueOf(split[1]);
            long third = Long.valueOf(split[2]);
            long fourth = Long.valueOf(split[3]);
            return first < 256 && first > 0
                    && second < 256 && second >= 0
                    && third < 256 && third >= 0
                    && fourth < 256 && fourth >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
