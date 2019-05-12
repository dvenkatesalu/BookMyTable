package com.example.bookmytable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BookingUpcoming extends Fragment {
    public TextView restaurantName,bookingTime,seats;
    public Button editBooking,cancelBooking;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking_upcoming, container, false);
        restaurantName = v.findViewById(R.id.textView_restaurantname_booking_upcoming);
        bookingTime = v.findViewById(R.id.textView_time_booking_upcoming);
        seats = v.findViewById(R.id.textView_no_of_seats_upcoming);
        editBooking = v.findViewById(R.id.button_edit_booking_upcoming);
        cancelBooking = v.findViewById(R.id.button_cancel_booking_upcoming);
        return v;
    }
}
