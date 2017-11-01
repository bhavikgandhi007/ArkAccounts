package com.arkaccountslite.ui.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arkaccountslite.R;
import com.arkaccountslite.ui.AddAccountActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity {

    private Context mContext;
    private BottomNavigationViewEx bottomBar;
    private Toolbar toolbar;
    private AppCompatTextView txt_toolbar;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = HomeActivity.this;
        setContentView(R.layout.activity_main);
        setupToolbar(0);
        initView();
    }

    private void initView() {
        int currentItem = getIntent().getIntExtra("currentItem",0);
        container = (FrameLayout) findViewById(R.id.container);
        bottomBar = (BottomNavigationViewEx) findViewById(R.id.bnve);
        bottomBar.setIconSize(24, 24);
        bottomBar.setIconSizeAt(2, 40, 40);
        bottomBar.setIconMarginTop(2, 0);
        bottomBar.enableAnimation(false);
        bottomBar.enableShiftingMode(false);
        bottomBar.enableItemShiftingMode(false);

        if(currentItem == 4) {
            menuSelected(4);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentSetting.newInstance(), FragmentSetting.class.getSimpleName())
                    .commit();
        } else {
            menuSelected(0);
        }
        Log.i("TAG", "initView: currentItem"+currentItem);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.tab_home:
                        setupToolbar(0);
                        menuSelected(0);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, FragmentSummary.newInstance(), FragmentSummary.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.tab_credit:
                        setupToolbar(1);
                        menuSelected(1);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, FragmentCreditDebit.newInstance(), FragmentCreditDebit.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.tab_account:
                        setupToolbar(2);
                        menuSelected(2);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, FragmentAccount.newInstance(), FragmentAccount.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.tab_debit:
                        setupToolbar(3);
                        menuSelected(3);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, FragmentCreditDebit.newInstance(), FragmentCreditDebit.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.tab_setting:
                        setupToolbar(4);
                        menuSelected(4);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, FragmentSetting.newInstance(), FragmentSetting.class.getSimpleName())
                                .commit();
                        break;
                }
                return false;
            }
        });
    }

    private void setupToolbar(int position) {
        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        txt_toolbar = (AppCompatTextView) findViewById(R.id.txt_toolbar);
        toolbar.getMenu().clear();
        switch (position) {
            case 0:
                txt_toolbar.setText(getResources().getString(R.string.Summary));
                break;
            case 1:
                txt_toolbar.setText(getResources().getString(R.string.Credit));
                break;
            case 2:
                toolbar.inflateMenu(R.menu.home_menu_account);
                toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.menu_add_account) {
                            startActivity(new Intent(mContext, AddAccountActivity.class));
                        }
                        return false;
                    }
                });
                txt_toolbar.setText(getResources().getString(R.string.Accounts));
                break;
            case 3:
                txt_toolbar.setText(getResources().getString(R.string.Debit));
                break;
            case 4:
                txt_toolbar.setText(getResources().getString(R.string.Settings));
                break;

        }

    }

    private void menuSelected(int position) {
        for (int i=0;i<bottomBar.getItemCount();i++){
            if(i == position){
                bottomBar.setIconTintList(i,getResources().getColorStateList(R.color.colorPrimary));
                bottomBar.setTextTintList(i,getResources().getColorStateList(R.color.colorPrimary));
            } else{
                bottomBar.setIconTintList(i,getResources().getColorStateList(R.color.white));
                bottomBar.setTextTintList(i,getResources().getColorStateList(R.color.white));
            }
        }
    }

}
