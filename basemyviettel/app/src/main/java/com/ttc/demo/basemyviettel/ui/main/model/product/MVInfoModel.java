package com.ttc.demo.basemyviettel.ui.main.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public
class MVInfoModel implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private String price;
    @SerializedName("salePrice")
    private String salePrice;
    @SerializedName("discountPercent")
    private String discountPercent;
    @SerializedName("link")
    private String link;

    protected
    MVInfoModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        image = in.readString();
        price = in.readString();
        salePrice = in.readString();
        discountPercent = in.readString();
        link = in.readString();
    }

    public static final Creator<MVInfoModel> CREATOR = new Creator<MVInfoModel>() {
        @Override
        public
        MVInfoModel createFromParcel(Parcel in) {
            return new MVInfoModel(in);
        }

        @Override
        public
        MVInfoModel[] newArray(int size) {
            return new MVInfoModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public String getLink() {
        return link;
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
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(price);
        dest.writeString(salePrice);
        dest.writeString(discountPercent);
        dest.writeString(link);
    }
}
