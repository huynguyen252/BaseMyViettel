package com.ttc.demo.basemyviettel.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.gemvietnam.base.log.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by namnh40 on 6/15/2015.
 */
public class SharedPref {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean autoCommit = true;

    public static final String FILE_NAME = "butler";

    public static final String CONUT_FRAGMENT_HOME_NEW = "count_fragment_home_new";

    private static final String ENABLE_MULTI_ACC = "enable_multi_acc";

    private static final String NUMBER_OPEN_APP = "number_open_app";

    private static final String HAS_DATA_TET = "has_data_tet";

    public static final String CURRENT_NEED_OTP = "current_need_otp";

    private static volatile SharedPref instance;

    /**
     * @param context ApplicationContext
     * @return
     */
    public static SharedPref getInstance(Context context) {
        if (instance == null)
            instance = new SharedPref(context);
        return instance;
    }

    public SharedPref(Context activity) {
        this.autoCommit = true;
        // pref = PreferenceManager.getDefaultSharedPreferences(mActivityBase);
        if (activity == null) {
            return;
        }
        pref = activity.getSharedPreferences(Constants.KEY_SHARE_PREFERENCES, Activity.MODE_PRIVATE);
        editor = pref.edit();
    }

    public SharedPref(Context activity, String name) {
        this.autoCommit = true;
        // pref = PreferenceManager.getDefaultSharedPreferences(mActivityBase);
        pref = activity
                .getSharedPreferences(name, Activity.MODE_PRIVATE);
        editor = pref.edit();
    }

    public SharedPref(Context activity, boolean autoCommit) {
        this.autoCommit = autoCommit;
        // pref = PreferenceManager.getDefaultSharedPreferences(mActivityBase);
        pref = activity
                .getSharedPreferences(Constants.KEY_SHARE_PREFERENCES, Activity.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     *
     * @param hotViewNumber
     */
    public void setHotViewNumber(String hotViewNumber){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.PREF_HOT_NUMBER_SERVICE, hotViewNumber);
        editor.commit();
    }

    public String getHotViewNumber() {
        return pref.getString(Constants.PREF_HOT_NUMBER_SERVICE, "0");
    }

    // String----------------------------------------------------------------//
    public void putString(String key, String value) {
        editor.putString(key, value);
        if (autoCommit) {
            commit();
        }

    }

    public void clear() {
        try {
            editor.remove(Constants.KEY_TOKEN);
            editor.remove(Constants.KEY_PRIVILEGE_MODEL);
            editor.remove(Constants.PREF_IS_AUTHEN2);
            editor.remove(CURRENT_NEED_OTP);
            editor.remove(HAS_DATA_TET);
            editor.remove(ENABLE_MULTI_ACC);
            editor.remove("GetAllServiceHot");
            editor.remove("GetDataUssdResult");
            editor.remove("GetPromotionResult");
            editor.remove(com.gemvietnam.Constants.FLAG_M_WIFI);
            editor.remove(com.gemvietnam.Constants.DATA_ANALYTICS_PRIMARY_KEY);
            //doi giai phap luu authen lifebox duoi local
//            //remove authen lifebox
//            editor.remove(ConstantLifeBox.LoginRegister.KEY_AUTHEN);
            ////
            //editor.clear();
            editor.commit();
        }
        catch (Exception ex)
        {
            Logger.w(ex);
        }
    }

    public void clear(String name){
        try {
//            editor.clear();
            editor.remove(name);
            editor.commit();
        } catch (Exception ex) {
            Logger.w(ex);
        }
    }

    public String getString(String key, String defaultValue) {
        return pref != null ? pref.getString(key, defaultValue) : "";
    }

    // Int------------------------------------------------------------------//
    public void putInt(String key, int value) {
        editor.putInt(key, value);
        if (autoCommit) {
            commit();
        }
    }

    public int getInt(String key, int defaultValue) {
        return pref.getInt(key, defaultValue);
    }

    // Long-----------------------------------------------------------------//
    public void putLong(String key, long value) {
        editor.putLong(key, value);
        if (autoCommit) {
            commit();
        }
    }

    public long getLong(String key, long defaultValue) {
        return pref.getLong(key, defaultValue);
    }

    // Float------------------------------------------------------------------//
    public void putFloat(String key, float value) {
        editor.putFloat(key, value);
        if (autoCommit) {
            commit();
        }
    }

    public float getFloat(String key, float defaultValue) {
        return pref.getFloat(key, defaultValue);
    }

    // Boolean-------------------------------------------------------------//
    public boolean getBoolean(String key, boolean defaultValue) {
        return pref.getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        if (editor != null){
            editor.putBoolean(key, value);
            if (autoCommit) {
                commit();
            }
        }
    }

    // ListString----------------------------------------------------------------//
    public int putListString(String key, ArrayList<String> listString) {
        for (int i = 0; i < listString.size(); i++)
            editor.putString("List#String" + i + key, listString.get(i));
        if (autoCommit)
            commit();
        return listString.size();
    }

    public ArrayList<String> getListString(String key, int sizeList,
                                           String defaultValue) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < sizeList; i++)
            result.add(pref.getString("List#String" + i + key, defaultValue));
        return result;
    }

    // ListInt----------------------------------------------------------------//
    public int putListInt(String key, int[] listInt) {
        for (int i = 0; i < listInt.length; i++)
            editor.putInt("List#Int" + i + key, listInt[i]);
        if (autoCommit)
            commit();
        return listInt.length;
    }

    public int[] getListInt(String key, int sizeList, int defaultValue) {
        int[] result = new int[sizeList];
        for (int i = 0; i < sizeList; i++)
            result[i] = pref.getInt("List#Int" + i + key, defaultValue);
        return result;
    }

    // Commnit-------------------------------------------------------------//
    public void commit() {
        if (editor != null) {
            editor.commit();
        }
    }


    public <T> void put(String key, T data) {
        SharedPreferences.Editor editor = pref.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else {
            editor.putString(key,new Gson().toJson(data));
        }
        editor.apply();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> anonymousClass, T defaultValue) {
        if (anonymousClass == String.class) {
            return (T) pref.getString(key, (String) defaultValue);
        } else if (anonymousClass == Boolean.class) {
            return (T) Boolean.valueOf(pref.getBoolean(key, (Boolean) defaultValue));
        } else if (anonymousClass == Float.class) {
            return (T) Float.valueOf(pref.getFloat(key, (Float) defaultValue));
        } else if (anonymousClass == Integer.class) {
            return (T) Integer.valueOf(pref.getInt(key, (Integer) defaultValue));
        } else if (anonymousClass == Long.class) {
            return (T) Long.valueOf(pref.getLong(key, (Long) defaultValue));
        } else {
            return (T) new Gson().fromJson(pref.getString(key, ""), anonymousClass);
        }
    }

    public ArrayList<String> getListString(String key, ArrayList<String> defaultArray){
        try {
            String strList = pref.getString(key,new Gson().toJson(defaultArray));
            Type founderListType = new TypeToken<ArrayList<String>>(){}.getType();
            return new Gson().fromJson(strList, founderListType);
        }catch (Exception e){
            return new ArrayList<>();
        }

    }

    public void putListHistorySearch(String key, ArrayList<String> listSearch){
        if(key == null){
            throw new NullPointerException();
        }
        String[] myStringList = listSearch.toArray(new String[listSearch.size()]);
        pref.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }

    public ArrayList<String> getListHistorySearch(String key){
        return new ArrayList<String>(Arrays.asList(TextUtils.split(pref.getString(key, ""), "‚‗‚")));
    }
}

