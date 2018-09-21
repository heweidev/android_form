package com.hewei.formblocks;

import android.os.Parcel;
import android.os.Parcelable;

final class Customer implements Parcelable {
    String name;
    String idNo;
    String idType;
    String addr;
    String mobileNo;

    protected Customer(Parcel in) {
        name = in.readString();
        idNo = in.readString();
        idType = in.readString();
        addr = in.readString();
        mobileNo = in.readString();
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(idNo);
        dest.writeString(idType);
        dest.writeString(addr);
        dest.writeString(mobileNo);
    }
}
