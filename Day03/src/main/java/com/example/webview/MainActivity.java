package com.example.webview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView mWebview;
    private ProgressBar mProcessbar;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        mProcessbar = (ProgressBar) findViewById(R.id.processbar);
    }

    public void load_webview(View view) {

        mWebview.loadUrl("https://www.wanandroid.com/");
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    mProcessbar.setVisibility(View.GONE);//加载完网页进度条消失
                    Toast.makeText(MainActivity.this, "进度完成100%", Toast.LENGTH_SHORT).show();
                }
                else{
                    mProcessbar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mProcessbar.setProgress(newProgress);//设置进度值
                }

            }
        });

        //想让网页适配webview
        settings = mWebview.getSettings();
        //支持js代码
        settings.setJavaScriptEnabled(true);
        //允许js弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);


        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //设置网页支持缩放
        //缩放操作
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //网页无图模式
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片

        //设置编码格式 为gbk  当网页出现乱码 就可以设置网页编码格式
        settings.setDefaultTextEncodingName("GBK");//设置编码格式


    }
}
