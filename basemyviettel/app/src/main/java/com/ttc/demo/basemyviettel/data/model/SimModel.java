package com.ttc.demo.basemyviettel.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimModel implements Parcelable {


        @SerializedName("isdn")
        @Expose
        private String isdn = null;
        @SerializedName("pre_price")
        @Expose
        private String prePrice = null;
        @SerializedName("pos_price")
        @Expose
        private String posPrice = null;
        @SerializedName("pledgeTime")
        @Expose
        private String pledgeTime = null;
        @SerializedName("desc")
        @Expose
        private String desc = null;
        @SerializedName("unit")
        @Expose
        private String unit = null;

        @SerializedName("ownerId")
        @Expose
        private String ownerId;

        @SerializedName("prodOfferId")
        @Expose
        private String prodOfferId;

        @Override
        public String toString() {
            return "SimModel{" +
                    "isdn='" + isdn + '\'' +
                    ", prePrice='" + prePrice + '\'' +
                    ", posPrice='" + posPrice + '\'' +
                    ", pledgeTime='" + pledgeTime + '\'' +
                    ", desc='" + desc + '\'' +
                    ", unit='" + unit + '\'' +
                    ", desc='" + ownerId + '\'' +
                    ", unit='" + prodOfferId + '\'' +
                    '}';
        }

        public String getIsdn() {
            return isdn;
        }

        public void setIsdn(String isdn) {
            this.isdn = isdn;
        }

        public String getPrePrice() {
            return prePrice;
        }

        public void setPrePrice(String prePrice) {
            this.prePrice = prePrice;
        }

        public String getPosPrice() {
            return posPrice;
        }

        public void setPosPrice(String posPrice) {
            this.posPrice = posPrice;
        }

        public String getPledgeTime() {
            return pledgeTime;
        }

        public void setPledgeTime(String pledgeTime) {
            this.pledgeTime = pledgeTime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setProdOfferId(String prodOfferId) {
            this.prodOfferId = prodOfferId;
        }

        public String getProdOfferId() {
            return prodOfferId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.isdn);
            dest.writeString(this.prePrice);
            dest.writeString(this.posPrice);
            dest.writeString(this.pledgeTime);
            dest.writeString(this.desc);
            dest.writeString(this.unit);
            dest.writeString(this.ownerId);
            dest.writeString(this.prodOfferId);
        }

        public SimModel() {
        }

        protected SimModel(Parcel in) {
            this.isdn = in.readString();
            this.prePrice = in.readString();
            this.posPrice = in.readString();
            this.pledgeTime = in.readString();
            this.desc = in.readString();
            this.unit = in.readString();
            this.ownerId = in.readString();
            this.prodOfferId = in.readString();
        }

        public static final Creator<SimModel> CREATOR = new Creator<SimModel>() {
            @Override
            public SimModel createFromParcel(Parcel source) {
                return new SimModel(source);
            }

            @Override
            public SimModel[] newArray(int size) {
                return new SimModel[size];
            }
        };
}
