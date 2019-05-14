package com.example.bookmytable;

import java.io.Serializable;

public class RestaurantBO implements Serializable {
    String name;
    String address;
    Integer ratingbar;

    String ownerId;
    public String reference;

    public Integer getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(Integer ratingbar) {
        this.ratingbar = ratingbar;
    }

    public RestaurantBO() {

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
