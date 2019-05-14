package com.example.bookmytable;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingsBO implements Parcelable {
    String restaurantId;
    Integer noOfPeople;
    Integer status;
    String bookingId;
    String date;
    String fromtime;
    String toTime;
    String customerId;

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        fromtime = fromtime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        toTime = toTime;
    }

    public BookingsBO(){

    }

    public BookingsBO(Parcel in) {
        restaurantId = in.readString();
        noOfPeople = in.readInt();
        status = in.readInt();
        bookingId = in.readString();
        date = in.readString();
        fromtime = in.readString();
        toTime = in.readString();
        customerId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(restaurantId);
        dest.writeInt(noOfPeople);
        dest.writeInt(status);
        dest.writeString(bookingId);
        dest.writeString(date);
        dest.writeString(fromtime);
        dest.writeString(toTime);
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

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
