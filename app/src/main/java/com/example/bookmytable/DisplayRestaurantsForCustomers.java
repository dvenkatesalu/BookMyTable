package com.example.bookmytable;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
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

import java.util.ArrayList;

public class DisplayRestaurantsForCustomers extends AppCompatActivity {

    private ListView mListView;
    private static final String API_KEY = "AIzaSyDi1OagTMGaVbLZb5UW8rHpKTWkmgaMZK4";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_SEARCH = "/nearbysearch";
    private static final String OUT_JSON = "/json?";
    private static final String LOG_TAG = "ListRest";

    ArrayList<RestaurantBO> restaurants;

    private View mLayout;

    public static DrawerLayout mDrawer;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_restaurant_user);

        mLayout = findViewById(R.id.search_restaurant_user);

        mDrawer = findViewById(R.id.search_restaurant_user);
        t = new ActionBarDrawerToggle(this, mDrawer,R.string.Open, R.string.Close);

        mDrawer.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nv2);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.bookings:
                        startActivity(new Intent(DisplayRestaurantsForCustomers.this,BookingActivity.class));

                    case R.id.book:
                        startActivity(new Intent(DisplayRestaurantsForCustomers.this,DisplayRestaurantsForCustomers.class));
                        break;
                    case R.id.signout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(DisplayRestaurantsForCustomers.this,LoginActivity.class));
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });
        // get data from Firebase database
        getRestaurantsToDisplay();


        if (restaurants != null)
        {
            mListView = (ListView) findViewById(R.id.listView);
            CustomAdapter adapter = new CustomAdapter(this,restaurants);
            mListView.setAdapter(adapter);


            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    LinearLayout ll = (LinearLayout) view;
                    TextView tv = (TextView) ll.findViewById(R.id.tv);
                    final String text = tv.getText().toString();

                    // CALL MAKE RESERVATION
                    //Intent intent = new Intent(this,MakingBookingActivity.class);
                    //TODO SET THE CHOSEN BO HERE
                    //intent.putExtra("pickesRestaurant", longit);
                    //startActivity(intent);


                    Toast.makeText(getApplicationContext(), "Restaurant added to Database!", Toast.LENGTH_SHORT).show();
                    //TODO: LAND THE USER ON APPROVE BOOKINGS

                }
            });
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