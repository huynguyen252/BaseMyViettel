package com.ttc.demo.basemyviettel.ui.main.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public
class DistrictModel implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("parent_id")
    private String parent_id;

    protected
    DistrictModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        parent_id = in.readString();
    }

    public static final Creator<DistrictModel> CREATOR = new Creator<DistrictModel>() {
        @Override
        public
        DistrictModel createFromParcel(Parcel in) {
            return new DistrictModel(in);
        }

        @Override
        public
        DistrictModel[] newArray(int size) {
            return new DistrictModel[size];
        }
    };

    public
    String getId() {
        return id;
    }

    public
    String getName() {
        return name;
    }

    public
    String getParent_id() {
        return parent_id;
    }

    @Override
    public
    int describeContents() {
        return 0;
    }

    @Override
    public
    void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(parent_id);
    }
}
