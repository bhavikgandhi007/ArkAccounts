package com.arkaccountslite.rest.service;

import com.arkaccountslite.model.GetData;
import com.arkaccountslite.model.account.AddAccount;
import com.arkaccountslite.model.account.ResponseAccount;
import com.arkaccountslite.model.account.ResponseAddAccount;
import com.arkaccountslite.model.account.ResponseDeleteAccount;
import com.arkaccountslite.rest.MainTransport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public final class AccountService {

    private static AccountRest sService;

    private AccountService() {
    }

    public static Call<ResponseAddAccount> addAccount(AddAccount addAccount) {
        return getAuthorizationService().addAccount(addAccount);
    }

    public static Call<ResponseAccount> getAccounts(GetData getData) {
        return getAuthorizationService().getAccounts(getData);
    }

    public static Call<ResponseDeleteAccount> deleteAccount(GetData getData) {
        return getAuthorizationService().deleteAccount(getData);
    }

    private static AccountRest getAuthorizationService() {
        if (sService == null) {
            sService = MainTransport.init(AccountRest.class);
        }

        return sService;
    }

    private interface AccountRest {

        @POST("Account/Add/")
        Call<ResponseAddAccount> addAccount(@Body AddAccount addAccount);

        @POST("Account/Get/")
        Call<ResponseAccount> getAccounts(@Body GetData getData);

        @POST("Account/Delete/")
        Call<ResponseDeleteAccount> deleteAccount(@Body GetData getData);
    }

}