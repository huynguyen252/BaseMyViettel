package com.ttc.demo.basemyviettel.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OmiSearchSim extends SearchResult {

    @SerializedName("data")
    OmiSearchSimModel data;

    protected OmiSearchSim(Parcel in) {
        super(in);
    }

    public OmiSearchSimModel getData() {
        return data;
    }

    public void setData(OmiSearchSimModel data) {
        this.data = data;
    }
    public class OmiSearchSimModel {
        @SerializedName("isdn")
        private String isdn = null;
        @SerializedName("pre_price")
        private String prePrice = null;
        @SerializedName("pos_price")
        private String posPrice = null;
        @SerializedName("pledgeTime")
        private String pledgeTime = null;
        @SerializedName("isdn_type")
        private int isdn_type;
        @SerializedName("unit")
        private String unit;


        @Override
        public String toString() {
            return "SimModel{" +
                    "isdn='" + isdn + '\'' +
                    ", prePrice='" + prePrice + '\'' +
                    ", posPrice='" + posPrice + '\'' +
                    ", pledgeTime='" + pledgeTime + '\'' +
                    ", desc='" + isdn_type + '\'' +
                    ", unit='" + unit + '\'' +
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


        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getIsdn_type() {
            return isdn_type;
        }

        public void setIsdn_type(int isdn_type) {
            this.isdn_type = isdn_type;
        }


//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeString(this.isdn);
//            dest.writeString(this.prePrice);
//            dest.writeString(this.posPrice);
//            dest.writeString(this.pledgeTime);
//            dest.writeString(this.unit);
//        }

//
//        protected OmiSearchSim(Parcel in) {
//            super(in);
//            this.isdn = in.readString();
//            this.prePrice = in.readString();
//            this.posPrice = in.readString();
//            this.pledgeTime = in.readString();
//            this.unit = in.readString();
//        }
//
//        public static final Creator<OmiSearchSim> CREATOR = new Creator<OmiSearchSim>() {
//            @Override
//            public OmiSearchSim createFromParcel(Parcel source) {
//                return new OmiSearchSim(source);
//            }
//
//            @Override
//            public OmiSearchSim[] newArray(int size) {
//                return new OmiSearchSim[size];
//            }
//        };

    }

}
