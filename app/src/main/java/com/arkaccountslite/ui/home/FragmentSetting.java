package com.arkaccountslite.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.arkaccountslite.R;
import com.arkaccountslite.ui.LoginActivity;
import com.arkaccountslite.utill.PrefUtils;


public class FragmentSetting extends Fragment {


    private View view;
    private Context mContext;
    private EditText edit_duedays;
    private SwitchCompat switch_reminder,switch_push_notification;
    private AppCompatTextView txt_logout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, null);
        mContext = getActivity();
        initView(view);
        setSettingData();
        return view;
    }

    public void initView(View view){
        edit_duedays = (EditText) view.findViewById(R.id.edit_duedays);
        switch_reminder = (SwitchCompat) view.findViewById(R.id.switch_reminder);
        switch_push_notification = (SwitchCompat) view.findViewById(R.id.switch_push_notification);
        txt_logout = (AppCompatTextView) view.findViewById(R.id.txt_logout);
        edit_duedays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!edit_duedays.getText().toString().isEmpty())
                 PrefUtils.setPrefDefaultDueDays(mContext,Integer.parseInt(edit_duedays.getText().toString().trim()));
            }
        });
        switch_push_notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                PrefUtils.setPrefIsAllowPush(mContext,ischecked);
            }
        });
        switch_reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                PrefUtils.setPrefIsAllowReminder(mContext,b);
            }
        });
        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtils.setLoggedIn(mContext,false);
                startActivity(new Intent(mContext, LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    public void setSettingData(){
        edit_duedays.setText(String.valueOf(PrefUtils.getPrefDefaultDueDays(mContext)));
        switch_reminder.setChecked(PrefUtils.isAllowReminder(mContext));
        switch_push_notification.setChecked(PrefUtils.isAllowPush(mContext));
    }
}
