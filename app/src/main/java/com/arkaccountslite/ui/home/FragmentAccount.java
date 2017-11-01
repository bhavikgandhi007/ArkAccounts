package com.arkaccountslite.ui.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arkaccountslite.R;
import com.arkaccountslite.adapter.AccountsAdapter;
import com.arkaccountslite.model.GetData;
import com.arkaccountslite.model.account.ResponseAccount;
import com.arkaccountslite.model.account.ResponseAccountData;
import com.arkaccountslite.model.account.ResponseDeleteAccount;
import com.arkaccountslite.model.auth.ResponseUserData;
import com.arkaccountslite.rest.service.AccountService;
import com.arkaccountslite.utill.PrefUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAccount extends Fragment {

    private View view;
    private RadioGroup radioGroup_account;
    private RadioButton radio_all;
    private RecyclerView recyclerViewAccounts;
    ArrayList<ResponseAccountData> responseAccountDatas = new ArrayList<>();
    private AccountsAdapter accountsAdapter;
    private ProgressDialog progressDialog;
    public static final String TAG = "AccountsFragment";
    private Paint p = new Paint();
    private LinearLayout empty_list_linear;


    public FragmentAccount() {

    }

    public static FragmentAccount newInstance() {
        Bundle args = new Bundle();
        FragmentAccount fragment = new FragmentAccount();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_account_list, null);
        initView(view);
        return view;
    }

    public void initView(View view) {
        radioGroup_account = (RadioGroup) view.findViewById(R.id.radioGroup_account);
        empty_list_linear = (LinearLayout) view.findViewById(R.id.empty_list_linear);
        radio_all = (RadioButton) view.findViewById(R.id.radio_all);
        radio_all.setChecked(true);
        recyclerViewAccounts = (RecyclerView) view.findViewById(R.id.recyclerViewAccounts);
        recyclerViewAccounts.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewAccounts.setLayoutManager(linearLayoutManager);
        radioGroup_account.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_credit:
                        ArrayList<ResponseAccountData> creditAccounts = new ArrayList<ResponseAccountData>();
                        for (int j = 0; j < responseAccountDatas.size(); j++) {
                            if (responseAccountDatas.get(j).getAccountType() == 2) {
                                creditAccounts.add(responseAccountDatas.get(j));
                            }
                        }
                        accountsAdapter = new AccountsAdapter(getActivity(), creditAccounts);
                        recyclerViewAccounts.setAdapter(accountsAdapter);
                        break;
                    case R.id.radio_debit:
                        ArrayList<ResponseAccountData> debitAccounts = new ArrayList<ResponseAccountData>();
                        for (int j = 0; j < responseAccountDatas.size(); j++) {
                            if (responseAccountDatas.get(j).getAccountType() == 1) {
                                debitAccounts.add(responseAccountDatas.get(j));
                            }
                        }
                        accountsAdapter = new AccountsAdapter(getActivity(), debitAccounts);
                        recyclerViewAccounts.setAdapter(accountsAdapter);
                        break;
                    case R.id.radio_all:
                        accountsAdapter = new AccountsAdapter(getActivity(), responseAccountDatas);
                        recyclerViewAccounts.setAdapter(accountsAdapter);
                        break;
                }

            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                        if (direction == ItemTouchHelper.LEFT) {
                            progressDialog = new ProgressDialog(getActivity());
                            progressDialog.setMessage("Please wait, removing account...");
                            progressDialog.show();

                            final int position = viewHolder.getAdapterPosition();
                            ResponseUserData responseUserData = new Gson().fromJson(PrefUtils.getUser(getActivity()), ResponseUserData.class);

                            GetData getData = new GetData(String.valueOf(accountsAdapter.getItems().get(position).getPKAccount()), String.valueOf(responseUserData.getPkUser()), responseUserData.getApiKey());
                            Call<ResponseDeleteAccount> call = AccountService.deleteAccount(getData);
                            call.enqueue(new Callback<ResponseDeleteAccount>() {
                                @Override
                                public void onResponse(Call<ResponseDeleteAccount> call, Response<ResponseDeleteAccount> response) {
                                    Log.d(TAG, "onResponse: " + response.body());
                                    Log.d(TAG, "onResponse: " + response.body().getResponseData());
                                    if (progressDialog != null && progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }

                                    if (response.body().getResponseCode() == 101) {
                                        Toast.makeText(getActivity(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                                    } else if (response.body().getResponseCode() == 100) {
                                        Toast.makeText(getActivity(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                                        if (radio_all.isChecked()) {
                                            responseAccountDatas.remove(position);
                                        } else {
                                            responseAccountDatas.remove(responseAccountDatas.indexOf(accountsAdapter.getItems().get(position)));
                                            accountsAdapter.getItems().remove(position);
                                        }
                                        accountsAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseDeleteAccount> call, Throwable t) {
                                    if (progressDialog != null && progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    accountsAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }

                    @Override
                    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                        Bitmap icon;
                        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                            View itemView = viewHolder.itemView;
                            float height = (float) itemView.getBottom() - (float) itemView.getTop();
                            float width = height / 3;

                            if (dX > 0) {
                               /* p.setColor(Color.parseColor("#388E3C"));
                                RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                                c.drawRect(background,p);
                                icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white_24dp);
                                RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                                c.drawBitmap(icon,null,icon_dest,p);*/
                            } else {
                                p.setColor(Color.parseColor("#D32F2F"));
                                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                                c.drawRect(background, p);
                                icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_plus);
                                RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                                c.drawBitmap(icon, null, icon_dest, p);
                            }
                        }
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }
                });
        itemTouchHelper.attachToRecyclerView(recyclerViewAccounts);

        radioGroup_account.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });
        doApiCall();
    }

    private void doApiCall() {

        ResponseUserData responseUserData = new Gson().fromJson(PrefUtils.getUser(getActivity()), ResponseUserData.class);

        GetData getData = new GetData(String.valueOf(responseUserData.getPkUser()), responseUserData.getApiKey());
        Call<ResponseAccount> call = AccountService.getAccounts(getData);
        call.enqueue(new Callback<ResponseAccount>() {
            @Override
            public void onResponse(Call<ResponseAccount> call, Response<ResponseAccount> response) {
                Log.d(TAG, "onResponse: " + response.body());
                Log.d(TAG, "onResponse: " + response.body().getResponseData());
                if (response.body().getResponseCode() == 101) {
                    Toast.makeText(getActivity(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getResponseCode() == 100) {
                    Toast.makeText(getActivity(), response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    responseAccountDatas = response.body().getResponseData();
                    if (response.body().getResponseData().size() > 0) {
                        empty_list_linear.setVisibility(View.GONE);
                        recyclerViewAccounts.setVisibility(View.VISIBLE);
                        accountsAdapter = new AccountsAdapter(getActivity(), responseAccountDatas);
                        recyclerViewAccounts.setAdapter(accountsAdapter);
                    } else {
                        empty_list_linear.setVisibility(View.VISIBLE);
                        recyclerViewAccounts.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAccount> call, Throwable t) {
                t.printStackTrace();
                empty_list_linear.setVisibility(View.VISIBLE);
                recyclerViewAccounts.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                if (accountsAdapter != null && accountsAdapter.getItems().size() > 0) {
                    accountsAdapter.getItems().clear();
                    accountsAdapter.notifyDataSetChanged();
                }
                doApiCall();
            }
        }
    }
}
