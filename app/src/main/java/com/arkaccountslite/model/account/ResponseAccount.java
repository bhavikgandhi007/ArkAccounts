package com.arkaccountslite.model.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseAccount {

    @SerializedName("ResponseData")
    @Expose
    private ArrayList<ResponseAccountData> responseData = null;
    @SerializedName("ResponseCode")
    @Expose
    private int responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("ResponseType")
    @Expose
    private String responseType;

    public ArrayList<ResponseAccountData> getResponseData() {
        return responseData;
    }

    public void setResponseData(ArrayList<ResponseAccountData> responseData) {
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
        return "ResponseAccount{" +
                "responseData=" + responseData +
                ", responseCode=" + responseCode +
                ", responseMessage='" + responseMessage + '\'' +
                ", responseType='" + responseType + '\'' +
                '}';
    }
}