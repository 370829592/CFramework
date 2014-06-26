package com.cc.app.http;

import org.json.JSONObject;

import com.android.volley.VolleyError;

public interface HttpJsonResponseListener {
	/**返回json数据*/
	public void onResponse(JSONObject object);
	
	/**错误响应*/
	public void onErrorResponse(VolleyError error);
}
