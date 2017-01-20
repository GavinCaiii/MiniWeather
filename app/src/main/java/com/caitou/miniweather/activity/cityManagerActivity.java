package com.caitou.miniweather.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.caitou.miniweather.R;
import com.caitou.miniweather.adapter.CityManagerAdapter;
import com.caitou.miniweather.adapter.CityManagerData;

import java.util.ArrayList;
import java.util.List;

/**
 * @className:
 * @classDescription:
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-16.
 */

public class CityManagerActivity extends BaseActivity{

    private Toolbar mToolbar;

    private FloatingActionButton mFab;

    private RecyclerView mRecycleView;
    private List<CityManagerData> mDataList;
    private CityManagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        // 实例化控件
        findViewById();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("城市管理");
        }

        // 初始化数据
        setData();
        initRecycleView();
    }

    // 实例化控件
    private void findViewById() {
        mToolbar = (Toolbar) findViewById(R.id.activity_city_manager_toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.activity_city_manager_fab);
        mRecycleView = (RecyclerView) findViewById(R.id.activity_city_manager_rv);

        mFab.setOnClickListener(onClickListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecycleView() {
        // 线性管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 初始化布局
        mRecycleView.setLayoutManager(layoutManager);
        // 设置adapter
        mAdapter = new CityManagerAdapter(this, mDataList, new CityManagerAdapter.ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                launch(MainActivity.class);
                finish();
            }
        });
        mRecycleView.setAdapter(mAdapter);
        // 添加动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setData() {
        mDataList = new ArrayList<>();
        CityManagerData data = new CityManagerData();
        for (int i = 0; i < 5; i++) {
            data.setCityName("广州");
            data.setTemp("15");
            data.setWeather("多云");
            mDataList.add(data);
        }
        CityManagerData data1 = new CityManagerData();
        for (int i = 0; i < 5; i++) {
            data1.setCityName("天河");
            data1.setTemp("20");
            data1.setWeather("小雨");
            mDataList.add(data1);
        }
        CityManagerData data2 = new CityManagerData();
        for (int i = 0; i < 5; i++) {
            data2.setCityName("雷州");
            data2.setTemp("10");
            data2.setWeather("晴天");
            mDataList.add(data2);
        }
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            launch(ChooseAreaActivity.class);
//            Snackbar.make(view, "点击了fab", Snackbar.LENGTH_SHORT).setAction("undo", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ToastUtil.toastShow("nothing to do");
//                }
//            }).show();
        }
    };
}
