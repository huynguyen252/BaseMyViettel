package com.ttc.demo.basemyviettel.ui.main.detail.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ttc.demo.basemyviettel.ui.main.detail.model.NumberModel;

import java.util.List;

public
class NumberResponse implements Parcelable {
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private
    List<NumberModel> data;

    public
    int getErrorCode() {
        return errorCode;
    }

    public
    String getMessage() {
        return message;
    }

    public
    List<NumberModel> getData() {
        return data;
    }

    protected
    NumberResponse(Parcel in) {
        errorCode = in.readInt();
        message = in.readString();
        data = in.createTypedArrayList(NumberModel.CREATOR);
    }

    public static final Creator<NumberResponse> CREATOR = new Creator<NumberResponse>() {
        @Override
        public
        NumberResponse createFromParcel(Parcel in) {
            return new NumberResponse(in);
        }

        @Override
        public
        NumberResponse[] newArray(int size) {
            return new NumberResponse[size];
        }
    };

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
