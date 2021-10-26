package com.ttc.demo.basemyviettel.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public
class ShopHomeResult implements Parcelable{
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;

    protected
    ShopHomeResult(Parcel in) {
        errorCode = in.readInt();
        message = in.readString();
        data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Creator<ShopHomeResult> CREATOR = new Creator<ShopHomeResult>() {
        @Override
        public
        ShopHomeResult createFromParcel(Parcel in) {
            return new ShopHomeResult(in);
        }

        @Override
        public
        ShopHomeResult[] newArray(int size) {
            return new ShopHomeResult[size];
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
    Data getData() {
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
        dest.writeParcelable(data, flags);
    }

    public static
    class Data implements Parcelable {
        @SerializedName("topBanner")
        private
        List<TopBannerModel> topBanner;

        protected
        Data(Parcel in) {
            topBanner = in.createTypedArrayList(TopBannerModel.CREATOR);
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public
            Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public
            Data[] newArray(int size) {
                return new Data[size];
            }
        };

        public
        List<TopBannerModel> getTopBanner() {
            return topBanner;
        }

        @Override
        public
        int describeContents() {
            return 0;
        }

        @Override
        public
        void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(topBanner);
        }
    }
}
