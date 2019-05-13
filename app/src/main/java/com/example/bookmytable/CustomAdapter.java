package com.example.bookmytable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bookmytable.R;
import com.example.bookmytable.RestaurantBO;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<RestaurantBO> implements View.OnClickListener{

    private ArrayList<RestaurantBO> dataSet = new ArrayList<>();
    Context mContext;

    public CustomAdapter(Context context, ArrayList<RestaurantBO> list) {
        super(context,0,list);
        dataSet = list;
        this.mContext=context;

    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        ImageView info;
    }


    public CustomAdapter(ArrayList<RestaurantBO> data, Context context) {
        super(context, R.layout.text_view, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        RestaurantBO restaurantBO=(RestaurantBO)object;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.text_view,parent,false);

        RestaurantBO currentMovie = dataSet.get(position);

//        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
//        image.setImageResource(currentMovie.getmImageDrawable());

        TextView name = (TextView) listItem.findViewById(R.id.tv);
        name.setText(currentMovie.getRestaurantName());

        TextView release = (TextView) listItem.findViewById(R.id.tv1);
        release.setText(currentMovie.getRestaurantAddress());

        RatingBar ratingBar = (RatingBar)listItem.findViewById(R.id.listitemrating);
        ratingBar.setRating(currentMovie.getRestaurantRating());

        return listItem;
    }


}