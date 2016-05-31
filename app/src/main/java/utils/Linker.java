package utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Linker {

    public static void makePhoneNumberLink(final TextView textView) {

        //Linkify.addLinks(textView, Linkify.PHONE_NUMBERS);

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {

                    Context context = textView.getContext();

                    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    if (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
                        //No calling functionality
                        return;
                    }

                    String phone = textView.getText().toString();

                    call(phone, context);

                } catch (Exception e) {
                    Logger.e(e);
                }
            }
        });

    }

    public static void call(String phone, Context context) {

        try {

            if (phone != null && phone.length() > 2) {

                Intent dial = new Intent();
                dial.setAction(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:" + phone));
                context.startActivity(dial);
            }

        } catch (Exception e) {

            Logger.e(e);
        }
    }

    public static void makeMapLink(final TextView textView, final String searchFormattedAddress) {

        //Linkify.addLinks(textView, Linkify.PHONE_NUMBERS);

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Context context = textView.getContext();

                    String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%s", searchFormattedAddress);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    context.startActivity(intent);

                } catch (Exception e) {
                    Logger.e(e);
                }
            }
        });

    }
}
