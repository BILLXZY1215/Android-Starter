package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = this.getClass().getName();
    private Messenger mMessenger = null;

    private final int GET_TEXT = 2;
    private final int COUNT_UP = 1;
    private final int COUNT_DOWN = 0;

    private Button upBtn;
    private Button downBtn;
    private TextView counter;

    private BroadcastReceiver updateUIReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        upBtn = findViewById(R.id.service_up);
        downBtn = findViewById(R.id.service_down);
        counter = findViewById(R.id.count_view);
        upBtn.setOnClickListener(this);
        downBtn.setOnClickListener(this);
        Intent intent = new Intent(this, CounterService.class);
        this.bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

        IntentFilter filter = new IntentFilter();
        filter.addAction("register");
        //UI update here
        updateUIReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //UI update here
                Bundle b = intent.getExtras();
                if(b != null){
                    counter.setText(b.getString("count"));
                }
            }
        };
        registerReceiver(updateUIReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        unregisterReceiver(updateUIReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_up:
                up(view);
                break;
            case R.id.service_down:
                down(view);
                break;
            default:
                break;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: MainActivity");
            mMessenger = new Messenger(service);

//            Thread t = new Thread(() -> {
//                while(true){
//                    try {
//                        Thread.sleep(2000);
//                    } catch (Exception e) {
//                        return;
//                    }
//                    Message message = new Message();
//                    message.what = GET_TEXT;
//                    try {
//                        mMessenger.send(message);
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//            t.start();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: onServiceDisconnected");
            mMessenger = null;
        }
    };

    private void up(View v){
        Message message = new Message();
        message.what = COUNT_UP;
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void down(View v){
        Message message = new Message();
        message.what = COUNT_DOWN;
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
