package com.caitou.miniweather.utils;

import android.content.Context;
import android.widget.Toast;

import com.caitou.miniweather.AppContext;


/**
 * @className : ToastUtil.java
 * @classDescription : 提示工具类
 * @author : AIDAN SU
 * @createTime : 2014-1-3
 *
 */
public class ToastUtil {

	/**
	 * 提示信息的调用方法
	 *
	 * @param msg 提示信息
	 */
	public static void toastShow(String msg) {
		toastShow(AppContext.getInstance().getApplicationContext(), msg);
	}

	/**
	 * 提示信息的调用方法
     *
	 * @param context 上下文
	 * @param msg 提示信息
	 */
	public static void toastShow(Context context,String msg){
		if(msg != null && context!=null){
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}
}
