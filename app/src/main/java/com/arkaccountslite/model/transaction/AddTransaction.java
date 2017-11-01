package com.arkaccountslite.model.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddTransaction {

    @SerializedName("FKUser")
    @Expose
    private String fKUser;
    @SerializedName("FKAccount")
    @Expose
    private String fKAccount;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("FKTransactionType")
    @Expose
    private String fKTransactionType;
    @SerializedName("Note")
    @Expose
    private String note;
    @SerializedName("IsCompleted")
    @Expose
    private String isCompleted;
    @SerializedName("IsCash")
    @Expose
    private String isCash;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("ApiAccessKey")
    @Expose
    private String apiAccessKey;

    public AddTransaction(String fKUser, String fKAccount, String amount, String date, String fKTransactionType, String note, String isCompleted, String isCash, String dueDate, String apiAccessKey) {
        this.fKUser = fKUser;
        this.fKAccount = fKAccount;
        this.amount = amount;
        this.date = date;
        this.fKTransactionType = fKTransactionType;
        this.note = note;
        this.isCompleted = isCompleted;
        this.isCash = isCash;
        this.dueDate = dueDate;
        this.apiAccessKey = apiAccessKey;
    }

    public String getFKUser() {
        return fKUser;
    }

    public void setFKUser(String fKUser) {
        this.fKUser = fKUser;
    }

    public String getFKAccount() {
        return fKAccount;
    }

    public void setFKAccount(String fKAccount) {
        this.fKAccount = fKAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFKTransactionType() {
        return fKTransactionType;
    }

    public void setFKTransactionType(String fKTransactionType) {
        this.fKTransactionType = fKTransactionType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getIsCash() {
        return isCash;
    }

    public void setIsCash(String isCash) {
        this.isCash = isCash;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getApiAccessKey() {
        return apiAccessKey;
    }

    public void setApiAccessKey(String apiAccessKey) {
        this.apiAccessKey = apiAccessKey;
    }

    @Override
    public String toString() {
        return "AddTransaction{" +
                "fKUser='" + fKUser + '\'' +
                ", fKAccount='" + fKAccount + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", fKTransactionType='" + fKTransactionType + '\'' +
                ", note='" + note + '\'' +
                ", isCompleted='" + isCompleted + '\'' +
                ", isCash='" + isCash + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", apiAccessKey='" + apiAccessKey + '\'' +
                '}';
    }
}