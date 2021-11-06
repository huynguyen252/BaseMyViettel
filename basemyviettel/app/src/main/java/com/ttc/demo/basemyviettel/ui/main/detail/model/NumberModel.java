package com.ttc.demo.basemyviettel.ui.main.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public
class NumberModel implements Parcelable {
    @SerializedName("isdn")
    private String isdn;

    @SerializedName("pre_price")
    private String prePrice;

    @SerializedName("pos_price")
    private String posPrice;

    @SerializedName("unit")
    private String unit;

    private boolean isSelected;

    public
    NumberModel() {
    }

    protected
    NumberModel(Parcel in) {
        isdn = in.readString();
        prePrice = in.readString();
        posPrice = in.readString();
        unit = in.readString();
    }

    public static final Creator<NumberModel> CREATOR = new Creator<NumberModel>() {
        @Override
        public
        NumberModel createFromParcel(Parcel in) {
            return new NumberModel(in);
        }

        @Override
        public
        NumberModel[] newArray(int size) {
            return new NumberModel[size];
        }
    };

    public
    boolean isSelected() {
        return isSelected;
    }

    public
    void setSelected(boolean selected) {
        isSelected = selected;
    }

    public
    String getIsdn() {
        return isdn;
    }

    public
    String getPrePrice() {
        return prePrice;
    }

    public
    String getPosPrice() {
        return posPrice;
    }

    public
    String getUnit() {
        return unit;
    }

    @Override
    public
    int describeContents() {
        return 0;
    }

    @Override
    public
    void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isdn);
        dest.writeString(prePrice);
        dest.writeString(posPrice);
        dest.writeString(unit);
    }
}
