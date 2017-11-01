package com.arkaccountslite.model.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddAccount {

    @SerializedName("FKUser")
    @Expose
    private String fKUser;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("AccountContactNo")
    @Expose
    private String accountContactNo;
    @SerializedName("OverallBalance")
    @Expose
    private String overallBalance;
    @SerializedName("ApiAccessKey")
    @Expose
    private String apiAccessKey;

    public AddAccount(String fKUser, String accountName, String accountContactNo, String overallBalance, String apiAccessKey) {
        this.fKUser = fKUser;
        this.accountName = accountName;
        this.accountContactNo = accountContactNo;
        this.overallBalance = overallBalance;
        this.apiAccessKey = apiAccessKey;
    }

    public String getFKUser() {
        return fKUser;
    }

    public void setFKUser(String fKUser) {
        this.fKUser = fKUser;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountContactNo() {
        return accountContactNo;
    }

    public void setAccountContactNo(String accountContactNo) {
        this.accountContactNo = accountContactNo;
    }

    public String getOverallBalance() {
        return overallBalance;
    }

    public void setOverallBalance(String overallBalance) {
        this.overallBalance = overallBalance;
    }

    public String getApiAccessKey() {
        return apiAccessKey;
    }

    public void setApiAccessKey(String apiAccessKey) {
        this.apiAccessKey = apiAccessKey;
    }

    @Override
    public String toString() {
        return "ResponseAddAccount{" +
                "fKUser='" + fKUser + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountContactNo='" + accountContactNo + '\'' +
                ", overallBalance='" + overallBalance + '\'' +
                ", apiAccessKey='" + apiAccessKey + '\'' +
                '}';
    }
}