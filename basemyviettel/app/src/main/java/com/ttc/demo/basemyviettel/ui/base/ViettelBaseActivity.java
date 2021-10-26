package com.ttc.demo.basemyviettel.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.gemvietnam.base.ContainerActivity;
import com.ttc.demo.basemyviettel.utils.DialogUtils;
import com.ttc.demo.basemyviettel.utils.SharedPref;

public abstract class ViettelBaseActivity extends ContainerActivity{
    protected SharedPref mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPref = new SharedPref(this);
    }

    public void showAlertDialog(String message) {
        DialogUtils.showErrorAlert(this, message);
    }

    public void showProgress() {
        DialogUtils.showProgressDialog(this);
    }

        public boolean isShowing() {
            return DialogUtils.isShowing();
        }

    public void hideProgress() {
        DialogUtils.dismissProgressDialog();
    }

    public void onRequestError(String errorCode, String errorMessage) {
        DialogUtils.showErrorAlert(this, errorMessage);
        hideProgress();
    }

    @Override
    public void showErrorAlert(Context context, String string) {
        DialogUtils.showErrorAlert(context, string);
    }

    @Override
    public void showNetworkErrorDialog(Activity activity) {
        DialogUtils.showNetworkErrorDialog(activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
