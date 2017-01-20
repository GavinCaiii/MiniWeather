package com.caitou.miniweather.adapter;

/**
 * @className:
 * @classDescription:
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-19.
 */

public class CityManagerData {

    private String cityName;
    private String weather;
    private String temp;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getCityName() {
        return cityName;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemp() {
        return temp;
    }
}
