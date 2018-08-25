package com.upays.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.upays.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.security.auth.callback.Callback;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login extends Activity implements View.OnClickListener {
    Button btn_login;
    ImageView facebook_btn, twitter_btn, google_btn, youtube_btn, linkedin_btn;
    EditText input_usename,otp_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        otp_et = (EditText) findViewById(R.id.otp_et);
        input_usename = (EditText) findViewById(R.id.input_usename);
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
                if (btn_login.getText().equals("submit")){
                    SharedPreferences s = getSharedPreferences("userdetails",MODE_PRIVATE);
                    if (s.getString("otp","").equals(otp_et.getText().toString())){
                        Intent home = new Intent(Login.this,Home.class);
                        startActivity(home);
                        finish();
                    }else {
                        showDialog(Login.this, "Invalid user login", "no");
                    }
                }else {
                    if (input_usename.getText().toString().length() == 0) {
                        showDialog(Login.this, "Mobile number should not be empty", "no");
                    } else if (input_usename.getText().length() < 10) {
                        showDialog(Login.this, "Please enter valid mobile number", "no");
                    } else {
                        btn_login.setText("submit");
                        setRegisterdetails(input_usename.getText().toString());
                    }
                }

                break;
            case R.id.youtube_btn:
                Intent youtube_btn = new Intent(Login.this, Social_Media.class);
                youtube_btn.putExtra("url", "https://www.youtube.com/channel/UCocV045wZkIpfodGxk4_guA");
                startActivity(youtube_btn);
                break;
            case R.id.twitter_btn:
                Intent twitter_btn = new Intent(Login.this, Social_Media.class);
                twitter_btn.putExtra("url", "https://twitter.com/UpayServices");
                startActivity(twitter_btn);
                break;
            case R.id.facebook_btn:
                Intent facebook_btn = new Intent(Login.this, Social_Media.class);
                facebook_btn.putExtra("url", "https://www.facebook.com/Upay-Services-260466841345542");
                startActivity(facebook_btn);
                break;
            case R.id.google_btn:
                Intent google_btn = new Intent(Login.this, Social_Media.class);
                google_btn.putExtra("url", "https://plus.google.com/u/0/105467287832735601375");
                startActivity(google_btn);
                break;
            case R.id.linkedin_btn:
                Intent linkedin_btn = new Intent(Login.this, Social_Media.class);
                linkedin_btn.putExtra("url", "https://plus.google.com/u/0/105467287832735601375");
                startActivity(linkedin_btn);
                break;

        }
    }


    public void setRegisterdetails(String DeviceNo) {
        final ProgressDialog progress;
        progress = new ProgressDialog(this);
        progress.setMessage("Authenticating User..");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();
        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.upays.org/upays.in/srinivasu/process1.php?").newBuilder();
        urlBuilder.addQueryParameter("act", "login");
        urlBuilder.addQueryParameter("mobile", input_usename.getText().toString());

        String url = urlBuilder.build().toString();

        final Request request = new Request.Builder()
                .url(url)
                .build();
        Log.d("RegisterDevice", request.toString());
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                progress.dismiss();
                // Log.d("result", e.getMessage().toString());
                // e.printStackTrace();
                Log.d("result", "service no runnning...............");
                Login.this.runOnUiThread(new Runnable() {
                    public void run() {
                        showDialog(Login.this, "Internal server occured please try again", "no");
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                progress.dismiss();
                if (response.isSuccessful()) {
                    Log.d("result_success", response.body().toString());
                    try {
                        if (response.body().toString().equals(null)) {
                            Login.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    showDialog(Login.this, "Service not working", "no");
                                }
                            });
                        } else {
                            JSONObject jsonObject = new JSONObject(response.body().string());

                            //  JSONArray jsonArray = jsonObject.getJSONArray("Message");
                            Log.d("success", jsonObject.toString());
                            if (jsonObject.getString("status").equals("1")) {
                                JSONObject ud = new JSONObject(jsonObject.getString("msg"));
                                SharedPreferences.Editor userdetails = getSharedPreferences("userdetails", MODE_PRIVATE).edit();
                                userdetails.putString("usermobile", ud.getString("mobile"));
                                userdetails.putString("userid", ud.getString("userid"));
                                userdetails.putString("otp", jsonObject.getString("Otp"));
                                userdetails.commit();
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //  throw new IOException("Unexpected code " + response.body().toString());
                } else {
                    Log.d("result_else", response.body().toString());
                    Login.this.runOnUiThread(new Runnable() {
                        public void run() {
                            showDialog(Login.this, "Server busy at this moment please try after some time or contact admin", "no");
                        }
                    });

                }
            }
        });
    }


    public void showDialog(Activity activity, String msg, final String status) {
        final Dialog dialog = new Dialog(activity, R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);

        TextView text = dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        ImageView b = dialog.findViewById(R.id.b);
        if (status.equals("yes")) {
            b.setVisibility(View.VISIBLE);
        } else {
            b.setVisibility(View.GONE);
        }
        Button dialogButton = dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equals("yes")) {
                    dialog.dismiss();
                    Intent home = new Intent(Login.this, Customer_map.class);
                    startActivity(home);
                    finish();
                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }
}
