package utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Base64ImageUtil {

    public static Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64
                    .decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {

            Logger.e(e);

            return null;
        }
    }

    public static Bitmap bytesToBitmap(byte[] encodeByte) {
        try {
            Bitmap bitmap = stringToBitmap( new String(encodeByte, "UTF-8") );
            return bitmap;
        } catch (Exception e) {

            Logger.e(e);

            return null;
        }
    }

    public static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, ByteStream);
        byte[] b = ByteStream.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }


    public static String bitmapToString(byte[] bytes) {

        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap getBitmap(String path, Activity context) {

        ContentResolver mContentResolver = context.getContentResolver();

        try {

            InputStream in = new FileInputStream(path);
            final int IMAGE_MAX_SIZE = 1200000; // 1.2MP

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(in, null, o);
            in.close();

            int scale = 1;

            while ((o.outWidth * o.outHeight) * (1 / Math.pow(scale, 2)) >
                    IMAGE_MAX_SIZE) {

                scale++;
            }
            Logger.d("Image", "scale = " + scale + ", orig-width: " + o.outWidth + ", orig-height: " + o.outHeight);

            Bitmap b = null;

            in = new FileInputStream(path);

            if (scale > 1) {

                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                o = new BitmapFactory.Options();
                o.inSampleSize = scale;
                b = BitmapFactory.decodeStream(in, null, o);

                // resize to desired dimensions
                int height = b.getHeight();
                int width = b.getWidth();
                Logger.d("Image", "1th scale operation dimenions - width: " + width + ", height: " + height);

                double y = Math.sqrt(IMAGE_MAX_SIZE
                        / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, (int) x,
                        (int) y, true);
                b.recycle();
                b = scaledBitmap;

                System.gc();

            } else {

                b = BitmapFactory.decodeStream(in);
            }

            in.close();

            Logger.d("Image", "bitmap size - width: " + b.getWidth() + ", height: " + b.getHeight());

            ExifInterface ei = new ExifInterface(path);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                   b = rotateBitmap(b, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    b = rotateBitmap(b, 180);
                    break;
                // etc.
            }

            return b;

        } catch (Exception e) {

            Logger.e(e);

            return null;
        }
    }

    public static Bitmap rotateBitmap(Bitmap source, float angle) {

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);

        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static String saveBitmap(Bitmap bitmap) {

        try {

            File newDir = new File(Environment.getExternalStorageDirectory(), "RRPrinter");

            newDir.mkdirs();

            File file = new File(newDir, "test.bmp");

            if (file.exists())

                file.delete();

            try {

                FileOutputStream fos = new FileOutputStream(file);

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                fos.flush();

                fos.close();

            } catch (FileNotFoundException e) {

                Logger.e("GREC", e.getMessage());

            } catch (IOException e) {

                Logger.e("GREC", e.getMessage());
            }

            return file.getAbsolutePath();

        } catch (Exception e) {

            Logger.e("BitmapUtill", e.getMessage());
        }

        return null;
    }
}