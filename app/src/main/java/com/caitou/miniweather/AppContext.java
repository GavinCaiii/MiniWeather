package com.caitou.miniweather;

import android.app.Application;

/**
 * @className: AppContext
 * @classDescription: app全局应用配置
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-13.
 */

public class AppContext extends Application {

    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
