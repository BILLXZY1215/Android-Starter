package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CounterService extends Service {
    private final String TAG = this.getClass().getName();
    private Messenger messenger;
    private Counter counter;

    private final int COUNT_UP = 1;
    private final int COUNT_DOWN = 0;
    private final int GET_TEXT = 2;

    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COUNT_UP:
                    Log.d(TAG, "countUp: UP!");
                    counter.setDirection(true); //up
                    break;
                case COUNT_DOWN:
                    Log.d(TAG, "countDown: DOWN!");
                    counter.setDirection(false); //down
                    break;
//                case GET_TEXT:
//                    Log.d(TAG, "Fetching States...");
////                    TextView t = findViewById(R.id.count_view);
//                    break;
                // do something...
                default: super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: Counter Service Created");
        super.onCreate();
        counter = new Counter();
        messenger = new Messenger(new MessageHandler());
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: Service Binder");
        return messenger.getBinder();
    }

    protected class Counter extends Thread implements Runnable {
        private final String TAG = this.getClass().getName();
        boolean direction = true;
        int count = 0;
        boolean running = true;
        public Counter() {
            this.start();
        }
        public int getCount() {
            return count;
        }
        public void setDirection(boolean d) {
            direction = d;
        }
        public void setRunning(boolean r) {
            running = r;
        }
        public void run() {
            while (this.running) {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    return;
                }
                if (direction)
                    count++;
                else
                    count--;
                Log.d(TAG, String.format("run: Service Counter = %d", count));
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("count", String.valueOf(count));
                intent.putExtras(b);
                intent.setAction("register");
                sendBroadcast(intent);
            }
            Log.d(TAG, "run: Service Counter Thread is Exiting"); }
    }
}
