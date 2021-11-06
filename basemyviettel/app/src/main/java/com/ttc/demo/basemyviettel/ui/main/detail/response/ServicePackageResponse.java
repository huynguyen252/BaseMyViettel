package com.ttc.demo.basemyviettel.ui.main.detail.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ttc.demo.basemyviettel.ui.main.detail.model.MobilePackage;
import com.ttc.demo.basemyviettel.ui.main.detail.model.ServicePackage;

import java.util.List;

public
class ServicePackageResponse implements Parcelable {
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private
    List<ServicePackage> data;

    protected
    ServicePackageResponse(Parcel in) {
        errorCode = in.readInt();
        message = in.readString();
        data = in.createTypedArrayList(ServicePackage.CREATOR);
    }

    public static final Creator<ServicePackageResponse> CREATOR = new Creator<ServicePackageResponse>() {
        @Override
        public
        ServicePackageResponse createFromParcel(Parcel in) {
            return new ServicePackageResponse(in);
        }

        @Override
        public
        ServicePackageResponse[] newArray(int size) {
            return new ServicePackageResponse[size];
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
    List<ServicePackage> getData() {
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
