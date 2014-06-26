package com.cc.app.ui.base;

import com.cc.app.AppManager;
import com.cc.app.api.AnalyticsUtils;
import com.cc.app.api.PushManager;

import android.app.Activity;
import android.os.Bundle;

/**
 * 应用程序Acivity的基类
 * @author wenchao
 *
 */
public class BaseActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
		PushManager.getInstance(this).onAppStart();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		AnalyticsUtils.onResume(this);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		AnalyticsUtils.onPause(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//结束Activity & 从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}
}	
