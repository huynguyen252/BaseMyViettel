package com.ttc.demo.basemyviettel.ui.main.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public
class ServicePackage implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("service_code")
    private String service_code;
    @SerializedName("price")
    private int price;
    @SerializedName("short_name")
    private String short_name;
    @SerializedName("short_description")
    private String short_description;
    @SerializedName("service_message")
    private String service_message;

    private boolean isSelected;

    protected
    ServicePackage(Parcel in) {
        id = in.readInt();
        service_code = in.readString();
        price = in.readInt();
        short_name = in.readString();
        short_description = in.readString();
        service_message = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<ServicePackage> CREATOR = new Creator<ServicePackage>() {
        @Override
        public
        ServicePackage createFromParcel(Parcel in) {
            return new ServicePackage(in);
        }

        @Override
        public
        ServicePackage[] newArray(int size) {
            return new ServicePackage[size];
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
    int getId() {
        return id;
    }

    public
    String getService_code() {
        return service_code;
    }

    public
    int getPrice() {
        return price;
    }

    public
    String getShort_name() {
        return short_name;
    }

    public
    String getShort_description() {
        return short_description;
    }

    public
    String getService_message() {
        return service_message;
    }

    @Override
    public
    int describeContents() {
        return 0;
    }

    @Override
    public
    void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(service_code);
        dest.writeInt(price);
        dest.writeString(short_name);
        dest.writeString(short_description);
        dest.writeString(service_message);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
