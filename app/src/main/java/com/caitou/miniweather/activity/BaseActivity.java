package com.caitou.miniweather.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.caitou.miniweather.PermissionListener;
import com.caitou.miniweather.AppManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: BaseActivity
 * @classDescription: Activity基类
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-13.
 */

public class BaseActivity extends AppCompatActivity{

    // 申请权限结果回调
    private static PermissionListener mPermissionListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
    }

    public void exitApp() {
        AppManager.getInstance().exit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppManager.getInstance().finishActivity(this);
    }

    /**
     * 申请运行时权限
     * @param permissions - 权限集合
     * @param listener - 申请权限结果回调
     */
    public static void requestRuntimePermission(String[] permissions, PermissionListener listener) {
        if (permissions == null || permissions.length <= 0)
            return;
        Activity currentActivity = AppManager.getInstance().currentActivity();
        if (currentActivity == null)
            return;
        mPermissionListener = listener;

        List<String> permissionList = new ArrayList<>();
        try {
            // 检查权限
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(currentActivity, permission) !=
                        PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission);
                }
            }
            // 请求权限
            if (!permissionList.isEmpty()) {
                ActivityCompat.requestPermissions(currentActivity,
                        permissionList.toArray(new String[permissionList.size()]), 1);
            }else {
                // 全部都已经授权
                if (mPermissionListener != null) {
                    mPermissionListener.onGranted();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0) {
                List<String> deniedPermissions = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        deniedPermissions.add(permissions[i]);
                    }
                }

                // 用户已授权
                if (deniedPermissions.isEmpty()) {
                    mPermissionListener.onGranted();
                } else {
                    // 用户已拒绝的权限
                    mPermissionListener.onDenied(deniedPermissions);
                }
            }
        }
    }

    /**
     * 启动新的Activity，不带参数
     * @param context
     * @param classic
     */
    public void launch(Class<? extends Activity> classic) {
        Intent intent = new Intent();
        intent.setClass(this, classic);
        startActivity(intent);
    }

    /**
     * 启动新的Activity，带bundle
     * @param classic
     * @param bundle
     */
    public void launch(Class<? extends Activity> classic, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(this, classic);
        startActivity(intent);
    }

    /**
     * 启动新的Activity，带参数
     * @param classic
     * @param request
     */
    public void launch(Class<? extends Activity> classic, int request) {
        Intent intent = new Intent();
        intent.setClass(this, classic);
        startActivityForResult(intent, request);
    }
}
