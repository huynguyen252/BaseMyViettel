package com.ttc.demo.basemyviettel.utils;

import android.graphics.Bitmap;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by namnh40 on 6/15/2015.
 */
public class Constants {

    public static String[][] CERT_INFO = new String[][]{
            new String[]{"*.vietteltelecom.vn", "sha256/q+s5NZ88oOPtGAPYAVrKZKGwST1yNX5ym6FDm6TqdGA="},
            new String[]{"*.vietteltelecom.vn", "sha256/hETpgVvaLC0bvcGG3t0cuqiHvr4XyP2MTwCiqhgRWwU="}
    };

    public static final String BASE_SERVER_URL = "http://apiv3.viettel.vn/myviettel.php/";

    public static final String KEY_TOKEN = "KEY_TOKEN_VIETTEL_DISCOVERY";
    public static final String KEY_SHARE_PREFERENCES = "KEY_SHARE_PREFERENCES_VIETTEL_DISCOVERY";
    public static final String GETIMEI = "GETIMEI_VIETTEL_DISCOVERY";
    public static final String GETMAC = "GETMAC_VIETTEL_DISCOVERY";
    public static final String PREF_IS_AUTHEN2 = "PREF_IS_AUTHEN2";
    public static final String LINK_GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";
    public static final String KEY_PRIVILEGE_MODEL = "KEY_PRIVILEGE_MODEL";
    public static final String PREF_HOT_NUMBER_SERVICE = "PREF_HOT_SEVICE_NUMBER";

    public static final class STORAGE {
        public static final int ONE_MEGABYTE = 1024;
        public static final int ONE_GIGABYTE = ONE_MEGABYTE * 1024;
        public static final int ONE_TERABYTE = ONE_GIGABYTE * 1024;

    }

    public static final class TIME {
        public static final int ONE_SECOND = 1000;
        public static final int ONE_MINUTES = ONE_SECOND * 60;
        public static final int ONE_HOUR = ONE_MINUTES * 60;
    }

}

