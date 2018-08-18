package com.upays.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.upays.R;

public class Become_A_Partner extends Activity implements View.OnClickListener {
    ImageView drawable_img;
    TextView header_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.become_a_partner);
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv.setText("Become A Partner");
        drawable_img = (ImageView) findViewById(R.id.drawable_img);
        drawable_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawable_img:
                finish();
                break;
        }
    }
}
