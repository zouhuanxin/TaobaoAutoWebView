package com.zhx.taobaoautowebview.util.accessutil;

import android.util.Log;

public class AccessibilityLog {

    private static final String TAG = "AccessibilityService";
    public static void printLog(String logMsg) {
        Log.d(TAG, logMsg);
    }
}
