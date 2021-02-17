package com.lishuaihua.playdemo;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lishuaihua.jackplayer.widget.JackVideoView;

public class MainActivity extends AppCompatActivity {

    //    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/upyunb";
//    String path = "rtmp://rtmptest.b0.upaiyun.com/live/default4demo33596ad21e01c659489973d38c4d2c56d9mic";
//    String path = "http://rtmptest.b0.upaiyun.com/live/default4demo33596ad21e01c659489973d38c4d2c56d9mic.m3u8";
    String path = "rtmp://58.200.131.2:1935/livetv/hunantv";

//    String path = "/mnt/sdcard/storage/emulated/0/2651H.mp4";
//    String path = "/mnt/sdcard/videotest/2641H.mp4";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/myapp11";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/upyunab";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/myapp11";
//    String path = "rtmp://www.zhibo.58youxian.cn/uplive/test111";
//    String path = "rtmp://115.231.100.126/live/upyunab";
//    String path = "/mnt/sdcard/test.mp3";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/upyunaa";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/test131";

    private static final String TAG = MainActivity.class.getSimpleName();
    RelativeLayout.LayoutParams mVideoParams;

    JackVideoView jackVideoView;
    private EditText mPathEt;
    private TableLayout mHudView;
    private TextView mPlayInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mPathEt = (EditText) findViewById(R.id.editText);
        mHudView = (TableLayout) findViewById(R.id.hud_view);
        mPlayInfo = (TextView) findViewById(R.id.tv_info);
        mPlayInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHudView.getVisibility() == View.VISIBLE) {
                    mHudView.setVisibility(View.GONE);
                } else {
                    mHudView.setVisibility(View.VISIBLE);
                }
            }
        });

        jackVideoView = (JackVideoView) findViewById(R.id.uvv_vido);
        jackVideoView.setHudView(mHudView);

        //设置背景图片


        //设置播放地址
        jackVideoView.setVideoPath(path);

        //开始播放
        jackVideoView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 重新开始播放器
        jackVideoView.resume();
        jackVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jackVideoView.pause();
    }

    public void toggle(View view) {

        if (jackVideoView.isPlaying()) {

            //暂停播放
            jackVideoView.pause();

        } else {

            //开始播放
            jackVideoView.start();
        }
    }

    public void refresh(View view) {
        path = mPathEt.getText().toString();
        jackVideoView.setVideoPath(path);
        jackVideoView.start();
    }

    //全屏播放
    public void fullScreen(View view) {
        jackVideoView.fullScreen(this);
    }

    private void fullScreen() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(metrics.widthPixels, metrics.heightPixels);
        mVideoParams = (RelativeLayout.LayoutParams) jackVideoView.getLayoutParams();
        jackVideoView.setLayoutParams(params);
        jackVideoView.getTrackInfo();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {

        if (jackVideoView.isFullState()) {
            //退出全屏
            jackVideoView.exitFullScreen(this);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        jackVideoView.release(true);
    }
}
