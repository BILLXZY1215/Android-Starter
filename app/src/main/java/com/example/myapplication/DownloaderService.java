package com.example.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.webkit.URLUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloaderService extends IntentService {
    private final String TAG = this.getClass().getName();
    public DownloaderService() {
        super("DownloaderService");

    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
        String url = intent.getStringExtra("urlToDownload");
        Bitmap image = getImage(url);
        String filename = URLUtil.guessFileName(url, null, null);
        File file = new File(Environment.getExternalStorageDirectory() + "/" + "Download/" + filename);
        saveImage(image, file);
    }

    private void saveImage(Bitmap img, File outPath){
        if (outPath.exists()) outPath.delete ();
        try {
            FileOutputStream outStream = new FileOutputStream(outPath);
            img.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            Log.d(TAG, "saveImage: There was an Error saving the image file - "
                    + e.getMessage()); }
    }
    private Bitmap getImage(String url){
        Bitmap img;
        Log.d(TAG, "getImage: " + url);
        try {
            URL imgUrl = new URL(url);
            URLConnection connection = imgUrl.openConnection();
            InputStream stream = connection.getInputStream();
            img = BitmapFactory.decodeStream(stream);
            Log.d(TAG, String.format("getImage: Download of %s is complete.",
                    url));
            return img ;
        } catch(MalformedURLException e){
            Log.d(TAG, "getImage: Malformed URL exception" + e.getMessage());
        } catch(IOException e){
            Log.d(TAG, "getImage: IO Exception" + e.getMessage());
        }
        return null;
    }
}