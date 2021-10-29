package com.ttc.demo.basemyviettel.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SimModel implements Parcelable {
    @SerializedName("isdn")
    private String isdn;
    @SerializedName("pre_price")
    private String pre_price;
    @SerializedName("pos_price")
    private String pos_price;
    @SerializedName("unit")
    private String unit;

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getPre_price() {
        return pre_price;
    }

    public void setPre_price(String pre_price) {
        this.pre_price = pre_price;
    }

    public String getPos_price() {
        return pos_price;
    }

    public void setPos_price(String pos_price) {
        this.pos_price = pos_price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    protected SimModel(Parcel in) {
        isdn = in.readString();
        pre_price = in.readString();
        pos_price = in.readString();
        unit      = in.readString();

    }

    public static final Creator<SimModel> CREATOR = new Creator<SimModel>() {
        @Override
        public SimModel createFromParcel(Parcel in) {
            return new SimModel(in);
        }

        @Override
        public SimModel[] newArray(int size) {
            return new SimModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(isdn);
        parcel.writeString(pre_price);
        parcel.writeString(pos_price);
        parcel.writeString(unit);
    }
}
