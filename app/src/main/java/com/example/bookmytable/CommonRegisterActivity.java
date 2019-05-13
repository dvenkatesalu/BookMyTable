package com.example.bookmytable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CommonRegisterActivity extends AppCompatActivity {

    EditText username,password;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_register);

        //TODO value = getIntent(key) and use the value;

        username = findViewById(R.id.editText3_username_common_register);
        password = findViewById(R.id.editText_password_common_register);
        register = findViewById(R.id.buttonRegister_common_register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    //TODO SIGNIN using firebase function;
                    return; //not required.
                }
            }
        });
    }
}
