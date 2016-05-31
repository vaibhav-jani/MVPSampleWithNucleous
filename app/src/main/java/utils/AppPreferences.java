package utils;

import android.content.Context;
import android.content.SharedPreferences;


public class AppPreferences {

    private static final String PREFERENCE_FILE_NAME = "application_preferences";

    public static final String USERNAME = "username";

    public static final String USERID = "userid";

    public static final String PASSWORD = "password";

    public static final String REMEMBER_ME = "remember_me";
    public static final String NEXT_ORDER_ID = "nextOrderId";
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String NEXT_CONTACT_ID = "next_contact_id";
    public static final String NEXT_EVENT_ID = "next_event_id";
    public static final String SECURITY_PIN = "security_pin";

    private static SharedPreferences getSharedPreferences(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, 0);

        return sharedPreferences;
    }

    private static SharedPreferences.Editor getSharedPreferencesEditor(Context context) {

        return getSharedPreferences(context).edit();
    }

    public static boolean getBoolean(String key, boolean defaultValue, Context context) {

        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value, Context context) {

        getSharedPreferencesEditor(context).putBoolean(key, value).commit();
    }

    public static int getInt(String key, int defaultValue, Context context) {

        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static void setInt(String key, int value, Context context) {

        getSharedPreferencesEditor(context).putInt(key, value).commit();
    }

    public static String getString(String key, String defaultValue, Context context) {

        return getSharedPreferences(context).getString(key, defaultValue);
    }

    public static void setStringEncrypted(String key, String value, Context context) {

        value = EncryptionUtils.encrypt(value, context);

        getSharedPreferencesEditor(context).putString(key, value).commit();
    }


    public static String getStringEncrypted(String key, String defaultValue, Context context) {

        SharedPreferences prefs = getSharedPreferences(context);
        if(prefs.contains(key)) {

            String value = prefs.getString(key, defaultValue);
            return EncryptionUtils.decrypt(value, context);
        } else {

            return defaultValue;
        }
    }

    public static void setString(String key, String value, Context context) {

        getSharedPreferencesEditor(context).putString(key, value).commit();
    }


    public static float getFloat(String key, float defaultValue, Context context) {

        return getSharedPreferences(context).getFloat(key, defaultValue);
    }

    public static void setFloat(String key, float value, Context context) {

        getSharedPreferencesEditor(context).putFloat(key, value).commit();
    }

    public static long getLong(String key, long defaultValue, Context context) {

        return getSharedPreferences(context).getLong(key, defaultValue);
    }

    public static void setLong(String key, long value, Context context) {

        getSharedPreferencesEditor(context).putLong(key, value).commit();
    }

    public static void clearAllPreferences(Context context) {

        String username = getString(USERNAME, "", context);
        String password = getString(PASSWORD, "", context);
        boolean rememberMe = getBoolean(REMEMBER_ME, false, context);

        getSharedPreferencesEditor(context).clear().commit();

        if(rememberMe) {

            setString(USERNAME, username, context);
            setString(PASSWORD, password, context);
            setBoolean(REMEMBER_ME, true, context);
        }

    }

    /*public static void saveUserInfo(UserBean userInfo, Context context) {

        Gson gson = new Gson();

        //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String jsonObject = gson.toJson(userInfo);

        setString(USER_INFO, jsonObject, context);
    }

    public static UserBean getUserInfo(Context context) {

        //Gson gson = new Gson();

        Gson gson = AppGSonBuilder.getInternal();

        String json = getString(USER_INFO, "", context);

        UserBean userInfo = null;
        if(!StringUtils.isEmpty(json)) {

            userInfo = gson.fromJson(json, UserBean.class);
        }

        return userInfo;
    }*/

}
