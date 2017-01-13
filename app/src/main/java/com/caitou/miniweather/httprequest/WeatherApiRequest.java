package com.caitou.miniweather.httprequest;

import com.caitou.miniweather.AppConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @className: WeatherApiRequest
 * @classDescription: 天气api网络请求
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-13.
 */

public class WeatherApiRequest {

    private Retrofit mRetrofit;

    private static WeatherApiRequest instance;

    private WeatherApiRequest() {}

    public static WeatherApiRequest getInstance() {
        if (instance == null)
            instance = new WeatherApiRequest();
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
