package com.rhw.learning.utils;

import android.util.Log;

import com.rhw.learning.BuildConfig;

/**
 * Date:2017/11/28 on 13:32
 * @author Simon
 */

public class LogUtils {
    private final static boolean DEBUG = BuildConfig.DEBUG;

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, checkMsg(msg));
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.v(tag, checkMsg(msg), tr);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, checkMsg(msg));
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.d(tag, checkMsg(msg), tr);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, checkMsg(msg));
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.i(tag, checkMsg(msg), tr);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, checkMsg(msg));
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.w(tag, checkMsg(msg), tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (DEBUG) {
            Log.w(tag, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, checkMsg(msg));
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.e(tag, checkMsg(msg), tr);
        }
    }

    private static String checkMsg(String msg) {
        return msg == null ? "null" : msg;
    }
}
