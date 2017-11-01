package com.arkaccountslite.utill;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Utilities and constants related to app preferences.
 */
public class PrefUtils {
    public static final String PREF_TOS_ACCEPTED = "pref_tos_accepted";
    public static final String PREF_WELCOME_DONE = "pref_welcome_done";
    public static final String PREF_IS_LOGGED_IN = "pref_is_logged_in";
    public static final String PREF_CART_COUNT_LOGGED_IN = "pref_cart_count_logged_In";
    public static final String PREF_DEFAULT_DUE_DAYS = "pref_default_due_days";
    public static final String PREF_IS_ALLOW_REMINDER = "pref_is_allow_reminder";
    public static final String PREF_IS_ALLOW_NOTIFICATION = "pref_is_allow_notification";
    public static final String PREF_DEVICE_TOKEN = "PREF_DEVICE_TOKEN";
    private static final String PREF_AUTH_TOKEN = "PREF_AUTH_TOKEN";
    private static final String PREF_LOGIN = "PREF_LOGIN";
    private static final String PREF_USER = "PREF_USER";

    private static final String PREF_ACCOUNT_DATA = "PREF_ACCOUNT_DATA";


    private static final String TAG = PrefUtils.class.getSimpleName();

    public static boolean isTosAccepted(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_TOS_ACCEPTED, false);
    }

    public static void markTosAccepted(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_TOS_ACCEPTED, true).commit();
    }

    public static boolean isWelcomeDone(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_WELCOME_DONE, false);
    }

    public static void markWelcomeDone(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_WELCOME_DONE, true).commit();
    }

    public static String getDeviceToken(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PREF_DEVICE_TOKEN, "");
    }

    public static void setDeviceToken(Context context, String deviceToken) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_DEVICE_TOKEN, deviceToken).commit();
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_IS_LOGGED_IN, false);
    }

    public static void setLoggedIn(Context context, boolean isLoggedIn) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_IS_LOGGED_IN, isLoggedIn).commit();
    }


    public static int getCartCountLoggedIn(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(PREF_CART_COUNT_LOGGED_IN, 0);
    }

    public static void setCartCountLoggedIn(Context context, int cartCount) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(PREF_CART_COUNT_LOGGED_IN, cartCount).commit();
    }

    public static int getPrefDefaultDueDays(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(PREF_DEFAULT_DUE_DAYS, 30);
    }

    public static void setPrefDefaultDueDays(Context context, int duedays) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(PREF_DEFAULT_DUE_DAYS, duedays).commit();
    }

    public static boolean isAllowReminder(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_IS_ALLOW_REMINDER, true);
    }

    public static void setPrefIsAllowReminder(Context context, boolean isAllow) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_IS_ALLOW_REMINDER, isAllow).commit();
    }

    public static boolean isAllowPush(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_IS_ALLOW_NOTIFICATION, true);
    }

    public static void setPrefIsAllowPush(Context context, boolean isAllow) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_IS_ALLOW_NOTIFICATION, isAllow).commit();
    }

    public static String isTokenAvailable(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PREF_AUTH_TOKEN, null);
    }

    public static void markTokenAvailable(Context context, String token) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_AUTH_TOKEN, token).apply();
    }

    public static boolean isLoginDone(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_LOGIN, false);
    }

    public static void markLoginDone(Context context, boolean loginDone) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_LOGIN, loginDone).apply();
    }

    public static String getUser(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PREF_USER, null);
    }

    public static void setUser(Context context, String user) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_USER, user).apply();
    }

    public static String getAccountData(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PREF_ACCOUNT_DATA, null);
    }

    public static void setAccountData(Context context, String accountData) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_ACCOUNT_DATA, accountData).apply();
    }
}
