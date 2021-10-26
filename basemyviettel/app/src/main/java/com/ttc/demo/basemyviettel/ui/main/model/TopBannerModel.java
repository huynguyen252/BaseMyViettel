package com.ttc.demo.basemyviettel.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public
class TopBannerModel implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private String image;
    @SerializedName("banner_url")
    private String banner_url;

    protected
    TopBannerModel(Parcel in) {
        id = in.readInt();
        image = in.readString();
        banner_url = in.readString();
    }

    public static final Creator<TopBannerModel> CREATOR = new Creator<TopBannerModel>() {
        @Override
        public
        TopBannerModel createFromParcel(Parcel in) {
            return new TopBannerModel(in);
        }

        @Override
        public
        TopBannerModel[] newArray(int size) {
            return new TopBannerModel[size];
        }
    };

    public
    int getId() {
        return id;
    }

    public
    String getImage() {
        return image;
    }

    public
    String getBanner_url() {
        return banner_url;
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
        dest.writeString(image);
        dest.writeString(banner_url);
    }
}
