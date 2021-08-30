package com.llw.goodweather.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.llw.goodweather.R;
import com.llw.mvplibrary.base.BaseActivity;

/**
 * 隐私页面页面
 * @author lonel
 */
public class PrivacyPolicyActivity extends BaseActivity {
    private WebView webView;
    @Override
    public void initData(Bundle savedInstanceState) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        webView = findViewById(R.id.webview);

        loadUrl("https://www.umeng.com/page/policy");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_privacy_policy;
    }

    /**
     * 加载网页Url
     *
     * @param url
     */
    private void loadUrl(String url) {
        if (url == null) {
            return;
        }

        WebSettings webSetting = webView.getSettings();
        //设置JS允许
        webSetting.setJavaScriptEnabled(true);
        //设置WebView是否使用viewport
        webSetting.setUseWideViewPort(true);
        //设置WebView是否使用预览模式加载界面。
        webSetting.setLoadWithOverviewMode(true);
        //设置WebView是否支持使用屏幕控件或手势进行缩放
        webSetting.setSupportZoom(true);
        //设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setAppCacheEnabled(true);//APP缓存
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);//地理位置
        webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//读取缓存

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("webViewT", "加载开始");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("webViewT", "加载完成");
            }

        });

        //进度
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
                Log.d("webViewT", "newProgress: " + newProgress);
            }
        });
    }
}