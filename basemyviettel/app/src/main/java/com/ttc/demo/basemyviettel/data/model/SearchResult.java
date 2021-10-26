package com.ttc.demo.basemyviettel.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult extends SimpleResult {
    @SerializedName("data")
    private List<NumberModel> data;

    public List<NumberModel> getData() {
        return data;
    }

//    public class SearchResultModel {
//        @SerializedName("isdn")
//        String isdn;
//        @SerializedName("pre_price")
//        String pre_price;
//        @SerializedName("pos_price")
//        String pos_price;
////        @SerializedName("pledge_time")
////        String pledge_time;
//        @SerializedName("isdn_type")
//        int isdn_type;
//        @SerializedName("unit")
//        String unit;
//
//        public SearchResultModel(String isdn, String pre_price, String pos_price, int isdn_type, String unit) {
//            this.isdn = isdn;
//            this.pre_price = pre_price;
//            this.pos_price = pos_price;
//            this.isdn_type = isdn_type;
//            this.unit = unit;
//        }
//
//        public String getIsdn() {
//            return isdn;
//        }
//
//        public void setIsdn(String isdn) {
//            this.isdn = isdn;
//        }
//
//        public String getPre_price() {
//            return pre_price;
//        }
//
//        public void setPre_price(String pre_price) {
//            this.pre_price = pre_price;
//        }
//
//        public String getPos_price() {
//            return pos_price;
//        }
//
//        public void setPos_price(String pos_price) {
//            this.pos_price = pos_price;
//        }
//
//        public int getIsdn_type() {
//            return isdn_type;
//        }
//
//        public void setIsdn_type(int isdn_type) {
//            this.isdn_type = isdn_type;
//        }
//
//        public String getUnit() {
//            return unit;
//        }
//
//        public void setUnit(String unit) {
//            this.unit = unit;
//        }
//    }
//
}
