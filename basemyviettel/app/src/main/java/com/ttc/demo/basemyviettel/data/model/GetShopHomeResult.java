package com.ttc.demo.basemyviettel.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetShopHomeResult implements Parcelable  {
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;

    protected
    GetShopHomeResult(Parcel in) {
        errorCode = in.readInt();
        message = in.readString();
        data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Creator<GetShopHomeResult> CREATOR = new Creator<GetShopHomeResult>() {
        @Override
        public
        GetShopHomeResult createFromParcel(Parcel in) {
            return new GetShopHomeResult(in);
        }

        @Override
        public
        GetShopHomeResult[] newArray(int size) {
            return new GetShopHomeResult[size];
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

        @SerializedName("sim")
        private List<SimModel> sim;


        public
        List<SimModel> getSim() {
            return sim;
        }

        protected
        Data(Parcel in) {
            sim = in.createTypedArrayList(SimModel.CREATOR);
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

        @Override
        public
        int describeContents() {
            return 0;
        }

        @Override
        public
        void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(sim);
        }
    }
}
