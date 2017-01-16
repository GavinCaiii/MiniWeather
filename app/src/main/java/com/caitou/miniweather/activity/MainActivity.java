package com.caitou.miniweather.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.caitou.miniweather.R;
import com.caitou.miniweather.utils.ToastUtil;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 实例化控件
        findViewById();
        // 隐藏原本的title
//        mToolbar.setTitle("");
        // 把toolbar设置到actionBar中
        setSupportActionBar(mToolbar);
        // 隐藏原本的title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // 原生的toolbar侧滑导航
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 设置navigationView的点击事件
        setNavigationViewClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_menu_action_settings:
                ToastUtil.toastShow(MainActivity.this, "setting...");
                break;
            case R.id.toolbar_menu_action_share:
                ToastUtil.toastShow(MainActivity.this, "sharing...");
                break;
            case R.id.toolbar_menu_action_exit:
                ToastUtil.toastShow(MainActivity.this, "exiting");
                break;
        }

        return true;

    }

    private void findViewById() {
        mToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.activity_main_navigation_view);
    }

    private void setNavigationViewClick() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_city_manager:
//                        ToastUtil.toastShow(MainActivity.this, "城市管理");
                        ToastUtil.toastShow("城市管理");
                        break;
                    case R.id.nav_menu_theme_skin:
                        ToastUtil.toastShow(MainActivity.this, "主题皮肤");
                        break;
                    case R.id.nav_menu_check_update:
                        ToastUtil.toastShow(MainActivity.this, "检查更新");
                        break;
                    case R.id.nav_menu_setting:
                        ToastUtil.toastShow(MainActivity.this, "设置");
                        break;
                    case R.id.nav_menu_about:
                        ToastUtil.toastShow(MainActivity.this, "关于");
                        break;
                }
                // 点击一次后取消选中效果并且关闭侧滑栏
                item.setCheckable(false);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}
