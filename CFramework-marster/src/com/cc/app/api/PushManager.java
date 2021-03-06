package com.cc.app.api;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.cc.app.AppConfig;
import com.umeng.message.PushAgent;

public class PushManager {
	public static final String TAG = "PushManager";

	private static PushManager myInstance;

	private PushAgent mPushAgent;

	private static final int MSG_ADD_TAGS = 1;
	private static final int MSG_CLEAR_TAGS = 2;
	private Handler mHandler;

	private PushManager(Context ctx) {
		mPushAgent = PushAgent.getInstance(ctx);
	}

	public static PushManager getInstance(Context ctx) {
		if (myInstance == null) {
			myInstance = new PushManager(ctx);
		}
		return myInstance;
	}

	/**
	 * 初始化
	 */
	public void init() {
		// 开启推送服务
		mPushAgent.setDebugMode(AppConfig.debug);
		/**
		 * 在应用的主Activity onCreate() 函数中开启推送服务 注意: 如果你的应用继承了Application,
		 * 不要在Application onCreate() 中调用 mPushAgent.enable();. 由于SDK 设计的逻辑， 这会造成循环。
		 */
		mPushAgent.enable();
	}
	/**
	 * 在所有的Activity 的onCreate 函数添加
	 */
	public void onAppStart(){
		mPushAgent.onAppStart();
	}

	/**
	 * 来关闭客户端的通知服务。
	 */
	public void close() {
		if (isEnabled()) {
			mPushAgent.disable();
		}
	}

	/**
	 * 状态表示有没有启用/关闭推送功能， 不表示推送后台服务的运行状态。，
	 * 
	 * @return
	 */
	public boolean isEnabled() {
		return mPushAgent.isEnabled();
	}




	/**
	 * 清除所有的标签
	 */
	private void clearTags() {
		try {
			mPushAgent.getTagManager().reset();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 为免过度打扰用户，SDK默认在“23:00”到“7:00”之间收到通知消息时不响铃，不振动，不闪灯。如果需要改变默认的静音时间，可以使用以下接口：
	 * 例如：mPushAgent.setNoDisturbMode(23, 0, 7, 0);
	 * 
	 * @param startHour
	 * @param startMinute
	 * @param endHour
	 * @param endMinute
	 */
	public void setNoDisturbMode(int startHour, int startMinute, int endHour,
			int endMinute) {
		mPushAgent.setNoDisturbMode(startHour, startMinute, endHour, endMinute);
	}

	/**
	 * 添加用户标签 , 在本类中包含类型
	 * 
	 * @param tags
	 *            标签种类
	 * @throws Exception
	 */
	private void addTags(final String[] tags) {
		try {
			mPushAgent.getTagManager().add(tags);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 确定用户类型， 给用户加推送标签
	 * 
	 * @param userType
	 *            若为null，则清空tags
	 */
	public void setUserTags(String... tags) {
		if (tags == null || tags.length == 0)
			return;

		initHandler();
		mHandler.sendMessage(mHandler.obtainMessage(MSG_CLEAR_TAGS));
		mHandler.sendMessage(mHandler.obtainMessage(MSG_ADD_TAGS, tags));
	}

	private void initHandler() {
		if (mHandler == null) {
			HandlerThread handlerThread = new HandlerThread(
					"PushManager handler");
			handlerThread.start();
			mHandler = new Handler(handlerThread.getLooper()) {
				public void handleMessage(android.os.Message msg) {
					switch (msg.what) {
					case MSG_ADD_TAGS:
						String[] tags = (String[]) msg.obj;
						addTags(tags);
						break;

					case MSG_CLEAR_TAGS:
						clearTags();
						break;
					}
				};
			};
		}
	}

}
