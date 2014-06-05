package com.cc.app.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.cc.app.AppManager;
import com.cc.app.R;

/**
 * ui相关操作
 * @author wenchao
 *
 */
public class UIHelper {
	/**
	 * 发送错误报告
	 * @param context
	 */
	public static void showExceptionDialog(final Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_error);
		builder.setMessage(R.string.app_error_message);
		builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//发送异常报告
				//退出
				AppManager.getAppManager().appExit(context);
				System.exit(0);
			}
		});
		builder.setCancelable(false);
		builder.show();
	}
}
