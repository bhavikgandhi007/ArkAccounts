package com.arkaccountslite.model.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAccountData {

    @SerializedName("PKAccount")
    @Expose
    private int pKAccount;
    @SerializedName("FKUser")
    @Expose
    private int fKUser;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("AccountContactNo")
    @Expose
    private String accountContactNo;
    @SerializedName("OverallBalance")
    @Expose
    private String overallBalance;
    @SerializedName("OverallDebit")
    @Expose
    private String overallDebit;
    @SerializedName("OverallCredit")
    @Expose
    private String overallCredit;
    @SerializedName("AccountType")
    @Expose
    private int accountType;
    @SerializedName("ApiAccessKey")
    @Expose
    private Object apiAccessKey;
    @SerializedName("FKTransactionType")
    @Expose
    private int fKTransactionType;

    public int getPKAccount() {
        return pKAccount;
    }

    public void setPKAccount(int pKAccount) {
        this.pKAccount = pKAccount;
    }

    public int getFKUser() {
        return fKUser;
    }

    public void setFKUser(int fKUser) {
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

    public String getOverallDebit() {
        return overallDebit;
    }

    public void setOverallDebit(String overallDebit) {
        this.overallDebit = overallDebit;
    }

    public String getOverallCredit() {
        return overallCredit;
    }

    public void setOverallCredit(String overallCredit) {
        this.overallCredit = overallCredit;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public Object getApiAccessKey() {
        return apiAccessKey;
    }

    public void setApiAccessKey(Object apiAccessKey) {
        this.apiAccessKey = apiAccessKey;
    }

    public int getFKTransactionType() {
        return fKTransactionType;
    }

    public void setFKTransactionType(int fKTransactionType) {
        this.fKTransactionType = fKTransactionType;
    }

    @Override
    public String toString() {
        return "ResponseAccountData{" +
                "pKAccount=" + pKAccount +
                ", fKUser=" + fKUser +
                ", accountName='" + accountName + '\'' +
                ", accountContactNo='" + accountContactNo + '\'' +
                ", overallBalance='" + overallBalance + '\'' +
                ", overallDebit='" + overallDebit + '\'' +
                ", overallCredit='" + overallCredit + '\'' +
                ", accountType=" + accountType +
                ", apiAccessKey=" + apiAccessKey +
                ", fKTransactionType=" + fKTransactionType +
                '}';
    }
}