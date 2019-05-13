package com.example.bookmytable;

import java.io.Serializable;
import java.util.ArrayList;

public class RestaurantBO implements Serializable {
    String name;
    String address;
    String ownerId;
    public String reference;
    ArrayList<RestaurantBO> list1;

    public RestaurantBO() {

    }

    public RestaurantBO(ArrayList<RestaurantBO> list1) {
        this.list1 = list1;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
