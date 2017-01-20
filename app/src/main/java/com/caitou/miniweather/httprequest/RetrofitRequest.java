package com.caitou.miniweather.httprequest;

import com.caitou.miniweather.AppConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @className: RetrofitRequest
 * @classDescription: 网络请求工具类
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-20.
 */

public class RetrofitRequest {

    private Retrofit mRetrofit;

    private static RetrofitRequest instance;

    private RetrofitRequest() {}

    public static RetrofitRequest getInstance() {
        if (instance == null)
            instance = new RetrofitRequest();
        return instance;
    }

    public <T> T create(Class<T> service) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(service);
    }
}
