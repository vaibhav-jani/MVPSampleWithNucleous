package utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.widget.Button;

import java.util.Random;

public class PixelUtils {

    public static float dpFromPx(final float px, final Context context) {

        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final float dp, final Context context) {

        return dp * context.getResources().getDisplayMetrics().density;
    }

    private void setLeftCompoundButton(Button button, int drawableId) {

        Context activity = button.getContext();

        int size = (int) PixelUtils.pxFromDp(60, activity);

        Drawable drawable = ContextCompat.getDrawable(activity, drawableId);
        drawable.setBounds(0, 0, size, size);

        button.setCompoundDrawables(null, drawable, null, null);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static float screenWidth(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        return width;
    }

    public static int getRandomColor(){

        Random random = new Random();
        int red = random.nextInt();
        int green = random.nextInt();
        int blue = random.nextInt();
        int color = Color.rgb(red, green, blue);

        return color;
    }

}
