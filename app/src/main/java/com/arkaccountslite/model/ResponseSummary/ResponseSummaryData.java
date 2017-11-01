package com.arkaccountslite.model.ResponseSummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSummaryData {

    @SerializedName("ApiAccessKey")
    @Expose
    private Object apiAccessKey;
    @SerializedName("FKUser")
    @Expose
    private int fKUser;
    @SerializedName("OverallDebits")
    @Expose
    private int overallDebits;
    @SerializedName("OverallCredits")
    @Expose
    private int overallCredits;
    @SerializedName("DebitSummary")
    @Expose
    private List<Object> debitSummary = null;
    @SerializedName("CreditSummary")
    @Expose
    private List<Object> creditSummary = null;

    public Object getApiAccessKey() {
        return apiAccessKey;
    }

    public void setApiAccessKey(Object apiAccessKey) {
        this.apiAccessKey = apiAccessKey;
    }

    public int getFKUser() {
        return fKUser;
    }

    public void setFKUser(int fKUser) {
        this.fKUser = fKUser;
    }

    public int getOverallDebits() {
        return overallDebits;
    }

    public void setOverallDebits(int overallDebits) {
        this.overallDebits = overallDebits;
    }

    public int getOverallCredits() {
        return overallCredits;
    }

    public void setOverallCredits(int overallCredits) {
        this.overallCredits = overallCredits;
    }

    public List<Object> getDebitSummary() {
        return debitSummary;
    }

    public void setDebitSummary(List<Object> debitSummary) {
        this.debitSummary = debitSummary;
    }

    public List<Object> getCreditSummary() {
        return creditSummary;
    }

    public void setCreditSummary(List<Object> creditSummary) {
        this.creditSummary = creditSummary;
    }

    @Override
    public String toString() {
        return "ResponseSummaryData{" +
                "apiAccessKey=" + apiAccessKey +
                ", fKUser=" + fKUser +
                ", overallDebits=" + overallDebits +
                ", overallCredits=" + overallCredits +
                ", debitSummary=" + debitSummary +
                ", creditSummary=" + creditSummary +
                '}';
    }
}