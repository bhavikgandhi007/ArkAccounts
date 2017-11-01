package com.arkaccountslite.utill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utility {


    public static String convertDate(String date) {
        SimpleDateFormat spf = new SimpleDateFormat("EEE MMM dd hh:mm:ss 'GMT'Z yyyy");
        Date newDate = null;
        try {
            newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd MMM yyyy");
            String newDateString = spf.format(newDate);
            return newDateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
