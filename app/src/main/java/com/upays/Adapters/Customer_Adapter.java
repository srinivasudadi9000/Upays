package com.upays.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.upays.Activity.Become_A_Partner;
import com.upays.Activity.FAQ;
import com.upays.Activity.Free_Rides;
import com.upays.Activity.Insurance;
import com.upays.Activity.MapsActivity;
import com.upays.Activity.MyWallet;
import com.upays.Activity.Notification_s;
import com.upays.Activity.Payments;
import com.upays.Activity.Profile;
import com.upays.Activity.Settings_act;
import com.upays.Activity.Support;
import com.upays.Activity.Your_Rides;
import com.upays.Activity.Your_Trip;
import com.upays.Activity.Customer_map;
import com.upays.Models.Home_m;
import com.upays.R;

import java.util.ArrayList;

/**
 * Created by user on 15-08-2018.
 */

public class Customer_Adapter extends RecyclerView.Adapter<Customer_Adapter.Holder> {
    ArrayList<Home_m> home_ms;
    int rootlayout;
    Context context;

    public Customer_Adapter(ArrayList<Home_m> addpartner, int menu_single, Customer_map customer_map) {
        this.home_ms = addpartner;
        this.rootlayout = menu_single;
        this.context = customer_map;
    }

    /*
        public Customer_Adapter(ArrayList<Home_m> addhome, int root_pol, Home home) {
            this.home_ms = addhome;
            this.rootlayout = root_pol;
            this.context = home;
        }
    */


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rootlayout, parent, false);
        return new Customer_Adapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        holder.root_pol_txt.setText(home_ms.get(position).getHomename());
//        holder.root_pol_img.setImageDrawable(context.getResources().getDrawable(home_ms.get(position).getImage()));
        holder.menu_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (holder.root_pol_txt.getText().toString()) {
                    case "Profile":
                        Intent profile = new Intent(context, Profile.class);
                        // profile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(profile);
                        break;
                    case "Payment":
                        Intent Payment = new Intent(context, Payments.class);
                        //  Payment.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(Payment);
                        break;
                    case "Your Rides":
                        Intent yourrides = new Intent(context, Your_Rides.class);
                        //  yourrides.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(yourrides);
                        break;
                    case "Free Rides":
                        Intent freerides = new Intent(context, Free_Rides.class);
                        //  freerides.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(freerides);
                        break;
                    case "Notification_s":
                        Intent Notifications = new Intent(context, Notification_s.class);
                        //  Notification_s.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(Notifications);
                        break;
                    case "Insurance":
                        Intent ins = new Intent(context, Insurance.class);
                        //  ins.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(ins);
                        break;
                    case "Settings":
                        Intent Setting = new Intent(context, Settings_act.class);
                        //  Setting.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(Setting);
                        break;
                    case "Support":
                        Intent Supports = new Intent(context, Support.class);
                        // Support.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(Supports);
                        break;
                    case "FAQ":
                        Intent FAQs = new Intent(context, FAQ.class);
                        // FAQs.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(FAQs);
                        break;
                    case "MyWallet":
                        Intent MyWallets = new Intent(context, MyWallet.class);
                        //  MyWallet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(MyWallets);
                        break;
                    case "Become A Partner":
                        Intent become_a_partner = new Intent(context, Become_A_Partner.class);
                        //  become_a_partner.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(become_a_partner);
                        break;
                    case "Your Trips":
                        Intent ut = new Intent(context, Your_Trip.class);
                        //  become_a_partner.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(ut);
                        break;
                    case "Logout":
                        Intent Logout = new Intent(context, MapsActivity.class);
                        //  Logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(Logout);
                        ((Activity) context).finish();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return home_ms.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView root_pol_txt;
        CardView menu_cv;
        ImageView root_pol_img;

        public Holder(View itemView) {
            super(itemView);
            menu_cv = (CardView) itemView.findViewById(R.id.menu_cv);
            root_pol_txt = (TextView) itemView.findViewById(R.id.root_pol_txt);
            //root_pol_img = (ImageView) itemView.findViewById(R.id.root_pol_img);
        }
    }
}

