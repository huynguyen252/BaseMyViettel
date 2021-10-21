package com.ttc.demo.basemyviettel.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.utils.ContextUtils;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.dialog.NProgressDialog;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Dialog Utils
 * Created by neo on 2/15/2016.
 */
public class DialogUtils{

  @Retention(RetentionPolicy.SOURCE)
  private @interface ID_BUTTON_POPUP_FEE {
    int CANCEL = 0;
    int AGREE = 1;
  }

  @Retention(RetentionPolicy.SOURCE)
  public  @interface ID_BUTTON_POPUP {
    int CANCEL = 0;
    int AGREE = 1;
  }
  @Retention(RetentionPolicy.SOURCE)
  public  @interface TYPE_PACKAGE {
    int DATA = 1;
    int SMS = 3;
  }

  private static final String TAG = DialogUtils.class.getSimpleName();
  private static volatile NProgressDialog progress;

  private DialogUtils() {

  }

  /**
   * Show alert message with message id
   */
  public static void showAlert(Context context, int msgId) {
    showAlert(context, msgId, null);
  }

  /**
   * Show alert message with message id
   */
  public static void showAlert(Context context, int msgId, DialogInterface.OnClickListener onClickListener) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(msgId);
    builder.setPositiveButton(R.string.ok, onClickListener);
    builder.create().show();
  }

  /**
   * Show alert message with message id
   */
  public static void showAlert(Context context, String message, DialogInterface.OnClickListener onClickListener) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message);
    builder.setPositiveButton(R.string.ok, onClickListener);
    builder.create().show();
  }

  /**
   * Show alert message with message
   */
  public static void showAlert(Context context, String msg) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(msg);
    builder.setPositiveButton(R.string.ok, null);
    builder.create().show();
  }

  /**
   * Show alert message with message id
   */
  public static void showErrorAlert(Context context, String message) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message);
    builder.setTitle(R.string.title_error);
    builder.setPositiveButton(R.string.ok, null);
    builder.create().show();
  }

  public static void showCautionAlert(Context context, String message, final Presenter presenter) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message);
    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        presenter.back();
      }
    });
    builder.setNegativeButton(R.string.cancel, null);
    builder.create().show();
  }

  /**
   * Show alert message with message id
   */
  public static void showErrorAlert(Context context, int messageId) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(context.getString(messageId));
    builder.setTitle(R.string.title_error);
    builder.setPositiveButton(R.string.ok, null);
    builder.create().show();
  }


  public static void showAlertTTC(Context context, String messageId) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog);
    builder.setMessage(messageId);
    builder.setTitle("Thông báo");
    builder.setPositiveButton(R.string.ok, null);
    builder.create().show();
  }

  /**
   * Show dialog with 1 button
   */
  public static void showErrorAlert(Context context, String message, int buttonTextId, DialogInterface.OnClickListener onClickListener) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }
    if (context instanceof Activity && ((Activity) context).isFinishing()) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message);
    builder.setTitle(R.string.title_error);
    builder.setPositiveButton(buttonTextId, onClickListener);
    builder.setCancelable(false);
    builder.create().show();
  }

  /**
   * Show dialog progress.
   *
   * @param activity the context is running.
   */
  public static void showProgressDialog(final Activity activity) {
    if (activity == null)
      return;

    if (progress != null) {
      try {
        progress.cancel();
      } catch (Exception ex) {
        MyLogger.log(ex);
      }
    }


    Utilities.hideKeyboard(null, activity);
    progress = new NProgressDialog(activity);
    progress.setTitle("");
    progress.setCancelable(true);
    progress.setOnCancelListener(null);
    progress.setCanceledOnTouchOutside(false);
    progress.show();
  }

  public static void showProgressWithTextDialog(final Activity activity) {
    if (activity == null)
      return;

    if (progress != null) {
      try {
        progress.cancel();
      } catch (Exception ex) {
        MyLogger.log(ex);
      }
    }
    Utilities.hideKeyboard(null, activity);
    progress = new NProgressDialog(activity, true);
    progress.setTitle("");
    progress.setCancelable(true);
    progress.setOnCancelListener(null);
    progress.setCanceledOnTouchOutside(false);
    progress.show();
  }

  public static void setCancelProgress(boolean b) {
    if (null != progress) {
      progress.setCancelable(false);
    }
  }

  public static boolean isShowing() {
    if (progress != null) {
      return progress.isShowing();
    }
    return false;
  }

  /**
   * Dismiss progress dialog
   */
  public static void dismissProgressDialog() {
    try {
      if (progress != null)
        progress.dismiss();
    } catch (Exception e) {
      Log.e(TAG, "dismissProgressDialog Exception", e);
    }
  }

  /**
   * Show alert dialog with action
   */
  public static void showAlertAction(Context context, int message, DialogInterface.OnClickListener listener) {
    showAlertAction(context, context.getString(message), listener);
  }

  /**
   * Show alert dialog with action
   */
  public static void showAlertAction(Context context, String message, DialogInterface.OnClickListener listener) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message);
    builder.setPositiveButton(R.string.ok, listener);
    builder.setNegativeButton(R.string.cancel, null);
    builder.create().show();
  }

  /**
   * Show alert dialog with 2 action
   */
  public static void showOptionAction(Context context, String message, String positiveText, String negativeText,
                                      DialogInterface.OnClickListener positiveListener,
                                      DialogInterface.OnClickListener negativeListener) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message);
    builder.setPositiveButton(positiveText, positiveListener);
    builder.setNegativeButton(negativeText, negativeListener);
    builder.create().show();
  }

  /**
   * Show alert dialog with 2 action
   */
  public static void showOptionAction(Context context, int message,
                                      DialogInterface.OnClickListener positiveListener,
                                      DialogInterface.OnClickListener negativeListener) {
    dismissProgressDialog();
    if (!isValidContext(context)) {
      return;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(message);
    builder.setPositiveButton(R.string.ok, positiveListener);
    builder.setNegativeButton(R.string.cancel, negativeListener);
    builder.create().show();
  }

  /**
   * @param context  the context is running.
   * @param title    of dialog.
   * @param message  of dialog.
   * @param listener callback when clicked button ok.
   * @param isCancel true logParams button cancel.
   */
  public static void showDialogMessage(final Context context,
                                       final String title, final String message,
                                       final DialogInterface.OnClickListener listener, final boolean isCancel) {
    dismissProgressDialog();

    // Check context valid
    if (!ContextUtils.isValidContext(context)) {
      return;
    }

    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
    if (title != null)
      builder.setTitle(title);
    builder.setMessage(message);
    builder.setCancelable(false);
    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        if (listener != null) {
          listener.onClick(dialog, 12);
        }
        dialog.dismiss();
      }
    });
    if (isCancel) {
      builder.setNegativeButton(R.string.cancel,
              new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
                }
              });
    }
    AlertDialog sAlert = builder.create();
    sAlert.show();
    TextView messageView = (TextView) sAlert
            .findViewById(android.R.id.message);
    if (messageView != null) {
      messageView.setGravity(Gravity.CENTER);
    }
  }


  /**
   * Show dialog when network error
   *
   * @param activity Activity logParams dialog
   */
  public static void showNetworkErrorDialog(final Activity activity) {
    dismissProgressDialog();

    // Check context valid
    if (!ContextUtils.isValidContext(activity)) {
      return;
    }

    // Open Wireless setting
    DialogInterface.OnClickListener settingsListener = new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        if (!activity.isFinishing()) {
          activity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        }
      }
    };

    // Show dialog
    showDialogMessage(activity, activity.getString(R.string.title_network_lost),
            activity.getString(R.string.msg_network_lost), settingsListener,
            true);
  }

  /**
   * Check if context is valid
   */
  public static boolean isValidContext(Context context) {
    if (context == null) {
      return false;
    }
    return !(context instanceof Activity && ((Activity) context).isFinishing());
  }
}