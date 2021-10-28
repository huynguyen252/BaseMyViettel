package com.ttc.demo.basemyviettel.ui.main.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public
class MVThemeProductResponse implements Parcelable {
    @SerializedName("data")
    private List<MVThemeProductModel> mListThemeProductModel;

    protected
    MVThemeProductResponse(Parcel in) {
        mListThemeProductModel = in.createTypedArrayList(MVThemeProductModel.CREATOR);
    }

    public static final Creator<MVThemeProductResponse> CREATOR = new Creator<MVThemeProductResponse>() {
        @Override
        public
        MVThemeProductResponse createFromParcel(Parcel in) {
            return new MVThemeProductResponse(in);
        }

        @Override
        public
        MVThemeProductResponse[] newArray(int size) {
            return new MVThemeProductResponse[size];
        }
    };

    public List<MVThemeProductModel> getListThemeProductModel() {
        return mListThemeProductModel;
    }

    @Override
    public
    int describeContents() {
        return 0;
    }

    @Override
    public
    void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mListThemeProductModel);
    }
}
