package com.example.adnan.webview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {
    WebView web;
    ProgressBar pro;
public static String url="";
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pro = (ProgressBar) findViewById(R.id.progress);
        web = (WebView) findViewById(R.id.webView);
        web.setWebViewClient(new MyBrowser());

        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            pro.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            pro.setVisibility(View.INVISIBLE);
        }
    }
}

