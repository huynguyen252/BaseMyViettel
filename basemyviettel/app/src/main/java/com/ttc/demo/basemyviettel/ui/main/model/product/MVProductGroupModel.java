package com.ttc.demo.basemyviettel.ui.main.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class MVProductGroupModel implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("info")
    private List<MVInfoModel> info;

    protected
    MVProductGroupModel(Parcel in) {
        name = in.readString();
        id = in.readString();
        info = in.createTypedArrayList(MVInfoModel.CREATOR);
    }

    public static final Creator<MVProductGroupModel> CREATOR = new Creator<MVProductGroupModel>() {
        @Override
        public
        MVProductGroupModel createFromParcel(Parcel in) {
            return new MVProductGroupModel(in);
        }

        @Override
        public
        MVProductGroupModel[] newArray(int size) {
            return new MVProductGroupModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<MVInfoModel> getInfo() {
        return info;
    }

    @Override
    public
    int describeContents() {
        return 0;
    }

    @Override
    public
    void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeTypedList(info);
    }
}
