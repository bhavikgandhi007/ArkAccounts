package com.arkaccountslite.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkaccountslite.R;
import com.arkaccountslite.model.account.ResponseAccountData;

import java.util.ArrayList;

public  class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder> {

    private final Context context;
    private ArrayList<ResponseAccountData> items;
    private OnItemClickListener<ResponseAccountData> responseAccountDataOnItemClickListener;

    public AccountsAdapter(Context context, ArrayList<ResponseAccountData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public AccountsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AccountsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false));
    }

    public void setResponseAccountDataOnItemClickListener(OnItemClickListener<ResponseAccountData> responseAccountDataOnItemClickListener) {
        this.responseAccountDataOnItemClickListener = responseAccountDataOnItemClickListener;
    }

    @Override
    public void onBindViewHolder(final AccountsViewHolder holder, final int position) {

        holder.textViewName.setText(items.get(position).getAccountName());
        holder.textViewPhone.setText(items.get(position).getAccountContactNo());
        holder.textViewAmount.setText(items.get(position).getOverallBalance());
        double n = Math.signum(Double.parseDouble(items.get(position).getOverallBalance()));
        if (n == 1.0) {
            holder.textViewAmount.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else if (n == -1.0) {
            holder.textViewAmount.setTextColor(Color.RED);
        } else {
            holder.textViewAmount.setTextColor(Color.BLACK);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (responseAccountDataOnItemClickListener != null) {
                    responseAccountDataOnItemClickListener.onItemClickListener(position, v, items.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ArrayList<ResponseAccountData> getItems() {
        return items;
    }

    public interface OnItemClickListener<T> {

        void onItemClickListener(int position, View v, T t);
    }

    public static class AccountsViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewName;
        AppCompatTextView textViewPhone;
        AppCompatTextView textViewAmount;

        public AccountsViewHolder(View itemView) {
            super(itemView);
            textViewName = (AppCompatTextView) itemView.findViewById(R.id.textViewName);
            textViewPhone = (AppCompatTextView) itemView.findViewById(R.id.textViewPhone);
            textViewAmount = (AppCompatTextView) itemView.findViewById(R.id.textViewAmount);
        }
    }
}
