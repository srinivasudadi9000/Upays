package com.upays.Activity;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upays.Adapters.Customer_Adapter;
import com.upays.Models.Home_m;
import com.upays.R;

import java.util.ArrayList;

public class Home extends Activity {
    RecyclerView survey_recycle, home_survey;
    ArrayList<Home_m> home_ms;
    ImageView drawable_img;
    DrawerLayout home;
    LinearLayout myll;
    TextView header_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        header_tv = (TextView) findViewById(R.id.header_tv);
        header_tv.setText("Dashboard");
        home = (DrawerLayout) findViewById(R.id.home);
        myll = (LinearLayout) findViewById(R.id.myll);
        drawable_img = (ImageView) findViewById(R.id.drawable_img);
        home_survey = (RecyclerView) findViewById(R.id.left_drawer);
        drawable_img.setImageDrawable(getResources().getDrawable(R.drawable.menu_img));
        home_survey.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

       // if (getIntent().getStringExtra("type").equals("Customer")){
          //  home_survey.setAdapter(new Customer_Adapter(addhome(), R.layout.menu_single, this));
       // }else {
            home_survey.setAdapter(new Customer_Adapter(addpartner(), R.layout.menu_single, this));
       // }

        drawable_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.openDrawer(myll);
            }
        });

    }

    public ArrayList<Home_m> addhome() {
        home_ms = new ArrayList<Home_m>();
      /*  home_ms.add(new Home_m(R.drawable.events, "Events"));
        home_ms.add(new Home_m(R.drawable.exhibition, "Exhibition"));
        home_ms.add(new Home_m(R.drawable.poll, "Bulk Sale"));
        home_ms.add(new Home_m(R.drawable.emergency, "Emergency"));
        home_ms.add(new Home_m(R.drawable.scholars, "ScholarShips"));
        home_ms.add(new Home_m(R.drawable.hospitals, "Hospitals"));
        home_ms.add(new Home_m(R.drawable.hotel, "Hotels"));
        home_ms.add(new Home_m(R.drawable.jobs, "Jobs"));
        home_ms.add(new Home_m(R.drawable.movies, "Movies"));
        home_ms.add(new Home_m(R.drawable.shoppingmalls, "Shopping Malls"));
        home_ms.add(new Home_m(R.drawable.companies, "Companies"));
        home_ms.add(new Home_m(R.drawable.notification, "Notification_s"));*/

        home_ms.add(new Home_m("Profile"));
        home_ms.add(new Home_m("Payment"));
        home_ms.add(new Home_m("Your Rides"));
        home_ms.add(new Home_m("Free Rides"));
        home_ms.add(new Home_m("Notification"));
        home_ms.add(new Home_m("Insurance"));
        home_ms.add(new Home_m("Settings"));
        home_ms.add(new Home_m("Support"));
        home_ms.add(new Home_m("FAQ"));
        home_ms.add(new Home_m("MyWallet"));
        home_ms.add(new Home_m("Become A Partner"));
        home_ms.add(new Home_m("Logout"));
        return home_ms;
    }
    public ArrayList<Home_m> addpartner() {
        home_ms = new ArrayList<Home_m>();
      /*  home_ms.add(new Home_m(R.drawable.events, "Events"));
        home_ms.add(new Home_m(R.drawable.exhibition, "Exhibition"));
        home_ms.add(new Home_m(R.drawable.poll, "Bulk Sale"));
        home_ms.add(new Home_m(R.drawable.emergency, "Emergency"));
        home_ms.add(new Home_m(R.drawable.scholars, "ScholarShips"));
        home_ms.add(new Home_m(R.drawable.hospitals, "Hospitals"));
        home_ms.add(new Home_m(R.drawable.hotel, "Hotels"));
        home_ms.add(new Home_m(R.drawable.jobs, "Jobs"));
        home_ms.add(new Home_m(R.drawable.movies, "Movies"));
        home_ms.add(new Home_m(R.drawable.shoppingmalls, "Shopping Malls"));
        home_ms.add(new Home_m(R.drawable.companies, "Companies"));
        home_ms.add(new Home_m(R.drawable.notification, "Notification_s"));*/

        home_ms.add(new Home_m("Your Trips"));
        home_ms.add(new Home_m("Profile"));
        home_ms.add(new Home_m("Earning Extra Money"));
        home_ms.add(new Home_m("Lead Board"));
        home_ms.add(new Home_m("MyWallet"));
        home_ms.add(new Home_m("On Going Trips"));
        home_ms.add(new Home_m("Emergency Contact"));
        home_ms.add(new Home_m("Invite Friends"));
        home_ms.add(new Home_m("Points History"));
        home_ms.add(new Home_m("Settings"));
        home_ms.add(new Home_m("Support"));
        home_ms.add(new Home_m("FAQ"));
        home_ms.add(new Home_m("Wallet History"));
        home_ms.add(new Home_m("Partner Care"));
        home_ms.add(new Home_m("Logout"));
        return home_ms;
    }

/*
    public ArrayList<Home_m> add_exitpol() {
        home_ms = new ArrayList<Home_m>();
        home_ms.add(new Home_m(R.drawable.school, "Schools"));
        home_ms.add(new Home_m(R.drawable.exhibition, "Collegs"));
        home_ms.add(new Home_m(R.drawable.events, "Politics"));
        home_ms.add(new Home_m(R.drawable.exhibition, "Books"));
        home_ms.add(new Home_m(R.drawable.laptop, "Laptops"));

        return home_ms;
    }
*/
}
