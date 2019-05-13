package com.example.bookmytable;

import android.net.Uri;

public class UserInfoBO {
    private String name;
    private String email;
    private Uri photoUrl;
    private String uid;
    private Boolean isCustomer = false;
    private  Boolean isRestaurant = false;

    public UserInfoBO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Boolean getCustomer() {
        return isCustomer;
    }

    public void setCustomer(Boolean customer) {
        isCustomer = customer;
    }

    public Boolean getRestaurant() {
        return isRestaurant;
    }

    public void setRestaurant(Boolean restaurant) {
        isRestaurant = restaurant;
    }
}
