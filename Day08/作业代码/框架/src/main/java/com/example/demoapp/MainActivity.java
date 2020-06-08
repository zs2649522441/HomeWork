package com.example.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView mImg;
    /**
     * 立即体验
     */
    private TextView mDesc;
    private int a=2;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            if (msg.what == 0) {
                if (a>0) {
                    mDesc.setText(a+"");
                    //直接进行跳转
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                }
                timer.cancel();
                finish();
            }

            return false;
        }
    });
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mImg = (ImageView) findViewById(R.id.img);
        mDesc = (TextView) findViewById(R.id.desc);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Message msg = Message.obtain();
                msg.obj = timer;
                msg.what = 0;
                handler.sendMessage(msg);
            }
        },1000,1000);
    }

}
