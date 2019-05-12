package com.example.bookmytable;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterCustomerActivity extends AppCompatActivity {

    EditText fullname, address1,address2 , mobilenum , dob;
    TextView forRestaurant;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);

        fullname = findViewById(R.id.editText_fullname_register_customer);
        address1 = findViewById(R.id.editText_address1_register_customer);
        address2 = findViewById(R.id.editText_address2_register_customer);
        mobilenum = findViewById(R.id.editText_phonenum_register_customer);
        dob = findViewById(R.id.editText_dob_register_customer);
        forRestaurant = findViewById(R.id.textView_register_restaurant);
        next = findViewById(R.id.button_next);


        forRestaurant.setTextColor(Color.BLUE);
        forRestaurant.setPaintFlags(forRestaurant.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }
}
