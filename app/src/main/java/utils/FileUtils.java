package utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    public static void copyFileOrDirFromAsset(String path, Context context) {

        AssetManager assetManager = context.getAssets();

        String assets[] = null;

        try {

            assets = assetManager.list(path);

            if (assets.length == 0) {

                copyFileFromAssets(path, context);

            } else {

                String fullPath = Environment.getExternalStorageDirectory() + "/" + path;

                File dir = new File(fullPath);

                if (!dir.exists()) {

                    dir.mkdir();
                }

                for (int i = 0; i < assets.length; ++i) {

                    copyFileOrDirFromAsset(path + "/" + assets[i], context);
                }
            }

        } catch (IOException ex) {

            Log.e("tag", "I/O Exception", ex);
        }
    }

    public static void copyFileFromAssets(String filename, Context context) {

        AssetManager assetManager = context.getAssets();

        InputStream in = null;

        OutputStream out = null;

        try {

            in = assetManager.open(filename);

            String newFileName = Environment.getExternalStorageDirectory() + "/" + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];

            int read;

            while ((read = in.read(buffer)) != -1) {

                out.write(buffer, 0, read);
            }

            in.close();

            in = null;

            out.flush();
            out.close();
            out = null;

            File file = new File(newFileName);
            Uri uri = Uri.fromFile(file);

            Intent scanFileIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
            context.sendBroadcast(scanFileIntent);

        } catch (Exception e) {

            Log.e("tag", e.getMessage());
        }
    }
}