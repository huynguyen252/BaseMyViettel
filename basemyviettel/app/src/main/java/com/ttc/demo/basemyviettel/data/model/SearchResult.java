package com.ttc.demo.basemyviettel.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SearchResult implements Parcelable {
    @SerializedName("errorCode")
    Integer errorCode = 0;

    @SerializedName("message")
    String message  ="Thành công";


    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel in) {
            return new SearchResult(in);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };

    @Override
    public String toString(){
        return "SearchResult{" +
                "errorCode=" +
                errorCode +
                ", message ='" +
                message + '\'' +
                '}';
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleResult that = (SimpleResult) o;

        if (!errorCode.equals(that.errorCode)) return false;
        return message.equals(that.message);

    }

    @Override
    public int hashCode() {
        int result = errorCode.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.errorCode);
        parcel.writeString(this.message);
    }
    public SearchResult() {

    }
    protected SearchResult(Parcel in) {
        this.errorCode = in.readInt();
        this.message = in.readString();
    }
}
