package com.cc.app.http;

import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * http 帮助类 此类中封装了http请求函数
 * 
 * @author Administrator
 * 
 */
public class CHttpClient {
	/** http请求队列 */
	private RequestQueue mRequestQueue;
	private Context mContext;

	public CHttpClient(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
		this.mContext = context;
	}

	public void getString(String url) {
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {

			}
		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

			}
		};
		Request<String> stringRequest = new StringRequest(Method.GET, url,
				listener, errorListener);
		//设置默认超时时间
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(
				DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		stringRequest.setTag(url);
		mRequestQueue.add(stringRequest);
	}

	public void getJsonObject(String url) {

		Listener<JSONObject> listener = new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub

			}

		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		};
		Request<JSONObject> JsonObjectRequest = new JsonObjectRequest(url,
				null, listener, errorListener);
		JsonObjectRequest.setTag(url);
		mRequestQueue.add(JsonObjectRequest);
	}

	public void addBitmapRequest(String url, int maxWidth, int maxHeight,
			Config decodeConfig) {
		Listener<Bitmap> listener = new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				// TODO Auto-generated method stub

			}

		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}

		};

		Request<Bitmap> bitmapRequest = new ImageRequest(url, listener,
				maxWidth, maxHeight, decodeConfig, errorListener);
		bitmapRequest.setTag(url);
		mRequestQueue.add(bitmapRequest);
	}

	public void cancelAll() {
		mRequestQueue.cancelAll(mContext);
	}

	public void cancel(String tag) {
		mRequestQueue.cancelAll(tag);
	}
}
