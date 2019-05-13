package com.example.bookmytable;

import java.io.Serializable;

public class RestaurantBO implements Serializable {
    String restaurantName;
    String restaurantImage;
    String restaurantAddress;
    float restaurantRating;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public float getRestaurantRating() {
        return restaurantRating;
    }

    public void setRestaurantRating(float restaurantRating) {
        this.restaurantRating = restaurantRating;
    }


}
