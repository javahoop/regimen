package com.wsd.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsd.infrastructure.constants.Constants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

/**
 * http请求工具类
 * @author wsd
 * @date 2021/6/14
 */
@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtils {

    private static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();
    /**
     * 设置超时时间为2秒
     */
    private static final Duration TIME_OUT = Duration.ofSeconds(2L);
    /**
     * http客户端
     */
    private static HttpClient httpClient;

    static {
        // 设置超时时间
        httpClient = HttpClient.newBuilder()
                .connectTimeout(TIME_OUT)
                .build();
    }
    /**
     * get操作
     * @param url 请求地址
     */
    public static String get(String url) throws IOException, InterruptedException {
        log.debug("get_url:" + url);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        log.debug("get_response:" + httpResponse.body());
        return httpResponse.body();
    }
    /**
     * post操作
     * @param url 请求地址
     * @param data 请求数据
     * @param contentType 请求协议
     */
    public static String post(String url, Map<String, Object> data, String contentType) throws IOException, InterruptedException {
        String body = "";
        if (Constants.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType)) {
            // application/x-www-form-urlencoded请求类型
            body = mapToQuery(data);
        } else if (Constants.APPLICATION_JSON_VALUE.equals(contentType)) {
            // application/json请求类型
            body = OBJECT_MAPPER.writeValueAsString(data);
        }
        log.debug("post_url:" + url);
        log.debug("post_body:" + body);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url))
                .header("Content-Type", contentType)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        log.debug("post_response:" + httpResponse.body());
        return httpResponse.body();
    }

    /**
     * post操作 默认json格式请求
     * @param url 请求地址
     * @param data 请求数据
     */
    public static String post(String url,Object data) throws IOException, InterruptedException {
        String body = "";
            // application/json请求类型
        body = OBJECT_MAPPER.writeValueAsString(data);
        log.debug("post_url:" + url);
        log.debug("post_body:" + body);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        log.debug("post_response:" + httpResponse.body());
        return httpResponse.body();
    }

    public static byte[] downloadFile(String url)throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<byte[]> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
        return httpResponse.body();
    }
    /**
     * map转换为query
     */
    private  static String mapToQuery(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            stringBuilder.append("&");
        }
        String result = stringBuilder.toString();
        return result.substring(0, result.lastIndexOf("&"));
    }
}
