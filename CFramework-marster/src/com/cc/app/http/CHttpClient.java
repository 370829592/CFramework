package com.cc.app.http;

import java.util.Map;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cc.app.R;

/**
 * http 帮助类 此类中封装了http请求函数 还包含 bitmap
 * 
 * @author Administrator
 * 
 */
public class CHttpClient {
	/** http请求队列 */
	private RequestQueue mRequestQueue;
	private Context mContext;
	private ImageLoader mImageLoader;

	public CHttpClient(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
		this.mContext = context;
		mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
	}

	/**
	 * get方法获取字符串返回值
	 * 
	 * @param url
	 *            请求地址
	 * @param httpStringResponseListener
	 *            返回结果监听
	 */
	public void getString(String url,
			final HttpStringResponseListener httpStringResponseListener) {
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				if (httpStringResponseListener != null) {
					httpStringResponseListener.onResponse(response);
				}
			}
		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				if (httpStringResponseListener != null) {
					httpStringResponseListener.onErrorResponse(error);
				}
			}
		};
		Request<String> stringRequest = new StringRequest(Method.GET, url,
				listener, errorListener);
		// 设置默认超时时间
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(
				DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mRequestQueue.add(stringRequest);
	}

	/**
	 * post方式
	 * 
	 * @param url
	 * @param params
	 *            参数
	 * @param httpStringResponseListener
	 */
	public void postString(String url, final Map<String, String> params,
			final HttpStringResponseListener httpStringResponseListener) {
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				if (httpStringResponseListener != null) {
					httpStringResponseListener.onResponse(response);
				}
			}
		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				if (httpStringResponseListener != null) {
					httpStringResponseListener.onErrorResponse(error);
				}
			}
		};
		Request<String> stringRequest = new StringRequest(Method.POST, url,
				listener, errorListener) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}

		};
		// 设置默认超时时间
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(
				DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mRequestQueue.add(stringRequest);
	}

	/**
	 * get方式 请求 json数据
	 * 
	 * @param url
	 * @param httpJsonResponseListener
	 */
	public void getJsonObject(String url,
			final HttpJsonResponseListener httpJsonResponseListener) {

		Listener<JSONObject> listener = new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				if (httpJsonResponseListener != null) {
					httpJsonResponseListener.onResponse(response);
				}
			}

		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				if (httpJsonResponseListener != null) {
					httpJsonResponseListener.onErrorResponse(error);
				}
			}
		};
		Request<JSONObject> JsonObjectRequest = new JsonObjectRequest(url,
				null, listener, errorListener);
		mRequestQueue.add(JsonObjectRequest);
	}

	/**
	 * post方式
	 * 
	 * @param url
	 * @param params
	 * @param httpJsonResponseListener
	 */
	public void postJsonObject(String url, final Map<String, String> params,
			final HttpJsonResponseListener httpJsonResponseListener) {

		Listener<JSONObject> listener = new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				if (httpJsonResponseListener != null) {
					httpJsonResponseListener.onResponse(response);
				}
			}

		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				if (httpJsonResponseListener != null) {
					httpJsonResponseListener.onErrorResponse(error);
				}
			}
		};
		Request<JSONObject> JsonObjectRequest = new JsonObjectRequest(
				Method.POST, url, null, listener, errorListener) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				return params;
			}

		};
		mRequestQueue.add(JsonObjectRequest);
	}

	/**
	 * 加载图片 到ImageView中 建议使用NetworkImageView
	 * 
	 * @param imageView
	 * @param url
	 */
	public void loadBitmap(ImageView imageView, String url) {
		ImageListener listener = ImageLoader.getImageListener(imageView,
				R.drawable.ic_empty, R.drawable.ic_error);
		mImageLoader.get(url, listener);
	}
	/**
	 * 加载图片到NetworkImageView 中，
	 * @param networkImageView
	 * @param url
	 */
	public void loadBitmap(NetworkImageView networkImageView,String url){
		networkImageView.setImageUrl(url, mImageLoader);
		networkImageView.setErrorImageResId(R.drawable.ic_error);
		networkImageView.setDefaultImageResId(R.drawable.ic_empty);
	}

	/**
	 * 取消本队列所有网络请求
	 */
	public void cancelAll() {
		mRequestQueue.cancelAll(mContext);
	}

	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

	private class BitmapCache implements ImageCache {

		private LruCache<String, Bitmap> mCache;

		@SuppressLint("NewApi")
		public BitmapCache() {
			int maxSize = 10 * 1024 * 1024;
			mCache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap value) {
					// TODO Auto-generated method stub
					return value.getRowBytes() * value.getHeight();
				}
			};
		}

		@SuppressLint("NewApi")
		@Override
		public Bitmap getBitmap(String url) {
			// TODO Auto-generated method stub
			return mCache.get(url);
		}

		@SuppressLint("NewApi")
		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			// TODO Auto-generated method stub
			mCache.put(url, bitmap);
		}

	}

}
