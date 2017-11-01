package com.arkaccountslite.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUserData {

    @SerializedName("IsVerified")
    @Expose
    private boolean isVerified;
    @SerializedName("PkUser")
    @Expose
    private int pkUser;
    @SerializedName("UserCountryCode")
    @Expose
    private String userCountryCode;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("DeviceType")
    @Expose
    private String deviceType;
    @SerializedName("DeviceToken")
    @Expose
    private String deviceToken;
    @SerializedName("ApiKey")
    @Expose
    private String apiKey;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("OTP")
    @Expose
    private String oTP;
    @SerializedName("IsDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("UserContactNo")
    @Expose
    private String userContactNo;
    @SerializedName("ApiAccessKey")
    @Expose
    private Object apiAccessKey;

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public int getPkUser() {
        return pkUser;
    }

    public void setPkUser(int pkUser) {
        this.pkUser = pkUser;
    }

    public String getUserCountryCode() {
        return userCountryCode;
    }

    public void setUserCountryCode(String userCountryCode) {
        this.userCountryCode = userCountryCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOTP() {
        return oTP;
    }

    public void setOTP(String oTP) {
        this.oTP = oTP;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContactNo() {
        return userContactNo;
    }

    public void setUserContactNo(String userContactNo) {
        this.userContactNo = userContactNo;
    }

    public Object getApiAccessKey() {
        return apiAccessKey;
    }

    public void setApiAccessKey(Object apiAccessKey) {
        this.apiAccessKey = apiAccessKey;
    }

    @Override
    public String toString() {
        return "ResponseUserData{" +
                "isVerified=" + isVerified +
                ", pkUser=" + pkUser +
                ", userCountryCode='" + userCountryCode + '\'' +
                ", password='" + password + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", name='" + name + '\'' +
                ", oTP='" + oTP + '\'' +
                ", isDeleted=" + isDeleted +
                ", userName='" + userName + '\'' +
                ", userContactNo='" + userContactNo + '\'' +
                ", apiAccessKey=" + apiAccessKey +
                '}';
    }
}