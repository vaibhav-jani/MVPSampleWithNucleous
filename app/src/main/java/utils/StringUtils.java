package utils;

import android.text.TextUtils;

public class StringUtils {

    public static boolean isEmpty(String text) {

        if(text == null) {

            return true;
        }

        return TextUtils.isEmpty(text.trim());
    }

    public static boolean isEmpty(CharSequence text) {

        if(text == null) {

            return true;
        }

        return isEmpty(text.toString());
    }
}
