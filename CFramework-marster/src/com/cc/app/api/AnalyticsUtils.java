package com.cc.app.api;

import android.content.Context;

import com.cc.app.AppConfig;
import com.umeng.analytics.MobclickAgent;

/**
 * 友盟统计
 * 
 * @author Administrator
 * 
 */
public class AnalyticsUtils {
	/**
	 * 友盟点击事件，
	 * 
	 * @param context
	 * @param eventId
	 *            事件id，可以从友盟后台查看对应事件的Id,事件id一定不能弄错
	 */
	public static void onClickEvent(Context context, String eventId) {
		MobclickAgent.onEvent(context, eventId);
	}

	/**
	 * 在Activity的onResume中调用
	 * @param context
	 */
	public static void onResume(Context context) {
		MobclickAgent.onResume(context);
	}

	/**
	 * 在Activity的onPause中调用
	 * @param context
	 */
	public static void onPause(Context context){
		MobclickAgent.onPause(context);
	}
	
	/**
	 * 初始化
	 * @param context
	 */
	public static void init(Context context) {
		MobclickAgent.setDebugMode(AppConfig.debug);
		// 禁止默认的页面统计方式，这样将不会再自动统计Activity
		MobclickAgent.openActivityDurationTrack(true);
		MobclickAgent.updateOnlineConfig(context);
	}
	
	public interface Event{
		public static final String CLICK_BUTTON = "001";
	}
}
