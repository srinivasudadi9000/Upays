package com.upays.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.upays.R;

import java.util.ArrayList;

public class Profile extends Activity implements View.OnClickListener {
    ImageView drawable_img;
    TextView header_tv;
    ArrayList<String> whererufrom;
    Spinner howdidufind_et, type_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv.setText("Profile");
        drawable_img = (ImageView) findViewById(R.id.drawable_img);
        drawable_img.setOnClickListener(this);
        howdidufind_et = (Spinner) findViewById(R.id.howdidufind_et);
        type_et = (Spinner) findViewById(R.id.type_et);
        whererufrom = new ArrayList<>();
        whererufrom.add("How did you find us ?");
        whererufrom.add("Friend");
        whererufrom.add("Social Media");
        whererufrom.add("TV Ads");
        whererufrom.add("Banners");
        whererufrom.add("Invitation");

        ArrayAdapter<String> cat = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1
                , whererufrom);
        howdidufind_et.setAdapter(cat);


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
