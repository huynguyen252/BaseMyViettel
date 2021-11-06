package com.ttc.demo.basemyviettel.ui.main.detail.model;

public
class InfoCustomer {
    private String name;
    private String phone;
    private String birthDay;
    private String city;
    private String district;
    private String commune;
    private String addressDetail;
    private String infoPapers;
    private String ngayCap;
    private String noiCap;

    public
    InfoCustomer(String name, String phone, String birthDay, String city, String district, String commune, String addressDetail, String infoPapers, String ngayCap, String noiCap) {
        this.name = name;
        this.phone = phone;
        this.birthDay = birthDay;
        this.city = city;
        this.district = district;
        this.commune = commune;
        this.addressDetail = addressDetail;
        this.infoPapers = infoPapers;
        this.ngayCap = ngayCap;
        this.noiCap = noiCap;
    }

    public
    String getName() {
        return name;
    }

    public
    void setName(String name) {
        this.name = name;
    }

    public
    String getPhone() {
        return phone;
    }

    public
    void setPhone(String phone) {
        this.phone = phone;
    }

    public
    String getBirthDay() {
        return birthDay;
    }

    public
    void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public
    String getCity() {
        return city;
    }

    public
    void setCity(String city) {
        this.city = city;
    }

    public
    String getDistrict() {
        return district;
    }

    public
    void setDistrict(String district) {
        this.district = district;
    }

    public
    String getCommune() {
        return commune;
    }

    public
    void setCommune(String commune) {
        this.commune = commune;
    }

    public
    String getAddressDetail() {
        return addressDetail;
    }

    public
    void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public
    String getInfoPapers() {
        return infoPapers;
    }

    public
    void setInfoPapers(String infoPapers) {
        this.infoPapers = infoPapers;
    }

    public
    String getNgayCap() {
        return ngayCap;
    }

    public
    void setNgayCap(String ngayCap) {
        this.ngayCap = ngayCap;
    }

    public
    String getNoiCap() {
        return noiCap;
    }

    public
    void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }
}
