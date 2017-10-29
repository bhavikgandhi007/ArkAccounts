package com.arkaccountslite.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.arkaccountslite.R;


public class FragmentAccount extends Fragment {

    private View view;
    private RadioGroup radioGroup_account;
    private RadioButton radio_all;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_account_list, null);
        initView(view);
        return view;
    }

    public void initView(View view){
        radioGroup_account = (RadioGroup) view.findViewById(R.id.radioGroup_account);
        radio_all = (RadioButton) view.findViewById(R.id.radio_all);
        radio_all.setChecked(true);
        radioGroup_account.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });
    }
}
