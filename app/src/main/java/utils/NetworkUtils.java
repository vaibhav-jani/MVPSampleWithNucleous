package utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.vaibhav.mvpsample.R;


public class NetworkUtils {

    public static boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null
                && netInfo.isConnectedOrConnecting()
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {

            return true;
        }

        return false;
    }

    public static boolean checkInternetConnection(Context context) {

        if(!NetworkUtils.isOnline(context)) {

            if(context instanceof Activity) {

                String message = context.getString(R.string.internet_off);
                Notify.dialogOK(message, (Activity)context, false);

            } else {

                //Notify.toast(message, context);
            }

            return false;
        }

        return true;
    }

}
