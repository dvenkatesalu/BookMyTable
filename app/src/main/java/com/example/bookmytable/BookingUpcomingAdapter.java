package com.example.bookmytable;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookingUpcomingAdapter extends BaseAdapter {
    public TextView restaurantName,bookingTime,seats;
    public Button editBooking,cancelBooking;
    Context context;
    public BookingUpcomingAdapter(FragmentActivity bookingUpcoming, int fragment_booking_upcoming, List<RestaurantBO> restaurantBOList) {
        this.context = bookingUpcoming;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v==null) {
            v = LayoutInflater.from(context).inflate(R.layout.listitem_booking_upcoming,parent,false);
            restaurantName = v.findViewById(R.id.textView_restaurantname_booking_upcoming);
            bookingTime = v.findViewById(R.id.textView_time_booking_upcoming);
            seats = v.findViewById(R.id.textView_no_of_seats_upcoming);
            editBooking = v.findViewById(R.id.button_edit_booking_upcoming);
            cancelBooking = v.findViewById(R.id.button_cancel_booking_upcoming);
        }
        return v;
    }
}
