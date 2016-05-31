package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by vaibhav on 26/3/16.
 */
public class DecimalFormatter {

    public static final String getFormat(double d) {

        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(d);

        if(format.startsWith(".")){

            format = "0"+format;
        }

        return format;
    }

    public static String formatAmount(String strAmount) {

        try {
            double amount = Double.parseDouble(strAmount);
        /*DecimalFormat formatter = new DecimalFormat("#,###.00");

        String result = formatter.format(amount);*/

            String result = "Rs. " + NumberFormat.getNumberInstance(Locale.US).format(amount);
            return result;
        } catch (Exception e) {
            Logger.e(e);
        }

        return "Rs. " + 0;
    }
}
