package com.cc.app.util;

public class Log {
	public static boolean isDebug = false;
	
	private static final String tag = "cframeinfo";

	public static void v(String msg) {
		if (isDebug)
			android.util.Log.v(tag, getMsg(msg));
	}

	public static void v( String msg, Throwable t) {
		if (isDebug)
			android.util.Log.v(tag, getMsg(msg), t);
	}

	public static void d( String msg) {
		if (isDebug)
			android.util.Log.d(tag, getMsg(msg));
	}

	public static void d( String msg, Throwable t) {
		if (isDebug)
			android.util.Log.d(tag, getMsg(msg), t);
	}

	public static void i(String msg) {
		if (isDebug)
			android.util.Log.i(tag, getMsg(msg));
	}

	public static void i( String msg, Throwable t) {
		if (isDebug)
			android.util.Log.i(tag, getMsg(msg), t);
	}

	public static void w( String msg) {
		if (isDebug)
			android.util.Log.w(tag, getMsg(msg));
	}

	public static void w(String msg, Throwable t) {
		if (isDebug)
			android.util.Log.w(tag, getMsg(msg), t);
	}

	public static void e(String msg) {
		if (isDebug)
			android.util.Log.e(tag, getMsg(msg));
	}

	public static void e(String msg, Throwable t) {
		if (isDebug)
			android.util.Log.e(tag, getMsg(msg), t);
	}
	
	private static String getMsg(String msg){
		StackTraceElement ste = new Throwable().getStackTrace()[1];
    	StringBuilder sb=new StringBuilder();
    	sb.append(ste.getFileName()).append(" ")
    	  .append("Line:").append(ste.getLineNumber()).append(" ")
    	  .append("Msg:").append(msg);
    	return sb.toString();
	}
}
