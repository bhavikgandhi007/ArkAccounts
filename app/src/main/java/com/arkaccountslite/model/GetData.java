package com.arkaccountslite.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetData {

    @SerializedName("PKAccount")
    @Expose
    private String pKAccount;
    @SerializedName("FKUser")
    @Expose
    private String fKUser;
    @SerializedName("ApiAccessKey")
    @Expose
    private String apiAccessKey;

    public GetData(String fKUser, String apiAccessKey) {
        this.fKUser = fKUser;
        this.apiAccessKey = apiAccessKey;
    }

    public GetData(String pKAccount, String fKUser, String apiAccessKey) {
        this.pKAccount = pKAccount;
        this.fKUser = fKUser;
        this.apiAccessKey = apiAccessKey;
    }

    public String getpKAccount() {
        return pKAccount;
    }

    public void setpKAccount(String pKAccount) {
        this.pKAccount = pKAccount;
    }

    public String getfKUser() {
        return fKUser;
    }

    public void setfKUser(String fKUser) {
        this.fKUser = fKUser;
    }

    public String getApiAccessKey() {
        return apiAccessKey;
    }

    public void setApiAccessKey(String apiAccessKey) {
        this.apiAccessKey = apiAccessKey;

    }

    @Override
    public String toString() {
        return "GetData{" +
                "pKAccount='" + pKAccount + '\'' +
                ", fKUser='" + fKUser + '\'' +
                ", apiAccessKey='" + apiAccessKey + '\'' +
                '}';
    }
}