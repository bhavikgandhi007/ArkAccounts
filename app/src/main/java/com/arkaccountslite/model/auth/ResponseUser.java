package com.arkaccountslite.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUser {

    @SerializedName("ResponseData")
    @Expose
    private List<ResponseUserData> responseData = null;
    @SerializedName("ResponseCode")
    @Expose
    private int responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("ResponseType")
    @Expose
    private String responseType;

    public List<ResponseUserData> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<ResponseUserData> responseData) {
        this.responseData = responseData;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    @Override
    public String toString() {
        return "ResponseUser{" +
                "responseData=" + responseData +
                ", responseCode=" + responseCode +
                ", responseMessage='" + responseMessage + '\'' +
                ", responseType='" + responseType + '\'' +
                '}';
    }
}