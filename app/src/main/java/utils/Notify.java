package utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.example.vaibhav.mvpsample.R;


public class Notify {

    public static void toast(int stringId, Activity Activity) {

        toast(Activity.getString(stringId), Activity);
    }

    public static void toast(String text, Context Activity) {

        if (StringUtils.isEmpty(text)) {

            return;
        }

        try {

            Toast.makeText(Activity, text, Toast.LENGTH_LONG).show();

        } catch (Exception e) {

            Logger.e(e);
        }
    }

    public static void dialogOK(String message, final Activity activity, final boolean finish) {

        if (StringUtils.isEmpty(message)) {

            return;
        }

        try {

            String strOk = activity.getString(R.string.ok);

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(message);
            builder.setPositiveButton(strOk, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    if (finish) {
                        activity.finish();
                    }
                }
            });
            builder.show();

        } catch (Exception e) {

            Logger.e(e);
        }
    }

    public static void dialogOK(int message, final Activity activity, final OnDialogDismiss onDialogDismiss) {

        dialogOK(activity.getString(message), activity, onDialogDismiss);
    }

    public static void dialogOK(String message, final Activity activity, final OnDialogDismiss onDialogDismiss) {

        try {

            String strOk = activity.getString(R.string.ok);

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setPositiveButton(strOk, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    if (onDialogDismiss != null) {
                        onDialogDismiss.onDialogDismiss(true);
                    }
                }
            });
            builder.show();

        } catch (Exception e) {

            Logger.e(e);
        }
    }

    public static void dialogYesNo(int message, final Activity activity, final OnDialogDismiss onDialogDismiss) {

        dialogYesNo(activity.getString(message), activity, onDialogDismiss);
    }

    public static void dialogYesNo(String message, final Activity activity, final OnDialogDismiss onDialogDismiss) {

        try {

            String strYes = activity.getString(R.string.yes);
            String strNo = activity.getString(R.string.no);

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(message);
            builder.setCancelable(false);

            builder.setPositiveButton(strYes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                    if (onDialogDismiss != null) {

                        onDialogDismiss.onDialogDismiss(true);
                    }
                }
            });

            builder.setNegativeButton(strNo, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                    if (onDialogDismiss != null) {

                        onDialogDismiss.onDialogDismiss(false);
                    }
                }
            });

            builder.show();

        } catch (Exception e) {

            Logger.e(e);
        }
    }

    public static void dialogOK(String message, Activity activity) {

        dialogOK(message, activity, false);
    }

    /*public static void dialogOK(int message, Activity activity) {

        dialogOK(activity.getString(message), activity, false);
    }

    public static void dialogOK(int message, Activity activity, boolean finish) {

        dialogOK(activity.getString(message), activity, finish);
    }*/

    public static void showSnackBar(View view, String text, Activity activity) {

        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackBar(View view, int text, Activity activity) {

        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

    /*public static void showSnackBar(String text, Activity activity) {

        try {

            View view = activity.getCurrentFocus();
            Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();

        } catch (Exception e) {

            Logger.e(e);
        }
    }*/

    public static interface OnDialogDismiss {

        public void onDialogDismiss(boolean isPositive);

    }
}
