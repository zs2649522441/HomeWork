package com.example.webview;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class JsToAndroid {
    private MainActivity mContext;

    public JsToAndroid(MainActivity activity) {
        this.mContext = activity;
    }

    @JavascriptInterface
    public void hllo(String msg) {
        Log.e("TAG---", msg);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        MainActivity activity = (MainActivity) mContext;
        mContext.startActivityForResult(intent, 100);
    }
}
