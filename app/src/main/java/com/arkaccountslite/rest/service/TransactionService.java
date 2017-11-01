package com.arkaccountslite.rest.service;

import com.arkaccountslite.model.transaction.AddTransaction;
import com.arkaccountslite.model.transaction.ResponseAddTransaction;
import com.arkaccountslite.rest.MainTransport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public final class TransactionService {

    private static TransactionRest sService;

    private TransactionService() {
    }

    public static Call<ResponseAddTransaction> addTransaction(AddTransaction addTransaction) {
        return getTransactionService().addTransaction(addTransaction);
    }

    private static TransactionRest getTransactionService() {
        if (sService == null) {
            sService = MainTransport.init(TransactionRest.class);
        }

        return sService;
    }

    private interface TransactionRest {

        @POST("Transaction/Add/")
        Call<ResponseAddTransaction> addTransaction(@Body AddTransaction addTransaction);

    }

}