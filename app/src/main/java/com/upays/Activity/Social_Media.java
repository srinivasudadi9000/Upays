package com.upays.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.upays.R;

import org.w3c.dom.Text;

import java.io.InputStream;

public class Social_Media extends Activity implements View.OnClickListener {
    ImageView drawable_img;
    TextView header_tv;
    WebView social_wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social__media);
        drawable_img = (ImageView) findViewById(R.id.drawable_img);
        social_wv = (WebView) findViewById(R.id.social_wv);
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv.setText("Upays Social Media");
        drawable_img.setOnClickListener(this);
        WebSettings webSettings = social_wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        social_wv.setWebViewClient(new MyWebViewClient());
        System.out.print("load url "+getIntent().getStringExtra("url").toString());
        social_wv.loadUrl(getIntent().getStringExtra("url").toString());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawable_img:
                finish();
                break;
        }
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }


}
