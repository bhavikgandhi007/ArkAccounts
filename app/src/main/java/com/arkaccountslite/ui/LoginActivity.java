package com.arkaccountslite.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arkaccountslite.R;
import com.arkaccountslite.model.User;
import com.arkaccountslite.model.auth.ResponseUser;
import com.arkaccountslite.model.auth.ResponseUserData;
import com.arkaccountslite.rest.service.AuthorizationService;
import com.arkaccountslite.ui.home.HomeActivity;
import com.arkaccountslite.utill.PrefUtils;
import com.google.gson.Gson;
import com.rilixtech.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private Context mContext;
    private AppCompatButton btn_next, btn_signup;
    private static final String TAG = "LoginActivity";

    CountryCodePicker countryCodePicker;
    AppCompatEditText editTextPassword;
    AppCompatEditText editTextPhone;
    TextInputLayout textInputLayoutPhone;
    TextInputLayout textInputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PrefUtils.isLoginDone(getApplicationContext())) {
            ResponseUserData responseUserData = new Gson().fromJson(PrefUtils.getUser(getApplicationContext()), ResponseUserData.class);
            if (!responseUserData.isIsVerified()) {
                startActivity(new Intent(getApplicationContext(), VerifyActivity.class));
            } else {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
            finish();
        }
        mContext = LoginActivity.this;
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        countryCodePicker = (CountryCodePicker) findViewById(R.id.countryCodePicker);
        editTextPassword = (AppCompatEditText) findViewById(R.id.edit_password);
        editTextPhone = (AppCompatEditText) findViewById(R.id.edit_contact_number);
        textInputLayoutPhone = (TextInputLayout) findViewById(R.id.input_contact_number);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.input_password);
        btn_next = (AppCompatButton) findViewById(R.id.btn_next);
        btn_signup = (AppCompatButton) findViewById(R.id.btn_signup);

        countryCodePicker.enableHint(false);
        countryCodePicker.registerPhoneNumberTextView(editTextPhone);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidMobile(editTextPhone) && isValidPassword(editTextPassword)) {
                    Log.d(TAG, "onClick: " + countryCodePicker.getPhoneNumber().getNationalNumber());

                    User user = new User(String.valueOf(countryCodePicker.getPhoneNumber().getNationalNumber()), editTextPassword.getText().toString());
                    Call<ResponseUser> call = AuthorizationService.login(user);
                    call.enqueue(new Callback<ResponseUser>() {
                        @Override
                        public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                            Log.d(TAG, "onResponse: " + response.body());
                            Log.d(TAG, "onResponse: " + response.body().getResponseData());
                            if (response.body().getResponseCode() == 101) {
                                Toast.makeText(getApplicationContext(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                            } else if (response.body().getResponseCode() == 100) {
                                Toast.makeText(getApplicationContext(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                                PrefUtils.setUser(getApplicationContext(), new Gson().toJson(response.body().getResponseData().get(0)));
                                PrefUtils.markLoginDone(getApplicationContext(), true);

                                if (response.body().getResponseData().get(0).isIsVerified()) {
                                    startActivity(new Intent(getApplicationContext(), LanguageActivity.class));
                                } else {
                                    startActivity(new Intent(getApplicationContext(), VerifyActivity.class));
                                }

                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseUser> call, Throwable t) {
                           t.printStackTrace();
                        }
                    });
                } else {

                    if (!isValidMobile(editTextPhone)) {
                        textInputLayoutPhone.setError("Enter valid Phone Number");
                    }

                    if (!isValidPassword(editTextPassword)) {
                        textInputLayoutPassword.setError("Password should be at least 6 characters long");
                    }
                }

            }

        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Todo btn signup click
                startActivity(new Intent(mContext, SignupActivity.class));
                finish();
            }
        });

    }

    private boolean isValidMobile(EditText editText) {
        return android.util.Patterns.PHONE.matcher(editText.getText().toString().trim()).matches();
    }

    private boolean isValidPassword(EditText editText) {
        return editText.getText().toString().length() >= 6;
    }
}
