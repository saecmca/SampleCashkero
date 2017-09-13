package com.mani.cashkaro.cashkaro.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mani.cashkaro.cashkaro.R;

public class WebviewActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setTitle("Loading ....");
        progressDialog.setMessage("Loading...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                progressDialog.dismiss();


            }
        }, 5000);
        Intent intent = getIntent();
        String strTit = intent.getStringExtra("prName");
        ((TextView) findViewById(R.id.tvTit)).setText(strTit);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webView.loadUrl("https://cashkaro.com/hotdeals/topvouchers");
        webView.scrollTo(0,0);
        ((ImageView) findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
