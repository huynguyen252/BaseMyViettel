package com.ttc.demo.basemyviettel.ui.main.detail.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaModel;

import java.util.List;

public
class AreaResponse implements Parcelable {
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private
    List<AreaModel> data;

    protected
    AreaResponse(Parcel in) {
        errorCode = in.readInt();
        message = in.readString();
        data = in.createTypedArrayList(AreaModel.CREATOR);
    }

    public static final Creator<AreaResponse> CREATOR = new Creator<AreaResponse>() {
        @Override
        public
        AreaResponse createFromParcel(Parcel in) {
            return new AreaResponse(in);
        }

        @Override
        public
        AreaResponse[] newArray(int size) {
            return new AreaResponse[size];
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
    List<AreaModel> getData() {
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
