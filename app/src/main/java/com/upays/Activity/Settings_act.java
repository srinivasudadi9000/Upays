package com.upays.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.upays.R;

public class Settings_act extends Activity implements View.OnClickListener {
    ImageView drawable_img;
    TextView header_tv;
    CardView driver_cv,bank_cv,qualification_cv,document_cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_act);
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv.setText("Settings");
        drawable_img = (ImageView) findViewById(R.id.drawable_img);
        drawable_img.setOnClickListener(this);
        document_cv = (CardView) findViewById(R.id.document_cv);
        document_cv.setOnClickListener(this);
        qualification_cv = (CardView) findViewById(R.id.qualification_cv);
        qualification_cv.setOnClickListener(this);
        bank_cv = (CardView) findViewById(R.id.bank_cv);
        bank_cv.setOnClickListener(this);
        driver_cv = (CardView) findViewById(R.id.driver_cv);
        driver_cv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawable_img:
                finish();
                break;
            case R.id.document_cv:
                Intent doc = new Intent(this,Document_upload.class);
                startActivity(doc);
                break;
            case R.id.driver_cv:
                Intent driver_cv = new Intent(this,Driver_vehicle_Details.class);
                startActivity(driver_cv);
                break;
            case R.id.bank_cv:
                Intent bank_cv = new Intent(this,Bank_Details.class);
                startActivity(bank_cv);
                break;
            case R.id.qualification_cv:
                Intent qualification_cv = new Intent(this,Qualification_certificate.class);
                startActivity(qualification_cv);
                break;
        }
    }
}
