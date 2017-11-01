package com.arkaccountslite.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arkaccountslite.R;
import com.arkaccountslite.model.account.AddAccount;
import com.arkaccountslite.model.account.ResponseAddAccount;
import com.arkaccountslite.model.auth.ResponseUserData;
import com.arkaccountslite.rest.service.AccountService;
import com.arkaccountslite.utill.PrefUtils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddAccountActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private AppCompatTextView txt_toolbar;
    private RadioGroup radioGroup_account;
    private RadioButton radio_debit;
    private static final String TAG = "AddAccountActivity";
    final private int REQUEST_CODE_ASK_PERMISSIONS_READ_CONTACT = 12345;
    private int PICK_CONTACT = 121;
    AppCompatEditText editTextName, editTextPhone, editTextBalance;
    ImageView imageContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = AddAccountActivity.this;
        setContentView(R.layout.activity_add_account);
        setupToolbar();
        initView();
    }

    private void initView() {
        radioGroup_account = (RadioGroup) findViewById(R.id.radioGroup_account);
        radio_debit = (RadioButton) findViewById(R.id.radio_debit);
        radio_debit.setChecked(true);
        editTextName = (AppCompatEditText) findViewById(R.id.editTextName);
        editTextPhone = (AppCompatEditText) findViewById(R.id.editTextPhone);
        editTextBalance = (AppCompatEditText) findViewById(R.id.editTextBalance);
        imageContactList = (ImageView) findViewById(R.id.imageContactList);
        imageContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    getReadContactPermission();
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, PICK_CONTACT);
                }
            }
        });


    }

    private void setupToolbar() {
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
        toolbar.inflateMenu(R.menu.menu_add_account);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_save_account) {
                    if (isNotEmpty(editTextName) && isNotEmpty(editTextPhone)) {
                        ResponseUserData responseUserData = new Gson().fromJson(PrefUtils.getUser(getApplicationContext()), ResponseUserData.class);

                        AddAccount addAccount = new AddAccount(String.valueOf(responseUserData.getPkUser()), editTextName.getText().toString(), editTextPhone.getText().toString(), editTextBalance.getText().toString().trim(), responseUserData.getApiKey());
                        Call<ResponseAddAccount> call = AccountService.addAccount(addAccount);
                        call.enqueue(new Callback<ResponseAddAccount>() {
                            @Override
                            public void onResponse(Call<ResponseAddAccount> call, Response<ResponseAddAccount> response) {
                                Log.d(TAG, "onResponse: " + response.body());
                                Log.d(TAG, "onResponse: " + response.body().getResponseData());
                                if (response.body().getResponseCode() == 101) {
                                    Toast.makeText(getApplicationContext(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                                } else if (response.body().getResponseCode() == 100) {
                                    Toast.makeText(getApplicationContext(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                                    setResult(RESULT_OK);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseAddAccount> call, Throwable t) {

                            }
                        });

                    } else {
                        if (!isNotEmpty(editTextName)) {
                            editTextName.setError("Enter Name");
                        }

                        if (!isNotEmpty(editTextPhone)) {
                            editTextPhone.setError("Enter Contact Number");
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CONTACT) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                Cursor c = getContentResolver().query(contactData, null, null, null, null);
                if (c != null) {
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                        editTextName.setText(name);
                        int columnIndex_ID = c.getColumnIndex(ContactsContract.Contacts._ID);
                        String contactID = c.getString(columnIndex_ID);

                        int columnIndex_HASPHONENUMBER = c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                        String stringHasPhoneNumber = c.getString(columnIndex_HASPHONENUMBER);

                        if (stringHasPhoneNumber.equalsIgnoreCase("1")) {
                            Cursor cursorNum = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactID,
                                    null,
                                    null);

                            //Get the first phone number
                            if (cursorNum.moveToNext()) {
                                int columnIndex_number = cursorNum.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                                String stringNumber = cursorNum.getString(columnIndex_number);
                                editTextPhone.setText(stringNumber);
                            }

                        } else {
                            Log.d(TAG, "onActivityResult: ");
                        }
                    }
                    c.close();
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void getReadContactPermission() {
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                        REQUEST_CODE_ASK_PERMISSIONS_READ_CONTACT);
                return;
            }
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_ASK_PERMISSIONS_READ_CONTACT);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS_READ_CONTACT:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, PICK_CONTACT);
                } else {
                    // Permission Denied
                    Toast.makeText(AddAccountActivity.this, "Read Contact Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private boolean isNotEmpty(EditText editText) {
        return editText != null && editText.getText().toString().trim().length() > 1;
    }


}
