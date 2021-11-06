package com.ttc.demo.basemyviettel.ui.main.detail.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ttc.demo.basemyviettel.ui.main.detail.model.AreaDetailModel;

import java.util.List;

public
class AreaDetailResponse implements Parcelable {
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private
    List<AreaDetailModel> data;

    protected
    AreaDetailResponse(Parcel in) {
        errorCode = in.readInt();
        message = in.readString();
        data = in.createTypedArrayList(AreaDetailModel.CREATOR);
    }

    public static final Creator<AreaDetailResponse> CREATOR = new Creator<AreaDetailResponse>() {
        @Override
        public
        AreaDetailResponse createFromParcel(Parcel in) {
            return new AreaDetailResponse(in);
        }

        @Override
        public
        AreaDetailResponse[] newArray(int size) {
            return new AreaDetailResponse[size];
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
    List<AreaDetailModel> getData() {
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
