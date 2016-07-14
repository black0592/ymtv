package com.lemon95.ymtv.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;


public class AppSystemUtils {

	public static String getVersionName(Context context) {
		return getPackageInfo(context).packageName;
	}

	public static int getVersionCode(Context context) {
		return getPackageInfo(context).versionCode;
	}


	private static PackageInfo getPackageInfo(Context context) {
		PackageInfo pi = null;
		try {
			PackageManager pm = context.getPackageManager();
			pi = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
			return pi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pi;
	}

	/**
	 * 获取application中指定的meta-data
	 * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
	 */
	public static String getAppMetaData(Context ctx, String key) {
		if (ctx == null || TextUtils.isEmpty(key)) {
			return null;
		}
		String resultData = null;
		try {
			PackageManager packageManager = ctx.getPackageManager();
			if (packageManager != null) {
				ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
				if (applicationInfo != null) {
					if (applicationInfo.metaData != null) {
							resultData = applicationInfo.metaData.getString(key);
							if (resultData == null) {
								resultData = applicationInfo.metaData.getInt(key) + "";
							}
					}
				}

			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return resultData;
	}


}
