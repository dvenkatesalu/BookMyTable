package com.example.bookmytable;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

class BookingPastAdapter extends BaseAdapter {
    Context context;
    public TextView restaurantName,time;
    public Button review;

    public BookingPastAdapter(FragmentActivity activity, int fragment_booking_past, List<RestaurantBO> restaurantBOList) {
        this.context = activity;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        View v = convertView;
        if(v==null){
            v=LayoutInflater.from(context).inflate(R.layout.listitem_booking_past,parent,false);
            restaurantName=v.findViewById(R.id.textView_restaurant_name_booking_past);
            time=v.findViewById(R.id.textView_time_booking_past);
            review = v.findViewById(R.id.button_booking_past_review);
        }
        return v;
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
}
