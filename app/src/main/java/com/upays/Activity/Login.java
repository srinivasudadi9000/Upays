package com.upays.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.upays.R;

public class Login extends Activity implements View.OnClickListener {
    Button btn_login;
    ImageView facebook_btn,twitter_btn,google_btn,youtube_btn,linkedin_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn_login = (Button) findViewById(R.id.btn_login);
        youtube_btn = (ImageView) findViewById(R.id.youtube_btn);
        facebook_btn = (ImageView) findViewById(R.id.facebook_btn);
        twitter_btn = (ImageView) findViewById(R.id.twitter_btn);
        google_btn = (ImageView) findViewById(R.id.google_btn);
        linkedin_btn = (ImageView) findViewById(R.id.linkedin_btn);
        btn_login.setOnClickListener(this);
        google_btn.setOnClickListener(this);
        facebook_btn.setOnClickListener(this);
        twitter_btn.setOnClickListener(this);
        youtube_btn.setOnClickListener(this);
        linkedin_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Intent home = new Intent(Login.this, Home.class);
                startActivity(home);
                break;
            case R.id.youtube_btn:
                Intent youtube_btn = new Intent(Login.this, Social_Media.class);
                youtube_btn.putExtra("url","https://www.youtube.com/channel/UCocV045wZkIpfodGxk4_guA");
                startActivity(youtube_btn);
                break;
            case R.id.twitter_btn:
                Intent twitter_btn = new Intent(Login.this, Social_Media.class);
                twitter_btn.putExtra("url","https://twitter.com/UpayServices");
                startActivity(twitter_btn);
                break;
            case R.id.facebook_btn:
                Intent facebook_btn = new Intent(Login.this, Social_Media.class);
                facebook_btn.putExtra("url","https://www.facebook.com/Upay-Services-260466841345542");
                startActivity(facebook_btn);
                break;
            case R.id.google_btn:
                Intent google_btn = new Intent(Login.this, Social_Media.class);
                google_btn.putExtra("url","https://plus.google.com/u/0/105467287832735601375");
                startActivity(google_btn);
                break;
            case R.id.linkedin_btn:
                Intent linkedin_btn = new Intent(Login.this, Social_Media.class);
                linkedin_btn.putExtra("url","https://plus.google.com/u/0/105467287832735601375");
                startActivity(linkedin_btn);
                break;

        }
    }
}
