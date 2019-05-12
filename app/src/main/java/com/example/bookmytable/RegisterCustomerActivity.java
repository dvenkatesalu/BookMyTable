package com.example.bookmytable;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterCustomerActivity extends AppCompatActivity {

    EditText fullname, address1, address2, mobilenum, dob;
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
        forRestaurant.setPaintFlags(forRestaurant.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        forRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterCustomerActivity.this, RegisterRestaurantActivity.class));
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO get name, address,mobilenum,dob and pass it to next activity using intent.putExtra()

                if (checkFields()) {
//                    startActivity();
                }
            }
        });

    }

    private boolean checkFields() {
        return isNameValid() && isAddressValid() && isMobileValid() && isDobValid();
    }

    private boolean isDobValid() {
        if(dob.getText().toString().isEmpty()){
            dob.setError("DOB Empty");
            return false;
        }else{
            return true;
        }
    }

    private boolean isMobileValid() {
        if(mobilenum.getText().toString().isEmpty()){
            mobilenum.setError("Phone Number Empty");
            return false;
        }else{
            return true;
        }
    }

    private boolean isAddressValid() {
        if (address1.getText().toString().isEmpty()) {
            address1.setError("Address 1 Empty");
            return false;
        } else if (address2.getText().toString().isEmpty()) {
            address2.setError("Address 2 Empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean isNameValid() {
        if (fullname.getText().toString().isEmpty()) {
            fullname.setError("Please enter valid name");
            return false;
        } else {
            return true;
        }
    }
}
