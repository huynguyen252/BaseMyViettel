package com.ttc.demo.basemyviettel.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public
class MobileModel implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("service_code")
    private String service_code;
    @SerializedName("short_name")
    private String short_name;
    @SerializedName("short_description")
    private String short_description;
    @SerializedName("image")
    private String image;
    @SerializedName("banner_url")
    private String banner_url;

    public
    int getId() {
        return id;
    }

    public
    String getService_code() {
        return service_code;
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
    String getImage() {
        return image;
    }

    public
    String getBanner_url() {
        return banner_url;
    }

    protected
    MobileModel(Parcel in) {
        id = in.readInt();
        service_code = in.readString();
        short_name = in.readString();
        short_description = in.readString();
        image = in.readString();
        banner_url = in.readString();
    }

    public static final Creator<MobileModel> CREATOR = new Creator<MobileModel>() {
        @Override
        public
        MobileModel createFromParcel(Parcel in) {
            return new MobileModel(in);
        }

        @Override
        public
        MobileModel[] newArray(int size) {
            return new MobileModel[size];
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
        dest.writeInt(id);
        dest.writeString(service_code);
        dest.writeString(short_name);
        dest.writeString(short_description);
        dest.writeString(image);
        dest.writeString(banner_url);
    }
}
