package com.cc.app.ui;

import android.graphics.Color;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.cc.app.AppManager;
import com.cc.app.R;
import com.cc.app.api.AnalyticsUtils;
import com.cc.app.api.PushManager;
import com.cc.app.api.UpdateUtils;
import com.cc.app.http.CHttpClient;
import com.cc.app.http.HttpStringResponseListener;
import com.cc.app.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//初始化sdk
		PushManager.getInstance(this).init();
		AnalyticsUtils.init(this);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		UpdateUtils.check(this);
		
		
		ImageView iv = (ImageView)findViewById(R.id.iv_head);

		CHttpClient client = new CHttpClient(this);
		client.getString("http://www.baidu.com", new HttpStringResponseListener() {
			
			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				System.out.println(response);
			}
			
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				System.out.println(error);
			}
		});
		client.loadBitmap(iv, "http://img0.bdstatic.com/img/image/shouye/bzxgmzfb.jpg");
		
		TextView tv = (TextView)findViewById(R.id.tv_01);
		TextPaint tp = tv.getPaint();
		tp.setStrokeWidth(3);
		tp.setStyle(Style.FILL_AND_STROKE);
		tp.setFakeBoldText(true);
		tp.setColor(Color.parseColor("#ffff00"));
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		AppManager.getAppManager().appExit(this);
	}

}
