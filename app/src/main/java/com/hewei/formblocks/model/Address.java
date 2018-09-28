package com.hewei.formblocks.model;

public class Address {
    public static Address INSTANCE = new Address("深圳市", "福田区", "123号");

    public String city;
    public String street;
    public String no;

    public Address(String city, String street, String no) {
        this.city = city;
        this.street = street;
        this.no = no;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", no='" + no + '\'' +
                '}';
    }
}
