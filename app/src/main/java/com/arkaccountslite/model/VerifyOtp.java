package com.arkaccountslite.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtp {

    @SerializedName("PkUser")
    @Expose
    private String pkUser;
    @SerializedName("OTP")
    @Expose
    private String oTP;

    public VerifyOtp(String pkUser, String oTP) {
        this.pkUser = pkUser;
        this.oTP = oTP;
    }

    public VerifyOtp(String pkUser) {
        this.pkUser = pkUser;
    }

    public String getPkUser() {
        return pkUser;
    }

    public void setPkUser(String pkUser) {
        this.pkUser = pkUser;
    }

    public String getOTP() {
        return oTP;
    }

    public void setOTP(String oTP) {
        this.oTP = oTP;
    }

    @Override
    public String toString() {
        return "GetData{" +
                "pkUser='" + pkUser + '\'' +
                ", oTP='" + oTP + '\'' +
                '}';
    }
}