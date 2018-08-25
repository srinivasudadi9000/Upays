package com.upays.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.upays.Adapters.Customer_Adapter;
import com.upays.Models.Home_m;
import com.upays.Models.Markers;
import com.upays.R;

import java.util.ArrayList;

public class Customer_map extends FragmentActivity implements OnMapReadyCallback {
    RecyclerView survey_recycle, home_survey;
    ArrayList<Home_m> home_ms;
    ImageView drawable_img;
    DrawerLayout home;
    LinearLayout myll;
    TextView header_tv, tap_txt;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        tap_txt = (TextView) findViewById(R.id.tap_txt);
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
        tap_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(Customer_map.this, MapsActivity.class);
                startActivity(map);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera

        ///   mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

      /*  LatLng s = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney)
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));*/


        ArrayList<Markers> markersArray = new ArrayList<Markers>();
        markersArray.add(new Markers(17.7006298, 83.2443407));
        markersArray.add(new Markers(17.7106052,83.2143));
        markersArray.add(new Markers(17.7439138, 83.3530102));
        markersArray.add(new Markers(17.7439138, 83.3630102));
        markersArray.add(new Markers(17.7439138, 83.3830102));
        markersArray.add(new Markers(17.7439138, 83.2830102));
        markersArray.add(new Markers(17.7541954,83.2522687));
        markersArray.add(new Markers(17.7471215, 83.0617903));
        markersArray.add(new Markers(17.7464287, 83.0235876));
        for (int i = 0; i < markersArray.size(); i++) {
            // createMarker(markersArray.get(i).getLat(), markersArray.get(i).getLongi(), markersArray.get(i).getTitle(), markersArray.get(i).getSnippet(), markersArray.get(i).getIconResID());
            //  createMarker(markersArray.get(i).getLat(), markersArray.get(i).getLongi());
            LatLng sydney = new LatLng(markersArray.get(i).getLat(), markersArray.get(i).getLongi());
            mMap.addMarker(new MarkerOptions().position(sydney)
                    .flat(true)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

        }
        LatLng sydney = new LatLng(17.7266708, 83.2989934);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);
        mMap.animateCamera(zoom);

    }


    protected Marker createMarker(double latitude, double longitude) {
        LatLng s = new LatLng(latitude, longitude);
        return mMap.addMarker(new MarkerOptions().position(s)
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));

    }

    public ArrayList<Home_m> addpartner() {
        home_ms = new ArrayList<Home_m>();
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

}
