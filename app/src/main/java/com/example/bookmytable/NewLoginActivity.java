package com.example.bookmytable;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class NewLoginActivity extends AppCompatActivity {

    Button customerButton;
    Button restaurantButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        mAuth = FirebaseAuth.getInstance();

        //Initialize firebaseDB
        FirebaseApp.initializeApp(getBaseContext());

        customerButton = findViewById(R.id.isCustomer);
        restaurantButton = findViewById(R.id.isRestaurant);

        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerLogin();
            }
        });

        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantLogin();
            }
        });
    }

    private void customerLogin(){

        FirebaseUser user = mAuth.getCurrentUser();

        final String key = user.getDisplayName() + "_" + user.getUid();

        final UserInfoBO userInfo = new UserInfoBO();

        userInfo.setEmail(user.getEmail());
        userInfo.setName(user.getDisplayName());
        userInfo.setPhotoUrl(user.getPhotoUrl());
        userInfo.setUid(user.getUid());
        userInfo.setCustomer(true);

        // Get firebase DB instance
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference fbRef =
                database.getReference("users").child("customer").child(key);

        fbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    UserInfoBO userInfo = dataSnapshot.getValue(UserInfoBO.class);
                    Log.d("Retrieving user :", userInfo.toString());
                } else {
                    adduserDataToFB(userInfo, fbRef);
                }

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.d("Error", "Error" + firebaseError.getMessage());
            }
        });
        startActivity(new Intent(NewLoginActivity.this,MainActivity.class));
    }

    private void adduserDataToFB( UserInfoBO user, DatabaseReference fbRef){
        Toast.makeText(NewLoginActivity.this, "Inside add to db.",
                Toast.LENGTH_SHORT).show();

        fbRef.child("name").setValue(user.getName());
        fbRef.child("email").setValue(user.getEmail());
        fbRef.child("photo").setValue(user.getPhotoUrl().toString());
        fbRef.child("uid").setValue(user.getUid());
        fbRef.child("isCustomer").setValue(user.getCustomer());
        fbRef.child("isRestaurant").setValue(user.getRestaurant());
    }

    private void restaurantLogin(){
        FirebaseUser user = mAuth.getCurrentUser();

        final String key = user.getDisplayName() + "_" + user.getUid();

        final UserInfoBO userInfo = new UserInfoBO();

        userInfo.setEmail(user.getEmail());
        userInfo.setName(user.getDisplayName());
        userInfo.setPhotoUrl(user.getPhotoUrl());
        userInfo.setUid(user.getUid());
        userInfo.setRestaurant(true);

        // Get firebase DB instance
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference fbRef =
                database.getReference("users").child("restaurant").child(key);

        fbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    UserInfoBO userInfo = dataSnapshot.getValue(UserInfoBO.class);
                    Log.d("Retrieving user :", userInfo.toString());
                    startActivity(new Intent(NewLoginActivity.this,MainActivity.class));
                } else {
                    adduserDataToFB(userInfo, fbRef);
                }

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.d("Error", "Error" + firebaseError.getMessage());
            }
        });

        //TODO: CHANGE TO RESTAURANT LANDING SCREEN
        //startActivity(new Intent(NewLoginActivity.this,MainActivity.class));

    }
}
