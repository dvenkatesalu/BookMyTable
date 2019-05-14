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
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DisplayRestaurantsForCustomers extends AppCompatActivity {

    private ListView mListView;
    private static final String API_KEY = "AIzaSyDi1OagTMGaVbLZb5UW8rHpKTWkmgaMZK4";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_SEARCH = "/nearbysearch";
    private static final String OUT_JSON = "/json?";
    private static final String LOG_TAG = "ListRest";

    ArrayList<RestaurantBO> restaurants = new ArrayList<>();

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

        RestaurantBO restaurantBO = new RestaurantBO();
        restaurantBO.address = "17 Lime Street, Sydney";
        restaurantBO.name = "Steersons Steakhouse";
        restaurantBO.ownerId ="Dharanip Priya_rUIcMxXvMabEPpicWggdrkUaRHl1";
        restaurantBO.rating = 3;
        restaurantBO.reference = "";
        restaurantBO.resId = "Steersons SteakhouseDharanip Priya_rUIcMxXvMabEPpicWggdrkUaRHl1";
        restaurants.add(restaurantBO);

        //getRestaurantsToDisplay();


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

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    final String text = tv.getText().toString();
                    FirebaseUser user = mAuth.getCurrentUser();

                    String key = user.getDisplayName() + "_" + user.getUid();

                    String resId = text + key;

                    // CALL MAKE RESERVATION
                    Intent intent = new Intent(DisplayRestaurantsForCustomers.this, MakeBookingActivity.class);
                    intent.putExtra("resId", text);
                    startActivity(intent);


                    Toast.makeText(getApplicationContext(), "Reservation Successfully made!", Toast.LENGTH_LONG).show();
                    //TODO: LAND THE RESTAURANT USER ON APPROVE BOOKINGS

                }
            });
        }
    }

    public void getRestaurantsToDisplay(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference fbRef =
                database.getReference("restaurants");

        fbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("Retrieved restaurant", dataSnapshot.getValue().toString());

                RestaurantBO restaurantBO = new RestaurantBO();
                restaurantBO.address = "17 Lime Street, Sydney";
                restaurantBO.name = "Steersons Steakhouse";
                restaurantBO.ownerId ="Dharanip Priya_rUIcMxXvMabEPpicWggdrkUaRHl1";
                restaurantBO.rating = 3;
                restaurantBO.reference = "";
                restaurantBO.resId = "Steersons SteakhouseDharanip Priya_rUIcMxXvMabEPpicWggdrkUaRHl1";
                restaurants.add(restaurantBO);


                //restaurants.add((RestaurantBO)dataSnapshot.getValue());




                //Map<String, RestaurantBO> restaurantMap = new HashMap<>();



                /*List<HashMap<String, RestaurantBO>> restaurantList = (List<HashMap<String, RestaurantBO>> )dataSnapshot.;

                Iterator iterator = restaurantList.iterator();
                while(iterator.hasNext()) {
                    HashMap<String, RestaurantBO> restaurantMap = (HashMap<String, RestaurantBO>) iterator.next();
                    for (Map.Entry<String, RestaurantBO> entry : restaurantMap.entrySet()) {
                        restaurants.add((RestaurantBO)entry.getValue());
                        Log.d("Retrieved restaurant", entry.getValue().toString());
                    }
                }*/


                /*Set set = restaurantMap.entrySet();
                Iterator iterator = set.iterator();
                while(iterator.hasNext()) {
                    Map.Entry mentry = (Map.Entry)iterator.next();
                    restaurants.add((RestaurantBO) mentry.getValue());
                    Log.d("Retrieved restaurant", mentry.getValue().toString());
                }*/
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.d("Error", "Error" + firebaseError.getMessage());
            }
        });
    }


}