package com.example.bookmytable;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DisplayRestaurants extends AppCompatActivity {

    private ListView mListView;
    private static final String API_KEY = "AIzaSyDi1OagTMGaVbLZb5UW8rHpKTWkmgaMZK4";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_DETAILS = "/details";
    private static final String TYPE_SEARCH = "/nearbysearch";
    private static final String OUT_JSON = "/json?";
    private static final String LOG_TAG = "ListRest";
    private static String[] restname = new String[]{};
    private static String[] restLocation = new String[]{};
    //private static String[] readauthor = new String[]{};
    private static Integer[] restRating = new Integer[]{};
    private static RestaurantsAdapter restAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Keep the Nav Drawer, inflate a view as part of the nav link
        /*LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.search_restaurant, null, false);
        mDrawer.addView(contentView,0);*/

//        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_restaurant);

        Intent intent = getIntent();
        String longitude = intent.getStringExtra("long");
        String latitude = intent.getStringExtra("lat");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Double lng = Double.parseDouble(longitude);
        Double lat = Double.parseDouble(latitude);
        int radius = 1000;

        ArrayList<Place> list = search(lat, lng, radius);

        if (list != null)
        {
            mListView = (ListView) findViewById(R.id.listView);

//            restAdapter = new RestaurantsAdapter(this, restname, restLocation, restRating);
//            mListView.setAdapter(restAdapter);
//            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.text_view, list);
//            mListView.setAdapter(adapter);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.text_view,R.id.tv, list);
            mListView.setAdapter(adapter);
//            ArrayAdapter adapter1 = new ArrayAdapter(this, R.layout.text_view,R.id.tv1, list);
//            mListView.setAdapter(adapter1);
        }
    }

    public static ArrayList<Place> search(double lat, double lng, int radius) {
        ArrayList<Place> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_SEARCH);
            sb.append(OUT_JSON);
            sb.append("location=" + String.valueOf(lat) + "," + String.valueOf(lng));
            sb.append("&radius=" + String.valueOf(radius));
            sb.append("&type=restaurant");
            sb.append("&key=" + API_KEY);

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("results");

            // Extract the descriptions from the results
            resultList = new ArrayList<Place>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                Place place = new Place();
                place.reference = predsJsonArray.getJSONObject(i).getString("reference");
                place.name = predsJsonArray.getJSONObject(i).getString("name");
                place.rating = predsJsonArray.getJSONObject(i).getInt("rating");
                place.location = predsJsonArray.getJSONObject(i).getString("vicinity");



                resultList.add(place);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error processing JSON results", e);
        }

        return resultList;
    }


    //Value Object for the ArrayList
    public static class Place {
        private String reference;
        private String name;
        private int image;
        private int rating;
        private String location;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public Place(){
            super();
        }
        @Override
        public String toString(){
            return this.name+this.rating+this.location; //This is what returns the name of each restaurant for array list
        }
    }
}