package com.cc.app.api;

import android.content.Context;

import com.umeng.update.UmengUpdateAgent;

public class UpdateUtils {
	/**
	 * 检查更新;后续操作都由 sdk提供
	 * @param context
	 */
	public static void check(Context context) {
		UmengUpdateAgent.update(context);
		UmengUpdateAgent.setUpdateOnlyWifi(false);
	}
}
