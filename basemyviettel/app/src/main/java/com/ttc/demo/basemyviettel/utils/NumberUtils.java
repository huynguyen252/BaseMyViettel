package com.ttc.demo.basemyviettel.utils;

import com.gemvietnam.utils.StringUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public
class NumberUtils {
    private static final String NUMBER_FORMAT_2 = "#,###,###";

    public NumberUtils() {
    }

    public static String formatPriceNumber(int price){
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        otherSymbols.setGroupingSeparator('.');
        return new DecimalFormat(NUMBER_FORMAT_2).format(price).replace(",",".");
    }

    public static String formatPriceNumber(long price){
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        otherSymbols.setGroupingSeparator('.');
        return new DecimalFormat(NUMBER_FORMAT_2).format(price).replace(",",".");
    }

    public static String convertVietNamPhoneNumber(String phoneNumber){
        String mPhoneNumber;
        if (phoneNumber.length() < 6){
            return phoneNumber;
        }

        if (!StringUtils.isNumeric(phoneNumber)){
            return phoneNumber;
        }

        if ("84".equals(phoneNumber.trim().substring(0,2))){
            mPhoneNumber = "0" + phoneNumber.trim().substring(2);
        } else if ("+84".equals(phoneNumber.trim().substring(0,3))){
            mPhoneNumber = "0" + phoneNumber.trim().substring(3);
        } else if (!"0".equals(phoneNumber.trim().substring(0,1))){
            mPhoneNumber = "0" + phoneNumber.trim();
        } else {
            mPhoneNumber = phoneNumber;
        }
        return mPhoneNumber;
    }
}
