package com.ttc.demo.basemyviettel.ui.main.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class AreaDetailModel implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("ParentCity")
    private
    List<DistrictModel> parentCity;

    protected
    AreaDetailModel(Parcel in) {
        name = in.readString();
        parentCity = in.createTypedArrayList(DistrictModel.CREATOR);
    }

    public static final Creator<AreaDetailModel> CREATOR = new Creator<AreaDetailModel>() {
        @Override
        public
        AreaDetailModel createFromParcel(Parcel in) {
            return new AreaDetailModel(in);
        }

        @Override
        public
        AreaDetailModel[] newArray(int size) {
            return new AreaDetailModel[size];
        }
    };

    public
    String getName() {
        return name;
    }

    public
    List<DistrictModel> getParentCity() {
        return parentCity;
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
        dest.writeTypedList(parentCity);
    }
}
