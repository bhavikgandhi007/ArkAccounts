package com.arkaccountslite.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arkaccountslite.R;


public class AddAccountActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private AppCompatTextView txt_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = AddAccountActivity.this;
        setContentView(R.layout.activity_add_account);
        setupToolbar();
        initView();
    }

    private void initView() {

    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        txt_toolbar = (AppCompatTextView) findViewById(R.id.txt_toolbar);
        toolbar.getMenu().clear();
        toolbar.setNavigationIcon(R.drawable.ic_ios_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txt_toolbar.setText(getResources().getString(R.string.add_account));
    }


}
