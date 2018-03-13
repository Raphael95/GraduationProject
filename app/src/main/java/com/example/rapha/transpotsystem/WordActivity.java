package com.example.rapha.transpotsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import httpReuest.RequestOkHttp;

public class WordActivity extends AppCompatActivity {

    RequestOkHttp requestOkHttp = new RequestOkHttp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Intent intent = getIntent();
        String filePath = intent.getStringExtra("path");

        WebView showWord = (WebView)findViewById(R.id.showWord);
        String downloadPath =requestOkHttp.getDownloadUrl();
        String showWordPath = "http://ow365.cn/?i=15032&furl=" + downloadPath + filePath;
        showWord.loadUrl(showWordPath);

        showWord.getSettings().setJavaScriptEnabled(true);
        showWord.getSettings().setSupportZoom(true);
        showWord.getSettings().setBuiltInZoomControls(true);
        showWord.getSettings().setUseWideViewPort(true);
        showWord.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        showWord.getSettings().setLoadWithOverviewMode(true);

        showWord.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;   //返回true， 立即跳转，返回false,打开网页有延时
            }
        });

    }
}
