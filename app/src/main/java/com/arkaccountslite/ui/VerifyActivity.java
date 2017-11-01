package com.arkaccountslite.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arkaccountslite.R;
import com.arkaccountslite.model.VerifyOtp;
import com.arkaccountslite.model.auth.ResponseUser;
import com.arkaccountslite.model.auth.ResponseUserData;
import com.arkaccountslite.rest.service.AuthorizationService;
import com.arkaccountslite.ui.home.HomeActivity;
import com.arkaccountslite.utill.PrefUtils;
import com.goodiebag.pinview.Pinview;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VerifyActivity extends AppCompatActivity {


    private Context mContext;

    private static final String TAG = "VerifyActivity";
    private BroadcastReceiver broadcastReceiver;
    private ResponseUserData responseUserData;
    private AppCompatButton btn_verify;
    private Pinview pinview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = VerifyActivity.this;
        setContentView(R.layout.activity_verify);
        initView();

        responseUserData = new Gson().fromJson(PrefUtils.getUser(getApplicationContext()), ResponseUserData.class);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals("getSms")) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    pinview.setValue(intent.getStringExtra("otp"));
                }
            }
        };
    }

    public void initView (){
        btn_verify = (AppCompatButton) findViewById(R.id.btn_verify);
        pinview = (Pinview) findViewById(R.id.pinview);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotEmpty(pinview.getValue())) {
                    VerifyOtp verifyOtp = new VerifyOtp(String.valueOf(responseUserData.getPkUser()), pinview.getValue().toString());
                    Call<ResponseUser> call = AuthorizationService.verify(verifyOtp);
                    call.enqueue(new Callback<ResponseUser>() {
                        @Override
                        public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                            Log.d(TAG, "onResponse: " + response.body());
                            Log.d(TAG, "onResponse: " + response.body().getResponseData());
                            if (response.body().getResponseCode() == 101) {
                                Toast.makeText(getApplicationContext(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                            } else if (response.body().getResponseCode() == 100) {
                                Toast.makeText(getApplicationContext(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                                responseUserData.setIsVerified(true);
                                PrefUtils.setUser(getApplicationContext(), new Gson().toJson(responseUserData));

                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseUser> call, Throwable t) {

                        }
                    });
                } else {
                    if (!isNotEmpty(pinview.getValue())) {
                        Toast.makeText(VerifyActivity.this,"Please Enter OTP",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("getSms"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }


    private boolean isNotEmpty(String editText) {
        return editText != null && editText.toString().trim().length() > 1;
    }




}
