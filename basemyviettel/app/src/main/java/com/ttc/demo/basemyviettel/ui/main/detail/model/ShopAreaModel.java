package com.ttc.demo.basemyviettel.ui.main.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public
class ShopAreaModel implements Parcelable {
    @SerializedName("shopId")
    private long shopId;
    @SerializedName("shopCode")
    private String shopCode;
    @SerializedName("address")
    private String address;
    @SerializedName("shopFullName")
    private String shopFullName;
    @SerializedName("numberOfCustsServing")
    private long numberOfCustsServing;
    @SerializedName("approximateTimeToWait")
    private long approximateTimeToWait;

    protected
    ShopAreaModel(Parcel in) {
        shopId = in.readLong();
        shopCode = in.readString();
        address = in.readString();
        shopFullName = in.readString();
        numberOfCustsServing = in.readLong();
        approximateTimeToWait = in.readLong();
    }

    public static final Creator<ShopAreaModel> CREATOR = new Creator<ShopAreaModel>() {
        @Override
        public
        ShopAreaModel createFromParcel(Parcel in) {
            return new ShopAreaModel(in);
        }

        @Override
        public
        ShopAreaModel[] newArray(int size) {
            return new ShopAreaModel[size];
        }
    };

    public
    long getShopId() {
        return shopId;
    }

    public
    String getShopCode() {
        return shopCode;
    }

    public
    String getAddress() {
        return address;
    }

    public
    String getShopFullName() {
        return shopFullName;
    }

    public
    long getNumberOfCustsServing() {
        return numberOfCustsServing;
    }

    public
    long getApproximateTimeToWait() {
        return approximateTimeToWait;
    }

    @Override
    public
    int describeContents() {
        return 0;
    }

    @Override
    public
    void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(shopId);
        dest.writeString(shopCode);
        dest.writeString(address);
        dest.writeString(shopFullName);
        dest.writeLong(numberOfCustsServing);
        dest.writeLong(approximateTimeToWait);
    }
}
