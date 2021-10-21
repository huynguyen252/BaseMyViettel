package com.ttc.demo.basemyviettel.utils;

import android.util.Log;

/**
 * Created by hieunv29 on 4/25/17.
 */

public class MyLogger {

    public static void log(Exception ex) {
        // Do nothing
        ex.printStackTrace();
    }

    public static void logString(Exception ex) {
        Log.e("MyViettel", "Exception: " + ex.toString());
    }

    public static void logErr(Error ex) {
        // Do nothing
        ex.printStackTrace();
    }

}
