package utils;

import android.content.Context;
import android.util.Log;

public class Logger {

    private static final boolean shouldLog = true;

    public static void i(String tag, String msg) {

        if(shouldLog) {

            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {

        if(shouldLog) {

            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {

        if(shouldLog) {

            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {

        if(shouldLog) {

            Log.v(tag, msg);
        }
    }

    public static void e(Throwable e) {

        if(shouldLog) {

            e.printStackTrace();
        }
    }

    public static void toast(String string, Context context) {

        if(shouldLog) {

            Notify.toast(string, context);
        }
    }
}
