package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class JsToAndroidActivity extends AppCompatActivity {


    private WebView mWebview;
    private WebSettings mWebSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_to);
        initView();
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // -------------   JS调用Android第二种方式--------------
  /*      mWebview.loadUrl("file:///android_asset/demo3.html");
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if ("js".equals(uri.getScheme())){
                    if (uri.getAuthority().equals("webview")){
                        Log.e("TAG", "执行Android代码了");
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);

            }
        });*/

        // -------------   JS调用Android第三种方式--------------
        mWebview.loadUrl("file:///android_asset/demo4.html");
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Uri uri = Uri.parse(message);
                if (uri.getScheme().equals("js")) {
                    if (uri.getAuthority().equals("webview")) {
                        // 可以执行Android的代码了
                        Log.e("TAG","HAHHHHHHHHHH,js调用Android了");
                        result.confirm("哈哈哈，这是要传递给JS的返回值");
                    }
                    return true;
                }

                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Uri uri = Uri.parse(message);
                if (uri.getScheme().equals("js")){
                    if (uri.getAuthority().equals("webview")){
                        Log.e("TAG","bbbbbbbbbb,js调用Android了");
//                        result.confirm();
                    }
//                    return true;
                }
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Uri uri = Uri.parse(message);
                if (uri.getScheme().equals("js")) {
                    if (uri.getAuthority().equals("webview")) {
                        // 可以执行Android的代码了
                        Log.e("TAG","AAAAAAAAA,js调用Android了");
                    }
//                    return true;
                }
                return super.onJsAlert(view, url, message, result);
            }
        });
    }
}

