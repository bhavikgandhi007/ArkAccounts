package com.arkaccountslite.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.arkaccountslite.R;
import com.arkaccountslite.ui.home.HomeActivity;
import com.arkaccountslite.widget.LocaleHelper;

import java.util.ArrayList;

import io.blackbox_vision.wheelview.view.WheelView;


public class LanguageActivity extends AppCompatActivity {

    private Context mContext;
    private AppCompatButton btn_continue;
    private WheelView wheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = LanguageActivity.this;
        setContentView(R.layout.activity_select_language);
        initView();
    }

    private void initView() {
        ArrayList<String> languages = new ArrayList<>();
        languages.add("English");
        languages.add(getResources().getString(R.string.hindi));
        languages.add(getResources().getString(R.string.gujarati));
        wheelView = (WheelView) findViewById(R.id.loop_view);
        wheelView.setInitPosition(1);
        wheelView.setCanLoop(false);
        wheelView.setItems(languages);
        btn_continue = (AppCompatButton) findViewById(R.id.btn_continue);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wheelView.getSelectedItem() == 0){
                    LocaleHelper.setLocale(mContext,"en");
                } else if(wheelView.getSelectedItem() == 1){
                    LocaleHelper.setLocale(mContext,"hi");
                } else if(wheelView.getSelectedItem() == 2){
                    LocaleHelper.setLocale(mContext,"gu");
                }
                startActivity(new Intent(mContext, HomeActivity.class));
                finish();
            }
        });
    }
}
