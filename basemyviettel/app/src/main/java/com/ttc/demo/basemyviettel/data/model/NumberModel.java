package com.ttc.demo.basemyviettel.data.model;

import android.text.TextUtils;

import com.gemvietnam.utils.StringUtils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class NumberModel  extends RealmObject implements Serializable {
    @SerializedName("isdn")
    private String isdn;

    @SerializedName("pre_price")
    private Long prePrice;

    @SerializedName("pos_price")
    private Long posPrice;

    @SerializedName("unit")
    private String unit;

    @SerializedName("desc")
    private String desc;

    @SerializedName("ownerId")
    private String ownerId;

    @SerializedName("prodOfferId")
    private String prodOfferId;



    private Long mnpPrice, feePosPre, mnpFee;

    private int mnpType = 0;

    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public NumberModel(String number) {
        isdn = number;
        prePrice = 0L;
        posPrice = 0L;
        unit = "";
        ownerId = "";
        prodOfferId = "";

    }

    public NumberModel(SimModel simModel){
        this.isdn = simModel.getIsdn();
        this.prePrice = Long.parseLong(simModel.getPrePrice());
        this.posPrice = Long.parseLong(simModel.getPosPrice());
        this.desc = simModel.getDesc();
        this.prodOfferId = simModel.getProdOfferId();
        this.ownerId = simModel.getOwnerId();
    }


    public NumberModel() {
    }

    public String getIsdn() {
        if(!TextUtils.isEmpty(isdn)) {
            if (isdn.charAt(0) != '0' && isdn.length()<=9) {
                return "0" + isdn;
            } else {
                return isdn;
            }
        }
        else
        {
            return "";
        }
    }

    public String getIsdnRawFromServer(){
        if(!StringUtils.isNullOrEmpty(isdn)) {
            return isdn;
        }else{
            return "";
        }
    }


    public String getDesc() {
        return desc;
    }

    public Long getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(Long prePrice) {
        this.prePrice = prePrice;
    }

    public Long getPosPrice() {
        return posPrice;
    }

    public void setPosPrice(Long posPrice) {
        this.posPrice = posPrice;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getUnit() {
        return unit;
    }

    public Long getMnpSumPrice() {
        return mnpPrice == null ? 0 : mnpPrice;
    }

    public void setMnpSumPrice(Long mnpPrice) {
        this.mnpPrice = mnpPrice;
    }

    public int getMnpType() {
        return mnpType;
    }

    public void setMnpType(int mnpType) {
        this.mnpType = mnpType;
    }

    public Long getMnpFeePosPre() {
        return feePosPre;
    }

    public void setMnpFeePosPre(Long feePosPre) {
        this.feePosPre = feePosPre;
    }

    public Long getMnpFee() {
        return mnpFee;
    }

    public void setMnpFee(Long mnpFee) {
        this.mnpFee = mnpFee;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getProdOfferId() {
        return prodOfferId;
    }

    public void setProdOfferId(String prodOfferId) {
        this.prodOfferId = prodOfferId;
    }
}


