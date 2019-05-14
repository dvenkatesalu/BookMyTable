package com.example.bookmytable;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private static Integer[] restRating = new Integer[]{};
    ArrayList<RestaurantBO> restaurantBO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_restaurant);

        Intent intent = getIntent();
        String longitude = intent.getStringExtra("long");
        String latitude = intent.getStringExtra("lat");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Double lng = Double.parseDouble(longitude);
        Double lat = Double.parseDouble(latitude);
        int radius = 1000;

        ArrayList<RestaurantBO> list = search(lat, lng, radius);


        if (list != null)
        {
            mListView = (ListView) findViewById(R.id.listView);
            CustomAdapter adapter = new CustomAdapter(this,list);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    LinearLayout ll = (LinearLayout) view;
                    TextView tv = (TextView) ll.findViewById(R.id.tv);
                    TextView tv1 = (TextView) ll.findViewById(R.id.tv1);
                    RatingBar rate = (RatingBar)ll.findViewById(R.id.listitemrating);
                    final String text = tv.getText().toString();
                    final String location = tv1.getText().toString();
                    final Float ratingOfRestaurant = rate.getRating();

                    setRestaurantToOwner(text,location,ratingOfRestaurant);
                    Toast.makeText(getApplicationContext(), "Restaurant added to Database!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DisplayRestaurants.this,MainActivity.class));

                }
            });
        }
    }

    public void setRestaurantToOwner( String resName, String loc, Float ratings ){
        Toast.makeText(DisplayRestaurants.this, "Inside add resName to db.",
                Toast.LENGTH_SHORT).show();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        String key = user.getDisplayName() + "_" + user.getUid();
        String resId = resName + key;
        DatabaseReference keyRef =
                database.getReference("restaurants");//.child(resId);
        keyRef.child("name").setValue(resName);
        keyRef.child("address").setValue(loc);
       keyRef.child("rating").setValue(ratings);
        keyRef.child("ownerId").setValue(key);
        keyRef.child("reference").setValue("");
        keyRef.child("resId").setValue(resId);
    }

    public static ArrayList<RestaurantBO> search(double lat, double lng, int radius) {
        ArrayList<RestaurantBO> resultList = null;

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
            resultList = new ArrayList<RestaurantBO>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                RestaurantBO place = new RestaurantBO();
                place.reference = predsJsonArray.getJSONObject(i).getString("reference");
                place.name = predsJsonArray.getJSONObject(i).getString("name");
                place.rating = predsJsonArray.getJSONObject(i).getInt("rating");
                place.address = predsJsonArray.getJSONObject(i).getString("vicinity");
                resultList.add(place);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error processing JSON results", e);
        }

        return resultList;
    }


}