package com.arkaccountslite.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.arkaccountslite.R;
import com.arkaccountslite.ui.LoginActivity;
import com.arkaccountslite.utill.PrefUtils;
import com.arkaccountslite.widget.LocaleHelper;

import java.util.ArrayList;

import io.blackbox_vision.wheelview.view.WheelView;


public class FragmentSetting extends Fragment {


    private View view;
    private Context mContext;
    private EditText edit_duedays;
    private SwitchCompat switch_reminder,switch_push_notification;
    private AppCompatTextView txt_logout,txt_language;


    public FragmentSetting() {

    }

    public static FragmentSetting newInstance() {
        Bundle args = new Bundle();
        FragmentSetting fragment = new FragmentSetting();
        fragment.setArguments(args);
        return fragment;
    }


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
        txt_language = (AppCompatTextView) view.findViewById(R.id.txt_language);
        txt_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLanguageDialog();
            }
        });
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

                PrefUtils.markLoginDone(getActivity(), false);
                PrefUtils.setUser(getActivity(), null);
                PrefUtils.setAccountData(getActivity(), null);
                PrefUtils.setPrefIsAllowReminder(getActivity(), false);
                PrefUtils.setPrefIsAllowPush(getActivity(), false);
                PrefUtils.setPrefDefaultDueDays(getActivity(), 30);

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

    public void showLanguageDialog() {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity(),R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_language);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setTitle("Select Lanaguage");

        ArrayList<String> languages = new ArrayList<>();
        languages.add("English");
        languages.add(getResources().getString(R.string.hindi));
        languages.add(getResources().getString(R.string.gujarati));
        final WheelView wheelView = (WheelView) dialog. findViewById(R.id.loop_view);
        wheelView.setInitPosition(1);
        wheelView.setCanLoop(false);
        wheelView.setItems(languages);

        AppCompatTextView btn_save = (AppCompatTextView) dialog.findViewById(R.id.btn_save);
        AppCompatTextView btn_cancel = (AppCompatTextView) dialog.findViewById(R.id.btn_cancel);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wheelView.getSelectedItem() == 0){
                    LocaleHelper.setLocale(mContext,"en");
                } else if(wheelView.getSelectedItem() == 1){
                    LocaleHelper.setLocale(mContext,"hi");
                } else if(wheelView.getSelectedItem() == 2){
                    LocaleHelper.setLocale(mContext,"gu");
                }
                dialog.dismiss();
                Intent intent = new Intent(getActivity(),HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("currentItem",4);
                startActivity(intent);
                getActivity().overridePendingTransition( 0, 0);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
