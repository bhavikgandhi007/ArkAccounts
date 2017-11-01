package com.arkaccountslite.model.ResponseSummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DebitSummary {

    @SerializedName("TransactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("TransactionSum")
    @Expose
    private int transactionSum;

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(int transactionSum) {
        this.transactionSum = transactionSum;
    }

    @Override
    public String toString() {
        return "DebitSummary{" +
                "transactionDate='" + transactionDate + '\'' +
                ", transactionSum=" + transactionSum +
                '}';
    }
}
