package com.ttc.demo.basemyviettel.ui.main.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public
class AreaModel implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("code")
    private String code;

    protected
    AreaModel(Parcel in) {
        name = in.readString();
        code = in.readString();
    }

    public static final Creator<AreaModel> CREATOR = new Creator<AreaModel>() {
        @Override
        public
        AreaModel createFromParcel(Parcel in) {
            return new AreaModel(in);
        }

        @Override
        public
        AreaModel[] newArray(int size) {
            return new AreaModel[size];
        }
    };

    public
    String getName() {
        return name;
    }

    public
    String getCode() {
        return code;
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
        dest.writeString(code);
    }
}
