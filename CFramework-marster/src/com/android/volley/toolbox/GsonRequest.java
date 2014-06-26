package com.android.volley.toolbox;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

/**
 * 添加Gson请求支持，需要时可添加
 * 首先我们需要把gson的jar包添加到项目当中，jar包的下载地址是：https://code.google.com/p/google-gson/downloads/list 。
 * 此处未写完成
 * @author Administrator
 *
 * @param <T>
 */
public class GsonRequest<T> extends Request<T> {
	private Listener<T> mListener;

//	private Gson mGson;
	private Class<T> mClass;
	
	public GsonRequest(int method, String url,Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.mListener = listener;
	}
	
	public GsonRequest(String url,Listener<T> listener, ErrorListener errorListener) {
		super(Method.GET, url, errorListener);
		this.mListener = listener;
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String gsonString = new String(response.data,HttpHeaderParser.parseCharset(response.headers));
//			return Response.success(mGson.fromJson(gsonString, mClass),  
//                    HttpHeaderParser.parseCacheHeaders(response)); 
			return null;
			
		} catch (UnsupportedEncodingException e) {
			 return Response.error(new ParseError(e));  
		}
	}

	@Override
	protected void deliverResponse(T response) {
		// TODO Auto-generated method stub
		mListener.onResponse(response);
	}

}
