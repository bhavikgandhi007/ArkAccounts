package com.arkaccountslite.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arkaccountslite.R;
import com.arkaccountslite.model.User;
import com.arkaccountslite.model.auth.ResponseUser;
import com.arkaccountslite.rest.service.AuthorizationService;
import com.arkaccountslite.ui.home.HomeActivity;
import com.arkaccountslite.utill.PrefUtils;
import com.arkaccountslite.widget.SimpleTextWatcher;
import com.google.gson.Gson;
import com.rilixtech.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity {


    private static final String TAG = "SignUpActivity";
    final private int REQUEST_CODE_ASK_PERMISSIONS_READ_SMS = 1234;

    private Context mContext;
    private AppCompatButton btn_signup, btn_already_account;
    TextInputLayout textInputLayoutName, textInputLayoutPassword, textInputLayoutPhone,
            textInputLayoutConfirmPassword;
    AppCompatEditText editTextName, editTextPassword, editTextPhone, editTextConfirmPassword;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SignupActivity.this;
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView() {
        textInputLayoutName = (TextInputLayout) findViewById(R.id.input_name);
        textInputLayoutPhone = (TextInputLayout) findViewById(R.id.input_contact_number);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.input_password);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.input_confirm_passwrod);
        countryCodePicker = (CountryCodePicker) findViewById(R.id.countryCodePicker);


        editTextName = (AppCompatEditText) findViewById(R.id.edit_name);
        editTextPassword = (AppCompatEditText) findViewById(R.id.edit_password);
        editTextPhone = (AppCompatEditText) findViewById(R.id.edit_contact_number);
        editTextConfirmPassword = (AppCompatEditText) findViewById(R.id.edit_confirm_password);

        countryCodePicker.enableHint(false);
        countryCodePicker.registerPhoneNumberTextView(editTextPhone);

        editTextName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputLayoutName.isErrorEnabled()) {
                    textInputLayoutName.setError(null);
                    textInputLayoutName.setErrorEnabled(false);
                }
            }
        });

        editTextPhone.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputLayoutPhone.isErrorEnabled()) {
                    textInputLayoutPhone.setError(null);
                    textInputLayoutPhone.setErrorEnabled(false);
                }
            }
        });

        editTextPassword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputLayoutPassword.isErrorEnabled()) {
                    textInputLayoutPassword.setError(null);
                    textInputLayoutPassword.setErrorEnabled(false);
                }
            }
        });

        editTextConfirmPassword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputLayoutConfirmPassword.isErrorEnabled()) {
                    textInputLayoutConfirmPassword.setError(null);
                    textInputLayoutConfirmPassword.setErrorEnabled(false);
                }
            }
        });
        btn_signup = (AppCompatButton) findViewById(R.id.btn_signup);
        btn_already_account = (AppCompatButton) findViewById(R.id.btn_already_account);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotEmpty(editTextName)
                        && isValidMobile(editTextPhone)
                        && isValidPassword(editTextPassword)
                        && isValidPassword(editTextConfirmPassword)
                        && TextUtils.equals(editTextConfirmPassword.getText().toString(), editTextPassword.getText().toString())) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        getSmsPermission();
                    } else {
                        doApiCall();
                    }
                } else {
                    if (!isNotEmpty(editTextName)) {
                        textInputLayoutName.setError("Enter Name");
                    }

                    if (!isValidMobile(editTextPhone)) {
                        textInputLayoutPhone.setError("Enter valid Phone Number");
                    }

                    if (!isValidPassword(editTextPassword)) {
                        textInputLayoutPassword.setError("Password should be at least 6 characters long");
                    }

                    if (!isValidPassword(editTextConfirmPassword)) {
                        textInputLayoutConfirmPassword.setError("Password should be at least 6 characters long");
                    }

                    if (!TextUtils.equals(editTextConfirmPassword.getText().toString(), editTextPassword.getText().toString())) {
                        textInputLayoutConfirmPassword.setError("Confirm password does not match");
                    }
                }
               // startActivity(new Intent(mContext, HomeActivity.class));
                //finish();
            }
        });

        btn_already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        });
    }


    private boolean isNotEmpty(EditText editText) {
        return editText != null && editText.getText().toString().trim().length() > 1;
    }

    private boolean isValidMobile(EditText editText) {
        return android.util.Patterns.PHONE.matcher(editText.getText().toString().trim()).matches();
    }

    private boolean isValidPassword(EditText editText) {
        return editText.getText().toString().length() >= 6;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS_READ_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    doApiCall();
                } else {
                    // Permission Denied
                    Toast.makeText(SignupActivity.this, "Read Sms Permission Denied", Toast.LENGTH_SHORT).show();
                    doApiCall();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void doApiCall() {

        User user = new User(countryCodePicker.getSelectedCountryCode(), editTextName.getText().toString(), editTextPhone.getText().toString(), editTextPassword.getText().toString());
        Call<ResponseUser> call = AuthorizationService.signUp(user);
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
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), VerifyActivity.class));
                    }
                    finish();
                    finishAffinity();
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Log.e(TAG, "onFailure: " + call);
                Log.e(TAG, "onFailure: " + t);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void getSmsPermission() {
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_SMS);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS)) {
                requestPermissions(new String[]{Manifest.permission.READ_SMS},
                        REQUEST_CODE_ASK_PERMISSIONS_READ_SMS);
                return;
            }
            requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    REQUEST_CODE_ASK_PERMISSIONS_READ_SMS);
            return;
        }

        doApiCall();
    }
}
