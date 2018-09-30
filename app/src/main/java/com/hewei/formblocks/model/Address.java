package com.hewei.formblocks.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {
    public static Address INSTANCE = new Address("深圳市", "福田区", "123号");

    public String city;
    public String street;
    public String no;

    public Address() {

    }

    public Address(String city, String street, String no) {
        this.city = city;
        this.street = street;
        this.no = no;
    }

    protected Address(Parcel in) {
        city = in.readString();
        street = in.readString();
        no = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(street);
        dest.writeString(no);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    @Override
    public String toString() {
        return city + street + no;
    }


}
