package com.diligroup.Before.fragment;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.diligroup.R;
import com.diligroup.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by Kevin on 2016/7/4.
 */
public class BeforeFragment extends BaseFragment {
    @Bind(R.id.web_before)
    WebView  webView_before;

    @Override
    public int getLayoutXml() {
        return R.layout.fragment_before;
    }

    @Override
    public void setViews() {
        //启用支持javascript
        webView_before.getSettings().setDefaultTextEncodingName("utf-8");
        webView_before.getSettings().setJavaScriptEnabled(true);
        webView_before.getSettings().setSupportMultipleWindows(true);
        webView_before.getSettings().setBuiltInZoomControls(true);
        webView_before.getSettings().setSupportZoom(true);
        webView_before.getSettings().setLoadsImagesAutomatically(true);  //支持自动加载图片
        webView_before.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        MyWebClient  client=new MyWebClient();
        webView_before.setWebViewClient(client);
        webView_before.loadUrl("http://www.zealer.com/");
    }

    @Override
    public void setListeners() {

    }
    class MyWebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        //重写此方法才能够处理在浏览器中的按键事件。
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
        }
    }
}
