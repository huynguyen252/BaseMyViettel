package com.ttc.demo.basemyviettel.ui.main.detail.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ttc.demo.basemyviettel.ui.main.detail.model.MobilePackage;
import com.ttc.demo.basemyviettel.ui.main.detail.model.NumberModel;

import java.util.List;

public
class MobilePackageResponse implements Parcelable {
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private
    List<MobilePackage> data;

    protected
    MobilePackageResponse(Parcel in) {
        errorCode = in.readInt();
        message = in.readString();
        data = in.createTypedArrayList(MobilePackage.CREATOR);
    }

    public static final Creator<MobilePackageResponse> CREATOR = new Creator<MobilePackageResponse>() {
        @Override
        public
        MobilePackageResponse createFromParcel(Parcel in) {
            return new MobilePackageResponse(in);
        }

        @Override
        public
        MobilePackageResponse[] newArray(int size) {
            return new MobilePackageResponse[size];
        }
    };

    public
    int getErrorCode() {
        return errorCode;
    }

    public
    String getMessage() {
        return message;
    }

    public
    List<MobilePackage> getData() {
        return data;
    }

    @Override
    public
    int describeContents() {
        return 0;
    }

    @Override
    public
    void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(errorCode);
        dest.writeString(message);
        dest.writeTypedList(data);
    }
}
