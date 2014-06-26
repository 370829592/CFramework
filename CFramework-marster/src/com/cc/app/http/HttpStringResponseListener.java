package com.cc.app.http;

import com.android.volley.VolleyError;

/**
 * http响应监听器
 * @author Administrator
 *
 */
public interface HttpStringResponseListener {
	
	/**正确响应,返回字符串数据*/
	public void onResponse(String response);
	
	/**错误响应*/
	public void onErrorResponse(VolleyError error);
	
	
}
