package com.example.bookmytable;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DisplayRestaurantsForCustomers extends AppCompatActivity {

    private ListView mListView;

    ArrayList<RestaurantBO> restaurants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_restaurant);

        getRestaurantsToDisplay();


        if (restaurants != null)
        {
            mListView = (ListView) findViewById(R.id.listView);

//            restAdapter = new RestaurantsAdapter(this, restname, restLocation, restRating);
//            mListView.setAdapter(restAdapter);
            //restAdapter= new RestaurantsAdapter(restaurantBO,getApplicationContext());
             // CustomAdapter adapter = new CustomAdapter(this, R.layout.text_view, list);
//            mListView.setAdapter(adapter);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.text_view,R.id.tv, restaurants);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    LinearLayout ll = (LinearLayout) view;
                    TextView tv = (TextView) ll.findViewById(R.id.tv);
                    final String text = tv.getText().toString();

                    // CALL MAKE RESERVATION
                    //Intent intent = new Intent(this,DisplayRestaurants.class);
                    //TODO SET THE CHOSEN BO HERE
                    //intent.putExtra("pickesRestaurant", longit);
                    //startActivity(intent);


                    Toast.makeText(getApplicationContext(), "Restaurant added to Database!", Toast.LENGTH_SHORT).show();
                    //TODO: LAND THE USER ON APPROVE BOOKINGS

                }
            });
//            ArrayAdapter adapter1 = new ArrayAdapter(this, R.layout.text_view,R.id.tv1, list);
//            mListView.setAdapter(adapter1);
        }
    }

    public void getRestaurantsToDisplay(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference fbRef =
                database.getReference("restaurants");

        fbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    RestaurantBO res = dataSnapshot.getValue(RestaurantBO.class);
                    Log.d("Retrieving user :", res.toString());
                    restaurants.add(res);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.d("Error", "Error" + firebaseError.getMessage());
            }
        });
    }

}