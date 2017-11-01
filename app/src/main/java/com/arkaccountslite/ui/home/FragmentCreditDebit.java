package com.arkaccountslite.ui.home;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.arkaccountslite.R;
import com.arkaccountslite.model.auth.ResponseUserData;
import com.arkaccountslite.model.transaction.AddTransaction;
import com.arkaccountslite.model.transaction.ResponseAddTransaction;
import com.arkaccountslite.rest.service.TransactionService;
import com.arkaccountslite.service.AlarmReceiver;
import com.arkaccountslite.utill.PrefUtils;
import com.arkaccountslite.utill.Utility;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCreditDebit extends Fragment {


    private static final String TAG = "DebitCreditFragment";
    private View view;
    private Context mContext;
    public AppCompatButton btn_credit, btn_debit;
    public AppCompatTextView edit_duedate, edit_date;
    private DatePickerDialog fromDatePickerDialog, toDatePickerDialog;
    private AppCompatEditText editTextBalance, editTextNote, edit_duedays;


    public FragmentCreditDebit() {

    }

    public static FragmentCreditDebit newInstance() {
        Bundle args = new Bundle();
        FragmentCreditDebit fragment = new FragmentCreditDebit();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frgment_add_transaction, null);
        mContext = getActivity();
        initView(view);
        if (getArguments().getBoolean("isCredit")) {
            btn_credit.setVisibility(View.VISIBLE);
            btn_debit.setVisibility(View.GONE);
        } else {
            btn_debit.setVisibility(View.VISIBLE);
            btn_credit.setVisibility(View.GONE);
        }
        return view;
    }

    private void initView(View view) {
        edit_date = (AppCompatTextView) view.findViewById(R.id.edit_date);
        edit_duedate = (AppCompatTextView) view.findViewById(R.id.edit_duedate);
        edit_duedays = (AppCompatEditText) view.findViewById(R.id.edit_duedays);
        editTextBalance = (AppCompatEditText) view.findViewById(R.id.edit_balance);
        editTextNote = (AppCompatEditText) view.findViewById(R.id.edit_notes);
        btn_credit = (AppCompatButton) view.findViewById(R.id.btn_credit);
        btn_debit = (AppCompatButton) view.findViewById(R.id.btn_debit);

        int due_days = PrefUtils.getPrefDefaultDueDays(getActivity());
        edit_duedays.setText(String.valueOf(due_days));
        btn_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setAlarm(2017, 10, 30);
                callTransaction("0", "2");
            }
        });

        btn_debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callTransaction("0", "1");
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        final SimpleDateFormat spf = new SimpleDateFormat("EEE MMM dd hh:mm:ss 'GMT'Z yyyy");
        fromDatePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar startDate = Calendar.getInstance();
                startDate.set(year, monthOfYear, dayOfMonth);
                edit_date.setText(Utility.convertDate(spf.format(startDate.getTime())));
                Calendar newDate1 = Calendar.getInstance();
                newDate1.setTime(startDate.getTime());
                newDate1.add(Calendar.DATE, 31);
                edit_duedate.setText(Utility.convertDate(spf.format(newDate1.getTime())));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edit_duedate.setText(Utility.convertDate(newDate.toString()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        edit_duedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                toDatePickerDialog.show();
            }
        });
        edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                fromDatePickerDialog.show();
            }
        });

    }

    public void setAlarm(int year, int month, int date) {
        Intent myIntent = new Intent(mContext, AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(mContext.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, myIntent, 0);

        Calendar objCalendar = Calendar.getInstance();
        objCalendar.set(Calendar.YEAR, year);
        //objCalendar.set(Calendar.YEAR, objCalendar.get(Calendar.YEAR));
        objCalendar.set(Calendar.MONTH, month);
        objCalendar.set(Calendar.DAY_OF_MONTH, date);
        objCalendar.set(Calendar.HOUR_OF_DAY, 10);
        objCalendar.set(Calendar.MINUTE, 0);
        objCalendar.set(Calendar.SECOND, 0);
        objCalendar.set(Calendar.MILLISECOND, 0);
        objCalendar.set(Calendar.AM_PM, Calendar.AM);
        alarmManager.set(AlarmManager.RTC_WAKEUP, objCalendar.getTimeInMillis(), pendingIntent);
    }

    public void callTransaction(String id, String creditDebit) {
        if (isNotEmpty(editTextBalance) && id != null) {
            ResponseUserData responseUserData = new Gson().fromJson(PrefUtils.getUser(getActivity()), ResponseUserData.class);

            AddTransaction addTransaction = new AddTransaction(String.valueOf(responseUserData.getPkUser()), id, editTextBalance.getText().toString(), edit_date.getText().toString(), creditDebit, editTextNote.getText().toString().trim(), "0", "0", edit_duedate.getText().toString(), responseUserData.getApiKey());
            Call<ResponseAddTransaction> call = TransactionService.addTransaction(addTransaction);
            call.enqueue(new Callback<ResponseAddTransaction>() {
                @Override
                public void onResponse(Call<ResponseAddTransaction> call, Response<ResponseAddTransaction> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    Log.d(TAG, "onResponse: " + response.body().getResponseData());
                    if (response.body().getResponseCode() == 101) {
                        Toast.makeText(getActivity(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else if (response.body().getResponseCode() == 100) {
                        Toast.makeText(getActivity(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAddTransaction> call, Throwable t) {

                }
            });
        } else {
            if (!isNotEmpty(editTextBalance)) {
                editTextBalance.setError("Enter Balance");
            }
            if (id == null) {
                Toast.makeText(getActivity(), "Select Account Name", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNotEmpty(EditText editText) {
        return editText != null && editText.getText().toString().trim().length() > 1;
    }
}
