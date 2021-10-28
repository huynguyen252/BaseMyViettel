package com.ttc.demo.basemyviettel.ui.main.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class MVThemeProductModel implements Parcelable {
    @SerializedName("theme_id")
    private String mThemeId;

    @SerializedName("theme_name")
    private String mThemeName;

    @SerializedName("listProduct")
    private List<MVProductGroupModel> mListProductGroup;

    protected
    MVThemeProductModel(Parcel in) {
        mThemeId = in.readString();
        mThemeName = in.readString();
        mListProductGroup = in.createTypedArrayList(MVProductGroupModel.CREATOR);
    }

    public static final Creator<MVThemeProductModel> CREATOR = new Creator<MVThemeProductModel>() {
        @Override
        public
        MVThemeProductModel createFromParcel(Parcel in) {
            return new MVThemeProductModel(in);
        }

        @Override
        public
        MVThemeProductModel[] newArray(int size) {
            return new MVThemeProductModel[size];
        }
    };

    public String getThemeId() {
        return mThemeId;
    }

    public String getThemeName() {
        return mThemeName;
    }

    public List<MVProductGroupModel> getListProductGroup() {
        return mListProductGroup;
    }

    @Override
    public
    int describeContents() {
        return 0;
    }

    @Override
    public
    void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mThemeId);
        dest.writeString(mThemeName);
        dest.writeTypedList(mListProductGroup);
    }
}
