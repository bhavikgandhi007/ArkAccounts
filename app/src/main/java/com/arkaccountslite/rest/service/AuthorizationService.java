package com.arkaccountslite.rest.service;

import com.arkaccountslite.model.GetData;
import com.arkaccountslite.model.ResponseSummary.ResponseSummary;
import com.arkaccountslite.model.User;
import com.arkaccountslite.model.VerifyOtp;
import com.arkaccountslite.model.auth.ResponseUser;
import com.arkaccountslite.rest.MainTransport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public final class AuthorizationService {

    private static AuthorizationRest sService;

    private AuthorizationService() {
    }

    public static Call<ResponseUser> signUp(User user) {
        return getAuthorizationService().signUp(user);
    }

    public static Call<ResponseUser> login(User user) {
        return getAuthorizationService().login(user);
    }

    public static Call<ResponseUser> verify(VerifyOtp verifyOtp) {
        return getAuthorizationService().verify(verifyOtp);

    }

    public static Call<ResponseUser> resendOtp(VerifyOtp verifyOtp) {
        return getAuthorizationService().resendOtp(verifyOtp);
    }

    public static Call<ResponseSummary> homeSummary(GetData getData) {
        return getAuthorizationService().homeSummary(getData);
    }

    private static AuthorizationRest getAuthorizationService() {
        if (sService == null) {
            sService = MainTransport.init(AuthorizationRest.class);
        }

        return sService;
    }

    private interface AuthorizationRest {

        @POST("User/Add/")
        Call<ResponseUser> signUp(@Body User user);

        @POST("User/Login/")
        Call<ResponseUser> login(@Body User user);

        @POST("User/Verify/")
        Call<ResponseUser> verify(@Body VerifyOtp verifyOtp);

        @POST("User/ResendOTP/")
        Call<ResponseUser> resendOtp(@Body VerifyOtp verifyOtp);

        @POST("Home/Get/")
        Call<ResponseSummary> homeSummary(@Body GetData getData);
    }

}