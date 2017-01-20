package com.caitou.miniweather.httprequest.utils;

/**
 * @className:
 * @classDescription:
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-20.
 */

public class HttpRequest {
    private static HttpRequest instance;

    private HttpRequest() {}

    public HttpRequest getInstance() {
        if (instance == null)
            instance = new HttpRequest();
        return instance;
    }

    public void get() {

    }
}
