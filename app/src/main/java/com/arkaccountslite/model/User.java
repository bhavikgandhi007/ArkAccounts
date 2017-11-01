package com.arkaccountslite.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("UserCountryCode")
    @Expose
    private String userCountryCode;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("UserContactNo")
    @Expose
    private String userContactNo;
    @SerializedName("Username")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("DeviceType")
    @Expose
    private String deviceType;
    @SerializedName("DeviceToken")
    @Expose
    private String deviceToken;

    public User(String userCountryCode, String name, String userContactNo, String password) {
        this.userCountryCode = userCountryCode;
        this.name = name;
        this.userContactNo = userContactNo;
        this.password = password;
        this.deviceToken = "";
        this.deviceType = "Android";
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.deviceToken = "";
        this.deviceType = "Android";
    }

    public String getUserCountryCode() {
        return userCountryCode;
    }

    public void setUserCountryCode(String userCountryCode) {
        this.userCountryCode = userCountryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserContactNo() {
        return userContactNo;
    }

    public void setUserContactNo(String userContactNo) {
        this.userContactNo = userContactNo;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userCountryCode='" + userCountryCode + '\'' +
                ", name='" + name + '\'' +
                ", userContactNo='" + userContactNo + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                '}';
    }
}