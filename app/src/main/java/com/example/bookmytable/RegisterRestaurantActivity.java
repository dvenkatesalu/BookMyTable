package com.example.bookmytable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterRestaurantActivity extends AppCompatActivity {

    EditText fullname, address1, address2, mobilenum, description;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);

        fullname = findViewById(R.id.editText_fullname_register_restaurant);
        address1 = findViewById(R.id.editText_address1_register_restaurant);
        address2 = findViewById(R.id.editText_address2_register_restaurant);
        mobilenum = findViewById(R.id.editText_phonenum_register_restaurant);
        description = findViewById(R.id.editText_description_register_restaurant);
        next = findViewById(R.id.button_next_restaurant);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    startActivity(new Intent(RegisterRestaurantActivity.this,CommonRegisterActivity.class));
                }
            }
        });
    }

    private boolean checkFields() {
        return isNameValid() && isAddressValid() && isMobileValid() && isDescriptionValid();
    }

    private boolean isDescriptionValid() {
        if (description.getText().toString().isEmpty() ||
                (description.getText().toString().length() < 8 || description.getText().toString().length() > 140)) {
            description.setError("Please Enter Appropriate Description");
            return false;
        } else {
            return true;
        }
    }


    private boolean isMobileValid() {
        if (mobilenum.getText().toString().isEmpty()) {
            mobilenum.setError("Phone Number Empty");
            return false;
        } else {
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
