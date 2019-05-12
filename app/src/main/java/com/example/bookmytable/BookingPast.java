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

public class BookingPast extends Fragment {

    public TextView restaurantName, bookingTime;
    public Button giveReview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking_past, container, false);
        restaurantName = v.findViewById(R.id.textView_restaurant_name_booking_past);
        bookingTime = v.findViewById(R.id.textView_time_booking_past);
        giveReview = v.findViewById(R.id.button_booking_past_review);
        return v;
    }
}
