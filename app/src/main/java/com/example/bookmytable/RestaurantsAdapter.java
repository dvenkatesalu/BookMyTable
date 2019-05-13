package com.example.bookmytable;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class RestaurantsAdapter extends ArrayAdapter {

        private final Context classContext;

        //private final String[] ;
        private final String[] restaurantName;
        private final String[] restaurantLocation;
       // private final Integer[] restaurantImage;
        private final Integer[] restaurantRating;

        public RestaurantsAdapter(Context actCtx, String[] restaurantName, String[] restaurantLocation, Integer[] restaurantRating) {
            super(actCtx, R.layout.text_view);
         this.classContext = actCtx;
       // this.idList = bookID;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        //this.restaurantImage = restaurantImage;
            this.restaurantRating = restaurantRating;
        }

    public View getView(int pos, View view, ViewGroup parentView) {
            //LayoutInflater layoutInflater = this.classContext.getLayoutInflater();
        LayoutInflater layoutInflater = LayoutInflater.from(classContext);
        View listViewElement = layoutInflater.inflate(R.layout.text_view, null, true);
        TextView title = (TextView) listViewElement.findViewById(R.id.textView);
//        TextView author = (TextView) listViewElement.findViewById(R.id.textView4);
//        TextView bookid = (TextView) listViewElement.findViewById(R.id.textView2);

        RatingBar restRating = (RatingBar) listViewElement.findViewById(R.id.ratingBar);

        return listViewElement; } }

