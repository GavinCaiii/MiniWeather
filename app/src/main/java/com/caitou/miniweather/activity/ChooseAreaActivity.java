package com.caitou.miniweather.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caitou.miniweather.R;

/**
 * @className: ChooseAreaActivity
 * @classDescription: 选择城市
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-20.
 */

public class ChooseAreaActivity extends BaseActivity {
    private Toolbar mToolbar;

    private TextView mChooseCityTv;
    private LinearLayout mFindCityLl;
    private TextView mTitleTv;
    private RecyclerView mAllCityRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);
        findViewById();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        // 隐藏原本的title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void findViewById() {
        mToolbar = (Toolbar) findViewById(R.id.area_toolbar);

        mChooseCityTv = (TextView) findViewById(R.id.area_tv_all_city);
        mChooseCityTv.setOnClickListener(onClickListener);
        mFindCityLl = (LinearLayout) findViewById(R.id.area_ll_find_city);
        mTitleTv = (TextView) findViewById(R.id.area_tv_title);
        mAllCityRv = (RecyclerView) findViewById(R.id.area_rv_all_city);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.area_tv_all_city:
                    mFindCityLl.setVisibility(View.GONE);
                    mAllCityRv.setVisibility(View.VISIBLE);
                    mTitleTv.setText(getString(R.string.add_city));
                    closeKeyboard();
                    break;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mFindCityLl.getVisibility() == View.GONE) {
                mTitleTv.setText(getString(R.string.find_city));
                mFindCityLl.setVisibility(View.VISIBLE);
                mAllCityRv.setVisibility(View.GONE);
            } else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mFindCityLl.getVisibility() == View.GONE) {
            mTitleTv.setText(getString(R.string.find_city));
            mFindCityLl.setVisibility(View.VISIBLE);
            mAllCityRv.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
