package com.ttc.demo.basemyviettel.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HungNX on 7/28/16.
 */
public class SimpleResult implements Parcelable {

    @SerializedName("errorCode")
    Integer errorCode = -100;

    @SerializedName("message")
    String message  ="";
    @SerializedName("needOTP")
    String needOTP;

    @SerializedName("tranIdTracking")
    String tranIdTracking;

    public static final Creator<SimpleResult> CREATOR = new Creator<SimpleResult>() {
        @Override
        public SimpleResult createFromParcel(Parcel in) {
            return new SimpleResult(in);
        }

        @Override
        public SimpleResult[] newArray(int size) {
            return new SimpleResult[size];
        }
    };

    @Override
    public String toString() {
        return "SimpleResult{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                '}';
    }

    public String getNeedOTP() {
        return needOTP == null ? "0" : needOTP;
    }

    public void setNeedOTP(String needOTP) {
        this.needOTP = needOTP;
    }

    public Integer getErrorCode() {
        return errorCode == null ? 1 : errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTranIdTracking() {
        return tranIdTracking;
    }

    public void setTranIdTracking(String tranIdTracking) {
        this.tranIdTracking = tranIdTracking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

      SimpleResult that = (SimpleResult) o;

        if (!errorCode.equals(that.errorCode)) return false;
        return message.equals(that.message);

    }

    @Override
    public int hashCode() {
        int result = errorCode.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.errorCode);
        dest.writeString(this.message);
    }

    public SimpleResult() {
    }

    protected SimpleResult(Parcel in) {
        this.errorCode = in.readInt();
        this.message = in.readString();
    }

}
