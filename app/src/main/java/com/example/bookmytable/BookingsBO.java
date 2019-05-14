package com.example.bookmytable;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingsBO implements Parcelable {
    String restaurantName;
    Integer noOfPeople;
    Integer status;
    String bookingId;
    String date;
    String time;
    String customerId;

    public BookingsBO(){

    }

    public BookingsBO(Parcel in) {
        restaurantName = in.readString();
        noOfPeople = in.readInt();
        status = in.readInt();
        bookingId = in.readString();
        date = in.readString();
        time = in.readString();
        customerId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(restaurantName);
        dest.writeInt(noOfPeople);
        dest.writeInt(status);
        dest.writeString(bookingId);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(customerId);
    }

    // This is to de-serialize the object
    public static final Parcelable.Creator<BookingsBO> CREATOR = new Parcelable.Creator<BookingsBO>() {
        public BookingsBO createFromParcel(Parcel in) {
            return new BookingsBO(in);
        }

        public BookingsBO[] newArray(int size) {
            return new BookingsBO[size];
        }
    };

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(Integer noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
