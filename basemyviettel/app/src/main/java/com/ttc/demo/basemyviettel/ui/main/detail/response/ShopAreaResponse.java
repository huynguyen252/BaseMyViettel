package com.ttc.demo.basemyviettel.ui.main.detail.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ttc.demo.basemyviettel.ui.main.detail.model.ShopAreaModel;

import java.util.List;

public
class ShopAreaResponse implements Parcelable {
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private
    List<ShopAreaModel> data;

    protected
    ShopAreaResponse(Parcel in) {
        errorCode = in.readInt();
        message = in.readString();
        data = in.createTypedArrayList(ShopAreaModel.CREATOR);
    }

    public static final Creator<ShopAreaResponse> CREATOR = new Creator<ShopAreaResponse>() {
        @Override
        public
        ShopAreaResponse createFromParcel(Parcel in) {
            return new ShopAreaResponse(in);
        }

        @Override
        public
        ShopAreaResponse[] newArray(int size) {
            return new ShopAreaResponse[size];
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
    List<ShopAreaModel> getData() {
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
