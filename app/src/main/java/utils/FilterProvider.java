package utils;

import android.text.InputFilter;
import android.text.Spanned;

public class FilterProvider {

    public static InputFilter getRealNumberFilter(final int maxDigitsBeforeDecimalPoint, final int maxDigitsAfterDecimalPoint) {

        InputFilter filter = new InputFilter() {

            //final int maxDigitsBeforeDecimalPoint = 4;

            //final int maxDigitsAfterDecimalPoint = 1;

            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {

                StringBuilder builder = new StringBuilder(dest);

                builder.replace(dstart, dend, source
                        .subSequence(start, end).toString());

                if (!builder.toString().matches(
                        "(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?"

                )) {
                    if (source.length() == 0)
                        return dest.subSequence(dstart, dend);
                    return "";
                }

                return null;

            }
        };

        return filter;
    }

    public static InputFilter getIntNumberFilter(final int min, final int max) {

        InputFilter filter = new InputFilter() {

            //final int maxDigitsBeforeDecimalPoint = 4;

            //final int maxDigitsAfterDecimalPoint = 1;

            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {

                try {
                    int input = Integer.parseInt(dest.toString() + source.toString());
                    if (isInRange(min, max, input))
                        return null;
                } catch (NumberFormatException nfe) { }

                return "";

            }

            private boolean isInRange(int a, int b, int c) {

                return b > a ? c >= a && c <= b : c >= b && c <= a;
            }
        };

        return filter;
    }
}