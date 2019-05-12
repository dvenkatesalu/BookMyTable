package com.example.bookmytable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookingPast extends Fragment {
    ListView listView;
    BookingPastAdapter adapter;
    List<RestaurantBO> restaurantBOList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking_past, container, false);
        listView = v.findViewById(R.id.listView_booking_past);
        adapter = new BookingPastAdapter(getActivity(),R.layout.fragment_booking_past,restaurantBOList);
        listView.setAdapter(adapter);
        return v;
    }
}
