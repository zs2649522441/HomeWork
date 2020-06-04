package com.example.webview;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;
import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
public class MainActivity extends AppCompatActivity {

    private WebView mWebview;
    private ProgressBar mProcessbar;
    private WebSettings settings;
    private ImageView mImageView;

    private final int CODE_PERMISSION = 4;
    private final int CODE_CROP = 3;
    private boolean FLAG_PERMISSION = false;
    private List<String> list;
    private File file;
    private Uri cropUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        mProcessbar = (ProgressBar) findViewById(R.id.processbar);
        mImageView=(ImageView)findViewById(R.id.img);
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

    public void load_js(View view) {
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
        settings.setDefaultTextEncodingName("UTF-8");//设置编码格式


        mWebview.loadUrl("file:///android_asset/demo2.html");


        //---------------------------------------以下是JS(BUtton和图片都是再网页里面的)调用Android------------------------------------
        //1.要映射的那个类对象  2.类对象引用
        mWebview.addJavascriptInterface(new JsToAndroid(this), "test");

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //         回调成功
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 相册选取
                case 100:
                    Uri selectedImage = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    String imagePath = c.getString(columnIndex);
                    c.close();
                    cropPhoto(selectedImage);
                    break;
                case CODE_CROP:
                    mWebview.evaluateJavascript("javascript:logImgPath(\"" + cropUri.toString() + "\")", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                        }
                    });
                    mImageView.setImageURI(cropUri);
//                    Glide.with(this).load(cropUri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mPicUser);
//                    popupWindow.dismiss();
            }
        }
    }

    private void cropPhoto(Uri uri) {
        Log.d("test", "uri:" + uri.toString());
        file = new File(Environment.getExternalStorageDirectory(), "cropImage" + System.currentTimeMillis() + ".jpg");
//        showToast(file.getPath());
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
        }
        cropUri = Uri.fromFile(file);
        intent.setDataAndType(uri, "image/*");
        //裁剪图片的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("crop", "true");//可裁剪
        // 裁剪后输出图片的尺寸大小
        //intent.putExtra("outputX", 400);
        //intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);//支持缩放
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
        intent.putExtra("noFaceDetection", true);//取消人脸识别
        startActivityForResult(intent, CODE_CROP);
    }
}