package com.example.myapplication;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import java.net.URL;
import androidx.appcompat.app.AppCompatActivity;

public class ThreadActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private Handler imgDownloadHandler = new Handler();
    private ImageView imgDisplay;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate Running");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        imgDisplay = (ImageView) findViewById(R.id.imgDisplayWidget);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
//        mWebView.loadUrl("https://baidu.com/");
        mWebView.loadUrl(getString(R.string.download_url));
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String
                    contentDisposition,String mimetype, long contentLength){
                Intent downloadIntent = new Intent(ThreadActivity.this, DownloaderService.class);
                downloadIntent.putExtra("urlToDownload", url);
                Log.i(TAG, url);
                startService(downloadIntent);
            }
        });
        // Function containing the code to be executed in the new thread
        Thread t = new Thread(() -> {
            Log.i(TAG, "Thread Running");
            // Load the an image into a bitmap.
            final Bitmap bmp = loadBitmap(getString(R.string.img_url));
            // executed in the UI thread via the handler
            imgDownloadHandler.post(() -> imgDisplay.setImageBitmap(bmp));
        });
        t.start();
    }


    public Bitmap loadBitmap(String url) {
        Bitmap bmp = null;
        try {
            URL theUrl = new URL(url);
            bmp = BitmapFactory.decodeStream(theUrl.openConnection().getInputStream());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return bmp;
    }


}