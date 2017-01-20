package com.caitou.miniweather;

import java.util.List;

/**
 * @className: PermissionListener
 * @classDescription: 申请权限的回调接口
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-16.
 */

public interface PermissionListener {
    void onGranted();
    void onDenied(List<String> deniedPermissions);
}
