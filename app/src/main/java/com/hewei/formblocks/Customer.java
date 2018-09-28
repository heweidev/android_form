package com.hewei.formblocks;

import android.os.Parcel;
import android.os.Parcelable;

import com.hewei.formblocks.model.Address;
import com.hewei.formblocks.model.EIdType;

public final class Customer implements Parcelable {
    String name;
    String idNo;
    EIdType idType;
    Address addr;
    String mobileNo;

    protected Customer(Parcel in) {
        name = in.readString();
        idNo = in.readString();
        mobileNo = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public EIdType getIdType() {
        return idType;
    }

    public void setIdType(EIdType idType) {
        this.idType = idType;
    }

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Customer() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(idNo);
        dest.writeString(mobileNo);
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", idNo='" + idNo + '\'' +
                ", idType=" + idType +
                ", addr=" + addr +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
