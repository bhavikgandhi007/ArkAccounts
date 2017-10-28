package com.arkaccountslite.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.arkaccountslite.R;


public class LoginActivity extends AppCompatActivity {

    private Context mContext;
    private AppCompatButton btn_next,btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = LoginActivity.this;
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){
        btn_next = (AppCompatButton) findViewById(R.id.btn_next);
        btn_signup = (AppCompatButton) findViewById(R.id.btn_signup);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Todo btn next click
                startActivity(new Intent(mContext,LanguageActivity.class));
                finish();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Todo btn signup click
                startActivity(new Intent(mContext,SignupActivity.class));
                finish();
            }
        });

    }
}
