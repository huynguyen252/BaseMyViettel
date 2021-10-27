package com.ttc.demo.basemyviettel.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public
class SimModel implements Parcelable {
    @SerializedName("isdn")
    private String isdn;
    @SerializedName("pre_price")
    private String pre_price;
    @SerializedName("pos_price")
    private String pos_price;
    @SerializedName("unit")
    private String unit;

    public
    String getIsdn() {
        return isdn;
    }

    public
    String getPre_price() {
        return pre_price;
    }

    public
    String getPos_price() {
        return pos_price;
    }

    public
    String getUnit() {
        return unit;
    }

    protected
    SimModel(Parcel in) {
        isdn = in.readString();
        pre_price = in.readString();
        pos_price = in.readString();
        unit = in.readString();
    }

    public static final Creator<SimModel> CREATOR = new Creator<SimModel>() {
        @Override
        public
        SimModel createFromParcel(Parcel in) {
            return new SimModel(in);
        }

        @Override
        public
        SimModel[] newArray(int size) {
            return new SimModel[size];
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
        dest.writeString(isdn);
        dest.writeString(pre_price);
        dest.writeString(pos_price);
        dest.writeString(unit);
    }
}
