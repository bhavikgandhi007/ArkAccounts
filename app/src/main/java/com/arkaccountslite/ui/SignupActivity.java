package com.arkaccountslite.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.arkaccountslite.MainActivity;
import com.arkaccountslite.R;
import com.arkaccountslite.ui.home.HomeActivity;


public class SignupActivity extends AppCompatActivity {


    private Context mContext;
    private AppCompatButton btn_signup,btn_already_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SignupActivity.this;
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView(){
        btn_signup = (AppCompatButton) findViewById(R.id.btn_signup);
        btn_already_account = (AppCompatButton) findViewById(R.id.btn_already_account);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,HomeActivity.class));
                finish();
            }
        });

        btn_already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,LoginActivity.class));
                finish();
            }
        });
    }
}
