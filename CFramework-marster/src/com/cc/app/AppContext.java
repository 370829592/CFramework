package com.cc.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * @author wenchao 
 */
public class AppContext extends Application{
	
	private static AppContext mApplication;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApplication = this;
		//注册App异常奔溃处理器
		Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
	}
	
	/**
	 * 全局单列 应用程序对象
	 * @return
	 */
	public static AppContext shareApplication(){
		return mApplication;
	}
	
	/**
	 * 获取安装包信息
	 * @return
	 */
	public PackageInfo getPackageInfo(){
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
		}
		if(info == null){
			info = new PackageInfo();
		}
		return info;
	}
}
